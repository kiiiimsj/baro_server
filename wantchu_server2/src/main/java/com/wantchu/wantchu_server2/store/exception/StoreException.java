package com.wantchu.wantchu_server2.store.exception;

public abstract class StoreException extends Exception {
    private static final long serialVersionUID = 1L;

    protected int errno;

    public StoreException(String e) {
        super(e);
    }

    public StoreException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }
}
