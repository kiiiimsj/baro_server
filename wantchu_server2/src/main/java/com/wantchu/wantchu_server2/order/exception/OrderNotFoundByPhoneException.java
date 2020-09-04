package com.wantchu.wantchu_server2.order.exception;

public class OrderNotFoundByPhoneException extends OrderException {

    private static final long serialVersionUID = 1L;
    private static final int ERRNO = 1;
    private static final String MESSAGE = "주문 내역이 존재하지 않습니다.";

    public OrderNotFoundByPhoneException() {
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
