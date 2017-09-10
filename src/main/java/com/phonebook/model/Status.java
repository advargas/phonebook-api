package com.phonebook.model;

import java.util.Date;

public class Status {

	private String status;
    private Date timestamp;

    public Status(String status) {
        this.status = status;
        timestamp = new Date();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
