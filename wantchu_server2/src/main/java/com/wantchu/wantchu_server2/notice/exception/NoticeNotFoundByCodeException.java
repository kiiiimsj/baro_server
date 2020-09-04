package com.wantchu.wantchu_server2.notice.exception;

public class NoticeNotFoundByCodeException extends NoticeException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 3;
    public static final String MESSAGE = "notice_code가 잘못되었습니다.";

    public NoticeNotFoundByCodeException() {
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
