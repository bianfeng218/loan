package com.qp.common.web.view;


import org.apache.velocity.context.Context;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

import com.qp.common.web.url.QpUrl;

import javax.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * @author haiping
 *
 */
public class QpVelocityLayoutView extends VelocityLayoutView {

    /**
     * 存放公共url的map
     */
    private Map<String, QpUrl> velocityUrl;

    /**
     * 页面工具类
     */
    private Map<String, Object> velocityTools;

    /**
     * 渲染页面公共内容
     *
     * @param context
     * @param response
     * @throws Exception
     */
    protected void doRender(Context context, HttpServletResponse response) throws Exception {
        //将公共url merge到页面
        mergeUrl(context, velocityUrl);
        //渲染工具类到页面
        merge(context, velocityTools);
        //渲染页面内容
        super.doRender(context, response);
    }


    /**
     * 将公共url注入到context
     *
     * @param context
     * @param map
     */
    private void mergeUrl(Context context, Map<String, QpUrl> map) {
        if (map != null) {
            for (Map.Entry<String, QpUrl> stringUrlEntry : map.entrySet()) {
                String key = stringUrlEntry.getKey();
                QpUrl org = stringUrlEntry.getValue();
                QpUrl value = org.clone();
                value.setDlUrl(org);
                context.put(key, value);
            }
        }
    }

    private void merge(Context context, Map<String, Object> map) {
        if (map != null) {
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                context.put(stringObjectEntry.getKey(), stringObjectEntry.getValue());
            }
        }
    }

    public void setVelocityUrl(Map<String, QpUrl> velocityUrl) {
        this.velocityUrl = velocityUrl;
    }

    public void setVelocityTools(Map<String, Object> velocityTools) {
        this.velocityTools = velocityTools;
    }
}
