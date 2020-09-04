package com.wantchu.wantchu_server2.favorite.exception;

public abstract class FavoriteException extends Exception {
    private static final long serialVersionUID = 1L;

    protected int errno;

    public FavoriteException(String e) {
        super(e);
    }

    public FavoriteException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }
}
