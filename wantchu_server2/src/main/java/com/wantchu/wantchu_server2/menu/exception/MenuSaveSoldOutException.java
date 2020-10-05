package com.wantchu.wantchu_server2.menu.exception;

public class MenuSaveSoldOutException extends MenuException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 3;
    public static final String MESSAGE = "이미 품절처리된 상품입니다.";

    public MenuSaveSoldOutException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    @Override
    public int getErrno() {
        return ERRNO;
    }
}
