package com.wantchu.wantchu_server2.favorite.exception;

public class FavoriteDeleteException extends FavoriteException {

    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 3;
    public static final String MESSAGE = "즐겨찾기 삭제에 실패했습니다.";

    public FavoriteDeleteException() {
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
