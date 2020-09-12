package com.wantchu.wantchu_server2.favorite.exception;

public class FavoriteExistException extends FavoriteException{
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 4;
    public static final String MESSAGE = "해당회원이 즐겨찾기한 가게가 아닙니다.";

    public FavoriteExistException(){ super(ERRNO, MESSAGE);}

    @Override
    public int getErrno() {
        return super.getErrno();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
