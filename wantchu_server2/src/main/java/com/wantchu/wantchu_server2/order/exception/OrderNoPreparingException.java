package com.wantchu.wantchu_server2.order.exception;

public class OrderNoPreparingException extends OrderException{

    private static final long serialVersionUID = 1L;

    public static final int ERRNO = 2;
    public static final String MESSAGE = "새로 들어온 주문 사항이 없습니다.";

    public OrderNoPreparingException() {
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
