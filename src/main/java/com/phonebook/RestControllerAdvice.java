package com.phonebook;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RestControllerAdvice implements ResponseBodyAdvice<Object> {
	
	private static final Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		return body instanceof ResponseEnvelop ? body : new ResponseEnvelop<>(body);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEnvelop<?> processValidationError(MethodArgumentNotValidException ex) {
		
		logger.error("Bad request, validation errors");
        ResponseEnvelop<?> response = new ResponseEnvelop<Object>(false, "");
        
        List<String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> {
                    String name = field.getField();
                    String errorMessage = field.getDefaultMessage();
                    return name + ": " + errorMessage;
                })
                .collect(Collectors.toList());
        
        response.setErrors(validationErrors);
        return response;
    }
	
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEnvelop<?> processRuntimeException(RuntimeException ex) {
        logger.error("Unexpected exception occurred: ", ex);
        ResponseEnvelop<?> response = new ResponseEnvelop<Object>(false, "");
        response.addError("Internal Server Error");
        return response;
    }

}
