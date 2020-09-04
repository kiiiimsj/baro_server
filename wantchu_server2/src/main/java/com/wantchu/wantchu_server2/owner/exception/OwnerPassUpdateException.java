package com.wantchu.wantchu_server2.owner.exception;

public class OwnerPassUpdateException extends OwnerException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "가입된 전화번호가 아닙니다.";

    public OwnerPassUpdateException() {
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
