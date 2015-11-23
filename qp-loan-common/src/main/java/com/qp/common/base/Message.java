package com.qp.common.base;




import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;



/**
 * @author haiping
 *
 */
public class Message implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String code;// 编码
    private String result;// 结果说明
    private Map<String,Object> data = new HashMap<String,Object>();
    
    public Message() {
        // 默认构造方法
    }

    public Message(String code, String result) {
        this.code = code;
        this.result = result;
    }
    
    public Message addModel(String key,Object value){
    	this.data.put(key, value);
    	return this;
    }

    public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public static Message create(String code,String result) {
        return new Message(code, result);
    }
    
    public static Message success() {
        return success("操作成功!");
    }
    
    public static Message success(String msg) {
        return create("success", msg);
    }

    public static Message failure() {
        return failure("操作失败!");
    }
    
    public static Message failure(String msg) {
        return create("failure", msg);
    }
    
    public static Message failure(Exception ex) {
        return failure("系统异常:"+ex.getMessage(),ex);
    }
    
    public static Message failure(String message, Exception ex) {
        if(ex == null) {
            return failure();
        }
        Message msg = failure(message);
        try {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
        } catch (Exception e) {
        }
        return msg;
    }
    
    public boolean isSuccess(){
        return "success".equals(this.code);
    }
}
