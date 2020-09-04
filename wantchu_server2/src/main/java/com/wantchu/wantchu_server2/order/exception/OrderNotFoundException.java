package com.wantchu.wantchu_server2.order.exception;

public class OrderNotFoundException extends OrderException{

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 3;
    public static final String MESSAGE = "주문 내역이 존재하지 않습니다.";

    public OrderNotFoundException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public int getErrno() {
        return super.getErrno();
    }
}
