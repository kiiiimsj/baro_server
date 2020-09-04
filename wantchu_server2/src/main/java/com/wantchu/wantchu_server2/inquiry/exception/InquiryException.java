package com.wantchu.wantchu_server2.inquiry.exception;

public class InquiryException extends Exception {
    private static final long serialVersionUID = 1L;

    protected int errno;

    public InquiryException(String e) {
        super(e);
    }

    public InquiryException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;

    }
}
