package com.wantchu.wantchu_server2.menu.exception;

public class MenuNotFoundByCategoryIdException extends MenuException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "해당 store_id와 category_id를 가진 메뉴 정보가 존재하지 않습니다.";

    public MenuNotFoundByCategoryIdException() {
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
