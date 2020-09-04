package com.wantchu.wantchu_server2.store.exception;

public class StoreNameNotFoundException extends StoreException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 4;
    public static final String MESSAGE = "해당 가게 이름으로 조회된 가게 정보가 없습니다.";

    public StoreNameNotFoundException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public int getErrno() {
        return super.getErrno();
    }
}
