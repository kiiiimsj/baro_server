package com.wantchu.wantchu_server2.alert.exception;

public class AlertException extends Exception {
    private static final long serialVersionUID = 1L;

    protected  int errno;

    public AlertException(String e){ super(e); }

    public AlertException(int errno, String e){
        this(e);
        this.errno = errno;
    }

    public int getErrno() { return errno; }
}
