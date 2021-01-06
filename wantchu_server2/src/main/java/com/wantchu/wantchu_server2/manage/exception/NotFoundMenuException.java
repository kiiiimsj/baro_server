package com.wantchu.wantchu_server2.manage.exception;

public class NotFoundMenuException extends ManageException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 14;
    public static final String MESSAGE = "Menu 리스트가 존재하지 않습니다.";

    public NotFoundMenuException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() { return super.getErrno(); }

    @Override
    public String getMessage() { return super.getMessage();}

}
