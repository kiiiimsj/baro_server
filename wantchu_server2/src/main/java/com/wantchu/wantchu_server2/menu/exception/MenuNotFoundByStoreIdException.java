package com.wantchu.wantchu_server2.menu.exception;

public class MenuNotFoundByStoreIdException extends MenuException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "해당 store_id를 가진 메뉴 정보가 없습니다.";

    public MenuNotFoundByStoreIdException() {
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
