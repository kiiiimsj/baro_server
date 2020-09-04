package com.wantchu.wantchu_server2.owner.exception;

public class OwnerEmailInUseException extends OwnerException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 3;
    public static final String MESSAGE = "이미 사용중인 이메일 입니다.";

    public OwnerEmailInUseException() {
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
