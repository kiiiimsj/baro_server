package com.wantchu.wantchu_server2.owner.exception;

public class OwnerIdInUseException extends  OwnerException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 6;
    public static final String MESSAGE = "이미 사용하고 있는 ID 입니다.";

    public OwnerIdInUseException() { super(ERRNO, MESSAGE);}

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public int getErrno() {
        return super.getErrno();
    }
}
