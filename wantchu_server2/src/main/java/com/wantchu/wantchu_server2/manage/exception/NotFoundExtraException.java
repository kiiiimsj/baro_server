package com.wantchu.wantchu_server2.manage.exception;

public class NotFoundExtraException extends ManageException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 23;
    public static final String MESSAGE = "Extra 리스트가 존재하지 않습니다.";

    public NotFoundExtraException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() { return super.getErrno(); }

    @Override
    public String getMessage() { return super.getMessage();}

}
