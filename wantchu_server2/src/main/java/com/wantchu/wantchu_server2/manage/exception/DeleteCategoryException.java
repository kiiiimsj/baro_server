package com.wantchu.wantchu_server2.manage.exception;

public class DeleteCategoryException extends ManageException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 11;
    public static final String MESSAGE = "Category 삭제에 실패하였습니다.";

    public DeleteCategoryException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() { return super.getErrno(); }

    @Override
    public String getMessage() { return super.getMessage();}
}
