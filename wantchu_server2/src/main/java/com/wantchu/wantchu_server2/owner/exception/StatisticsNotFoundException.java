package com.wantchu.wantchu_server2.owner.exception;

public class StatisticsNotFoundException extends  OwnerException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 5;
    public static final String MESSAGE = "통계할 정보가 존재하지 않습니다.";

    public StatisticsNotFoundException() { super(ERRNO, MESSAGE);}

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public int getErrno() {
        return super.getErrno();
    }
}
