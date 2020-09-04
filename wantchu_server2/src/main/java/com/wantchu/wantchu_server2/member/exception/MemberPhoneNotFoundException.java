package com.wantchu.wantchu_server2.member.exception;

public class MemberPhoneNotFoundException extends MemberException{

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 5;
    public static final String MESSAGE = "존재하지 않는 고객 전화번호 입니다.";

    public MemberPhoneNotFoundException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public int getErrno() {
        return super.getErrno();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
