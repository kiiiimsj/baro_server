package com.wantchu.wantchu_server2.store.exception;

public class StoreTypeNotFoundException extends StoreException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "해당 type_code를 가진 가게 정보가 존재하지 않습니다.";

    public StoreTypeNotFoundException() {
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
