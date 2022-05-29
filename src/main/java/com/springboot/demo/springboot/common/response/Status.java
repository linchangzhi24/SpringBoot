package com.springboot.demo.springboot.common.response;

public enum Status {
    SUCCESS(200),

    FAILED(400),

    NOT_FOUND(404),

    INTERNAL_SERVER_ERROR(500);

    Status(int status) {
        this.status = status;
    }

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
