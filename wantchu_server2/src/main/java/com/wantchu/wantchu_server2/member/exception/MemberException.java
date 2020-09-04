package com.wantchu.wantchu_server2.member.exception;

public abstract class MemberException extends Exception{
    private static final long serialVersionUID = 1L;

    protected int errno;

    public MemberException(String e) {
        super(e);
    }

    public MemberException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }
}
