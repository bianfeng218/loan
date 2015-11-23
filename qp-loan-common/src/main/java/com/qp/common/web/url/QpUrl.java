package com.qp.common.web.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @author haiping
 *
 */
public class QpUrl {
	private String protocol;
	private String host;
	private int port;
	private String contextPath;
	private String path;
	private QpUrl dlUrl;
	private String charsetName = "utf-8";
	private boolean filter = true;
	private Map<String, Object> query = new LinkedHashMap<String, Object>();

	public void setUrl(String url) throws MalformedURLException {
		URL a = new URL(url);
		this.protocol = a.getProtocol();
		this.host = a.getHost();
		this.port = a.getPort();
		this.contextPath = a.getPath();
		String queryString = a.getQuery();
		if (!StringUtils.isEmpty(queryString))
			this.query.putAll(getQueryMap(queryString));
	}

	private Map<String, Object> getQueryMap(String query) {
		String[] params = query.split("&");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for (String param : params) {
			String[] strings = param.split("=");
			String name = strings[0];
			String value = null;
			if (strings.length > 1) {
				value = strings[1];
			}
			map.put(name, value);
		}
		return map;
	}

	public QpUrl getTarget(String target) {
		this.path = target;
		return this.clone();
	}
	
	public QpUrl clone() {
		QpUrl clone = new QpUrl();
		setDlUrlValue(clone, this);
	    clone.query = new LinkedHashMap<String,Object>();
	    clone.query.putAll(this.query);
	    return clone;
	  }

	public QpUrl addQueryData(String name, String value) {
		this.query.put(name, value);
		return this;
	}

	public QpUrl addQueryData(String name, Object value) {
		this.query.put(name, value);
		return this;
	}

	public QpUrl addQueryData(String name, int value) {
		this.query.put(name, Integer.valueOf(value));
		return this;
	}

	public QpUrl addQueryData(String name, long value) {
		this.query.put(name, Long.valueOf(value));
		return this;
	}

	public QpUrl addQueryData(String name, float value) {
		this.query.put(name, Float.valueOf(value));
		return this;
	}

	public String render() {
		try {
			dlUrl = new QpUrl();
			dlUrl.query = new LinkedHashMap<String, Object>();
			dlUrl.query.putAll(this.query);
			setDlUrlValue(dlUrl, this);
			return doIt();
		} finally {
		    setDlUrlValue(this, dlUrl);
			this.query.putAll(dlUrl.query);
		}
	}

	private void setDlUrlValue(QpUrl dest, QpUrl src) {
		dest.protocol = src.protocol;
		dest.host = src.host;
		dest.port = src.port;
		dest.contextPath = src.contextPath;
		dest.path = src.path;
	}

	@SuppressWarnings("rawtypes")
	private String doIt() {
		String str;
		try {

			String path = prefixPath(this.contextPath, this.path);

			URL url = new URL(this.protocol, this.host, this.port, path);
			if (url.getDefaultPort() == url.getPort()) {
				url = new URL(this.protocol, this.host, -1, path);
			}
			str = url.toString();
		} catch (Exception e) {
			str = "/";
		}

		StringBuilder builder = new StringBuilder(str);
		if (!this.query.isEmpty()) {
			for (String key : this.query.keySet()) {
				Object obj = this.query.get(key);

				Iterator it;
				if ((obj instanceof List)) {
					List list = (List) obj;
					for (it = list.iterator(); it.hasNext();) {
						Object o = it.next();
						setValue(builder, key, o);
					}
				} else {
					Map map;
					if ((obj instanceof Map)) {
						map = (Map) obj;
						for (it = map.keySet().iterator(); it.hasNext();) {
							Object o = it.next();
							setValue(builder, o == null ? "" : o.toString(),
									map.get(o));
						}
					} else if ((obj != null) && (obj.getClass().isArray())) {
						Object[] arrays = (Object[]) obj;
						for (Object o : arrays)
							setValue(builder, key, o);
					} else {
						setValue(builder, key, obj);
					}
				}
			}
			return builder.replace(str.length(), str.length() + 1, "?")
					.toString();
		}
		return str;
	}

	public String prefixPath(String contextPath, String path) {
		String returnPath;
		if ((path == null) || (contextPath == null)) {
			if ((path == null) && (contextPath == null)) {
				returnPath = "/";
			} else {
				if (contextPath == null)
					returnPath = path;
				else
					returnPath = contextPath;
			}
		} else {
			if ((contextPath.endsWith("/")) && (path.startsWith("/")))
				returnPath = contextPath + path.substring(1);
			else {
				returnPath = contextPath + path;
			}
		}
		return returnPath;
	}

	public void setValue(StringBuilder builder, String key, Object o) {
		String value = o == null ? "" : o.toString();
		if (value.length() > 0) {
			String str1 = encodeUrl(value);
			builder.append("&").append(key).append("=").append(str1);
		} else if (!this.filter) {
			builder.append("&").append(key).append("=");
		}
	}

	@SuppressWarnings("deprecation")
	public String encodeUrl(String value) {
		String str1;
		if (StringUtils.isNotBlank(this.charsetName))
			try {
				str1 = URLEncoder.encode(value, this.charsetName);
			} catch (Exception e) {
				str1 = value;
			}
		else {
			str1 = URLEncoder.encode(value);
		}
		return str1;
	}
	
	public void setDlUrl(QpUrl dlUrl) {
		this.dlUrl = dlUrl;
	}

	public void reset() {
		try {
			this.query.clear();
			this.dlUrl.query.clear();
			this.dlUrl.setDlUrlValue(this, this.dlUrl);
		} catch (Exception e) {
		
		}
	}

	@Override
	public String toString() {
		return render();
	}
}
