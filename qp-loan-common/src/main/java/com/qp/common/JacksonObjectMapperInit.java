package com.qp.common;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.text.SimpleDateFormat;

public class JacksonObjectMapperInit {
	public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    private ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper()
	{
	  return this.objectMapper;
	}
	
	public void setObjectMapper(ObjectMapper objectMapper) {
	  this.objectMapper = objectMapper;
	}
	
	public void init()
	{
	  this.objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	  SerializationConfig serConfig = this.objectMapper.getSerializationConfig();
        serConfig.withDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        DeserializationConfig deserializationConfig = this.objectMapper.getDeserializationConfig();
        deserializationConfig.withDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        this.objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
}
