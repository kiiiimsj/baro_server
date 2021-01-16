package com.wantchu.wantchu_server2.manage.exception;

public class NotFoundExtraByMenuException extends ManageException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 23;
    public static final String MESSAGE = "ExtraByMenu 리스트가 존재하지 않습니다.";

    public NotFoundExtraByMenuException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() { return super.getErrno(); }

    @Override
    public String getMessage() { return super.getMessage();}

}
