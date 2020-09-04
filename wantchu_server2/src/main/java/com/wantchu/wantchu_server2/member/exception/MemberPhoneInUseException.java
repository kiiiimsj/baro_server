package com.wantchu.wantchu_server2.member.exception;

public class MemberPhoneInUseException extends MemberException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "이미 가입된 전화번호 입니다.";

    public MemberPhoneInUseException() {
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
