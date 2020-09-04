package com.wantchu.wantchu_server2.member.exception;

public class MemberLoginException extends MemberException{

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "등록되지 않은 회원이거나 비밀번호가 일치하지 않습니다.";

    public MemberLoginException() {
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
