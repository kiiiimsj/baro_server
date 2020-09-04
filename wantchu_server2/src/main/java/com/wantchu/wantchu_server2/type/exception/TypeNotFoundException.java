package com.wantchu.wantchu_server2.type.exception;

public class TypeNotFoundException extends TypeException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "등록된 가게 종류 정보가 없습니다.";

    public TypeNotFoundException() {super(ERRNO, MESSAGE);}

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    @Override
    public int getErrno() {
        return ERRNO;
    }
}
