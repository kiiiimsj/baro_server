package com.wantchu.wantchu_server2.coupon.exception;

public class CouponNoUsableFoundException extends CouponException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 3;
    public static final String MESSAGE = "적용 가능한 쿠폰이 없습니다.";

    public CouponNoUsableFoundException() {
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
