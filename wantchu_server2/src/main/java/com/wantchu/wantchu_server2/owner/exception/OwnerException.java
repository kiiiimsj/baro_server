package com.wantchu.wantchu_server2.owner.exception;

public abstract class OwnerException extends Exception {

    private static final long serialVersionUID = 1L;

    protected int errno;

    public OwnerException(String e) {super(e);}
    public OwnerException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }
}
