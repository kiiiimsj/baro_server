package com.wantchu.wantchu_server2.member.exception;

public class MemberEmailInUseException extends MemberException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 3;
    public static final String MESSAGE = "이미 사용중인 이메일 입니다.";

    public MemberEmailInUseException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public String getMessage() {return MESSAGE;}

    @Override
    public int getErrno() {
        return ERRNO;
    }
}
