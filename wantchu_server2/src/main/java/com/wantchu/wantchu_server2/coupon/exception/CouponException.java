package com.wantchu.wantchu_server2.coupon.exception;

public abstract class CouponException extends Exception {
    private static final long serialVersionUID = 1L;

    protected int errno;

    public CouponException(String e) {
        super(e);
    }

    public CouponException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }
}
