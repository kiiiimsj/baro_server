package com.wantchu.wantchu_server2.coupon.exception;

public class CouponHistoryNotFoundException extends CouponException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "쿠폰 사용 내역이 존재하지 않습니다.";

    public CouponHistoryNotFoundException() {
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
