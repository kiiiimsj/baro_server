package com.wantchu.wantchu_server2.extra.exception;

public abstract class ExtraException extends Exception {
    private static final long serialVersionUID = 1L;

    protected int errno;

    public ExtraException(String e) {
        super(e);
    }

    public ExtraException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }
}
