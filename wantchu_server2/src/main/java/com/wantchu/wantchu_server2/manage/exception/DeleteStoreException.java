package com.wantchu.wantchu_server2.manage.exception;

public class DeleteStoreException extends ManageException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 9;
    public static final String MESSAGE = "Store 삭제에 실패하였습니다.";

    public DeleteStoreException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() { return super.getErrno(); }

    @Override
    public String getMessage() { return super.getMessage();}
}
