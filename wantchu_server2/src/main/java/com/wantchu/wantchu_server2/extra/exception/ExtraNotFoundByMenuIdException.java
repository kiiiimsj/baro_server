package com.wantchu.wantchu_server2.extra.exception;

public class ExtraNotFoundByMenuIdException extends ExtraException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "해당 menu_id를 가진 extra 정보가 존재하지 않습니다.";

    public ExtraNotFoundByMenuIdException() {
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
