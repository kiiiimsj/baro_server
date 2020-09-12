package com.wantchu.wantchu_server2.alert.exception;

public class AlertNotFoundException extends AlertException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "현재 DB에 알림 등록된게 없습니다.";

    public AlertNotFoundException(){ super(ERRNO, MESSAGE); }

    @Override
    public int getErrno() {
        return super.getErrno();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
