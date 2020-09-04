package com.wantchu.wantchu_server2.inquiry.exception;

public class InquiryNotFoundByEmailException extends InquiryException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "1:1 문의 내역이 존재하지 않습니다.";

    public InquiryNotFoundByEmailException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public int getErrno() {
        return super.getErrno();
    }
}
