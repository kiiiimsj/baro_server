package com.wantchu.wantchu_server2.store.exception;

public class StoreSearchException extends StoreException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 3;
    public static final String MESSAGE = "검색 결과가 존재하지 않습니다.";

    public StoreSearchException() {
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
