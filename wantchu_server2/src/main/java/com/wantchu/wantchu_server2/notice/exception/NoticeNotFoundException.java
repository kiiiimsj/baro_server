package com.wantchu.wantchu_server2.notice.exception;

public class NoticeNotFoundException extends NoticeException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "등록된 알림, 공지사항 또는 이벤트가 존재하지 않습니다.";

    public NoticeNotFoundException() {
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
