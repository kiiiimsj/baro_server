package com.wantchu.wantchu_server2.type.exception;

public class TypeException extends Exception {

    private static final long serialVersionUID = 1L;

    protected int errno;

    public TypeException(String e) {super(e);}

    public TypeException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {return errno;}
}
