package com.phonebook;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Aspect
@Component
public class RestServiceMonitor {

	private static final Logger logger = LoggerFactory.getLogger(RestServiceMonitor.class);
    
    private static Gson gson = new Gson();
    
    @Around("execution(* com.phonebook.controllers.ContactController.*(..))")
    public Object restLogger(ProceedingJoinPoint point) throws Throwable {
        
    	Logger classLog = LoggerFactory.getLogger(point.getTarget().getClass());
    	
        try {
        	logParameters(point.getTarget().getClass(), classLog, point.getArgs());
            
        } catch (Exception e) { // avoids error in services due to any issues while writing to logs
            logger.info("Unable to write to logs, check " + getClass(), e);
        }

        try {
            Object retVal = point.proceed();
            
            String responseString = gson.toJson(retVal);
            classLog.info("PHONEBOOK: REST Response " + responseString);
            
            return retVal;
            
        } catch (Exception ex) {
            classLog.error("Error found: ", ex);
            throw ex;
        }
    }
    
    private void logParameters(Class<?> clazz, Logger classLog, Object[] args) {
    	
    	if (args != null && args.length > 0) {
        	StringBuilder builder = new StringBuilder("");
            
            builder.append("PHONEBOOK: REST Request Parameters: ");
            for (int i = 0; i < args.length; i ++) {
                Object arg = args[i];
                String parameter = gson.toJson(arg);
                builder.append(parameter);
                if (i != args.length - 1) {
                	builder.append(",");
                }
            }
            classLog.info(builder.toString());
            
        }
    }
}

