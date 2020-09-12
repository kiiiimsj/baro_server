package com.wantchu.wantchu_server2.event.exception;

public class EventNotFoundException extends EventException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "등록된 이벤트가 없습니다.";

    public EventNotFoundException(){ super(ERRNO, MESSAGE); }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    @Override
    public int getErrno() {
        return ERRNO;
    }
}
