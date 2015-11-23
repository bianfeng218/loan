package com.qp.common.dao;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * @author haiping
 *
 */
@SuppressWarnings({ "rawtypes", "deprecation" })
public class BaseDaoImpl extends SqlMapClientTemplate {
	public static final Logger log = LogManager.getLogger(BaseDaoImpl.class);
	
	public List queryForListMerge(String statementName, String getFieldName,
			String setFieldname, String mergeFieldKeyName,
			String mergeStatementName) throws DataAccessException {
		return queryForListMerge(statementName, null, getFieldName,
				setFieldname, mergeFieldKeyName, mergeStatementName);
	}

	public List queryForListMerge(String statementName, Object parameterObject,
			String getFieldName, String setFieldname, String mergeFieldKeyName,
			String mergeStatementName) throws DataAccessException {
		List list = queryForList(statementName, parameterObject);
		merge(list, getFieldName, setFieldname, mergeFieldKeyName,
				mergeStatementName);

		return list;
	}

	public void merge(List list, String getFieldName, String setFieldname,
			String mergeFieldKeyName, String mergeStatementName) {
		Set<String> values = new HashSet<String>();
		try {
			for (Iterator i$ = list.iterator(); i$.hasNext();) {
				Object o = i$.next();
				String property = BeanUtils.getProperty(o, getFieldName);
				if (!StringUtils.isEmpty(property)) {
					values.add(property);
				}
			}

			if (!values.isEmpty()) {
				StringBuilder builder = new StringBuilder();

				for (String value : values) {
					builder.append(',');
					builder.append(value);
				}

				List merges = queryForList(mergeStatementName,
						builder.substring(1));
				for (Iterator i$ = list.iterator(); i$.hasNext();) {
					Object obj = i$.next();
					String val = BeanUtils.getProperty(obj, getFieldName);

					if (!StringUtils.isEmpty(val))
						for (i$ = merges.iterator(); i$.hasNext();) {
							Object merge = i$.next();
							if (val.equals(BeanUtils.getProperty(merge,
									mergeFieldKeyName))) {
								BeanUtils.setProperty(obj, setFieldname, merge);
								break;
							}
						}
				}
			}
		} catch (Exception e) {
			log.error("merge error!", e);
			throw new RuntimeException("merge error!", e);
		}
	}
}