package com.wantchu.wantchu_server2.business.ImageReceivers;

import java.io.IOException;

interface FileTransferServerListener {
    public void clientConnection(FileTransferClient client);
    public void connectionError(IOException e);
    public void connectionClose();
}
