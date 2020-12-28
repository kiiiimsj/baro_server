package com.wantchu.wantchu_server2.manage.exception;

public class DeleteTypeException extends ManageException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "type삭제에 실패하였습니다.";

    public DeleteTypeException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() {
        return super.getErrno();
    }

    @Override
    public String getMessage() { return super.getMessage(); }
}
