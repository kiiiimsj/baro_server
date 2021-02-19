package com.wantchu.wantchu_server2.manage.exception;

public class PayBackMoneyListNotFoundException extends ManageException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 29;
    public static final String MESSAGE = "PayBackList가 존재하지 않습니다.";

    public PayBackMoneyListNotFoundException() {super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() { return super.getErrno(); }

    @Override
    public String getMessage() { return super.getMessage();}
}
