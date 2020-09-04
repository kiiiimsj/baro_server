package com.wantchu.wantchu_server2.notice.exception;

public class NoticeIdNotFoundException extends NoticeException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "해당 notice_id로 존재하는 공지사항이 없습니다.";

    public NoticeIdNotFoundException() {
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
