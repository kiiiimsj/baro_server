package com.wantchu.wantchu_server2.business.ImageReceivers;

import java.net.SocketException;

public class FileTransferException extends SocketException {
    private static final long serialVersionUID = 1L;

    public FileTransferException() {
    }

    public FileTransferException(String arg) {
        super(arg);
    }
}