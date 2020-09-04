package com.wantchu.wantchu_server2.notice.exception;

public abstract class NoticeException extends Exception {

    private static final long serialVersionUID = 1L;

    protected int errno;

    public NoticeException(String e) {super(e);}

    public NoticeException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {return errno;}
}
