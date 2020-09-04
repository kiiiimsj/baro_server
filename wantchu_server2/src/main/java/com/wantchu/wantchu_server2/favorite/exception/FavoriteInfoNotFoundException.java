package com.wantchu.wantchu_server2.favorite.exception;

public class FavoriteInfoNotFoundException extends FavoriteException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "등록된 즐겨찾기 정보가 없습니다.";

    public FavoriteInfoNotFoundException() {
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
