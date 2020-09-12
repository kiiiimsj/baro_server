package com.wantchu.wantchu_server2.event.exception;

public class EventException extends Exception{
    private static final long serialVersionUID = 1L;

    protected int errno;

    public EventException(String e){super(e);}

    public EventException(int errno, String e){
        this(e);
        this.errno = errno;
    }
    public int getErrno(){ return errno;}
}
