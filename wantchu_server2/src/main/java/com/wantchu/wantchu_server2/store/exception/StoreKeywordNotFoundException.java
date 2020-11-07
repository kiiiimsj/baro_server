package com.wantchu.wantchu_server2.store.exception;

public class StoreKeywordNotFoundException extends StoreException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 5;
    public static final String MESSAGE = "해당 검색내용을 포함하는 가게 정보가 존재하지 않습니다.";

    public StoreKeywordNotFoundException() {
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
