package com.wantchu.wantchu_server2.manage.exception;

public class ManageException extends Exception{
    private static final long serialVersionUID = 1L;

    protected int errno;

    public ManageException(String e) { super(e); }
    public ManageException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {return errno; }

}
