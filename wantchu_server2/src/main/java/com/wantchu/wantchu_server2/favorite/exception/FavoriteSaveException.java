package com.wantchu.wantchu_server2.favorite.exception;

public class FavoriteSaveException extends FavoriteException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "이미 즐겨찾기로 등록된 가게 입니다.";

    public FavoriteSaveException() {
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
