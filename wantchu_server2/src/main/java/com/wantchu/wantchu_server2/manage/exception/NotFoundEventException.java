package com.wantchu.wantchu_server2.manage.exception;

public class NotFoundEventException extends ManageException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 16;
    public static final String MESSAGE = "Event 리스트가 존재하지 않습니다.";

    public NotFoundEventException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() { return super.getErrno(); }

    @Override
    public String getMessage() { return super.getMessage();}

}
