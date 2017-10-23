package com.myProject.framework;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CallbackJsonpConverter extends MappingJackson2HttpMessageConverter {

	public static Logger logger = Logger.getLogger(CallbackJsonpConverter.class);
	
    // 
    private String callbackName;

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {
        // 
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();
        String callbackParam = request.getParameter(callbackName);
        if (StringUtils.isEmpty(callbackParam)) {
            // 
        	logger.info("No callbackParam");
            super.writeInternal(object, outputMessage);
        } else {
            com.fasterxml.jackson.core.JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
            try {
            	logger.info("CallbackParam:"+callbackParam);
                String result = callbackParam + "(" + super.getObjectMapper().writeValueAsString(object) + ");";
                IOUtils.write(result, outputMessage.getBody(), encoding.getJavaName());
                logger.info("outputMessage:"+result);
            } catch (JsonProcessingException ex) {
                throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
            }
        }

    }

    public String getCallbackName() {
        return callbackName;
    }

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

}