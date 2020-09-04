package com.wantchu.wantchu_server2.inquiry.exception;

public class InquiryNotFoundByIdException extends InquiryException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "해당 id의 문의 내역이 존재하지 않습니다.";

    public InquiryNotFoundByIdException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public int getErrno() {
        return super.getErrno();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
