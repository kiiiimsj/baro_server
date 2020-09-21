package com.wantchu.wantchu_server2.alert.exception;

import com.wantchu.wantchu_server2.member.exception.MemberException;

public class DoNotHaveAnyMoreAlert extends MemberException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "새로 들어온 알림이 존재하지 않습니다.";

    public DoNotHaveAnyMoreAlert() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public String getMessage() {return MESSAGE;}

    @Override
    public int getErrno() {
        return ERRNO;
    }
}
