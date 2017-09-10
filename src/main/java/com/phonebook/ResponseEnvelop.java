package com.phonebook;

import java.util.ArrayList;
import java.util.List;

public class ResponseEnvelop<T> {

    private boolean success = true;

    private T response;

    private List<String> errors = new ArrayList<String>();

    public ResponseEnvelop() {
    }

    public ResponseEnvelop(T result) {
        this.response = result;
    }

    /**
     * @param success
     * @param result
     */
    public ResponseEnvelop(boolean success, T result) {
        super();
        this.success = success;
        this.response = result;
    }

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<String>();
        }
        this.errors.add(error);
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * @param success
     *            the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the result
     */
    public T getResponse() {
        return this.response;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResponse(T result) {
        this.response = result;
    }

    /**
     * @return the errors
     */
    public List<String> getErrors() {
        return this.errors;
    }

    /**
     * @param errors
     *            the errors to set
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
