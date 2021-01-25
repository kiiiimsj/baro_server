package com.wantchu.wantchu_server2.manage.exception;

public class NotFoundRecentInsertException extends ManageException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 24;
    public static final String MESSAGE = "최근에 Insert한 목록이 존재하지 않습니다.";

    public NotFoundRecentInsertException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() { return super.getErrno(); }

    @Override
    public String getMessage() { return super.getMessage();}

}
