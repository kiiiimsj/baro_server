package com.wantchu.wantchu_server2.manage.exception;

public class DeleteAlertException extends ManageException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 7;
    public static final String MESSAGE = "Alert 삭제에 실패하였습니다.";

    public DeleteAlertException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() { return super.getErrno(); }

    @Override
    public String getMessage() { return super.getMessage();}
}
