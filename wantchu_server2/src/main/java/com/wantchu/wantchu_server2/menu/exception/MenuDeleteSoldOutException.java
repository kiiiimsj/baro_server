package com.wantchu.wantchu_server2.menu.exception;

public class MenuDeleteSoldOutException extends MenuException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 4;
    public static final String MESSAGE = "이미 판매진행중인 상품입니다.";

    public MenuDeleteSoldOutException() {
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
