package com.wantchu.wantchu_server2.coupon.exception;

public class CouponNotInsertException extends CouponException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 4;
    public static final String MESSAGE = "이미 사용한 쿠폰이거나 쿠폰번호가 유효하지 않습니다.";

    public CouponNotInsertException() {
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
