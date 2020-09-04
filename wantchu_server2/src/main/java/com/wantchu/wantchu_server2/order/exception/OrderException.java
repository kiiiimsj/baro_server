package com.wantchu.wantchu_server2.order.exception;

public abstract class OrderException extends Exception {

    private static final long serialVersionUID = 1L;

    protected int errno;

    public OrderException(String e) {
        super(e);
    }

    public OrderException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }
}
