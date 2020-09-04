package com.wantchu.wantchu_server2.owner.exception;

public class OwnerPhoneInUseException extends OwnerException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 4;
    public static final String MESSAGE = "이미 가입된 전화번호 입니다.";

    public OwnerPhoneInUseException() {
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
