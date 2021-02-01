package com.wantchu.wantchu_server2.store.exception;

public class StoreAllNotFoundException extends StoreException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 6;
    public static final String MESSAGE = "DB에 가게가 존재하지않습니다.";

    public StoreAllNotFoundException() {
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
