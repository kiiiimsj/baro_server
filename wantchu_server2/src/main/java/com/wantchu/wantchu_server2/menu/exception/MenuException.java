package com.wantchu.wantchu_server2.menu.exception;

public class MenuException extends Exception{
    private static final long serialVersionUID = 1L;

    protected int errno;

    public MenuException(String e) {
        super(e);
    }

    public MenuException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }
}
