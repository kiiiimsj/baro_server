package com.wantchu.wantchu_server2.owner.exception;

public class OwnerLoginException extends OwnerException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "등록되지 않은 전화번호이거나 비밀번호가 일치하지 않습니다.";

    public OwnerLoginException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    @Override
    public int getErrno() {
        return ERRNO;
    }
}
