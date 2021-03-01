package com.wantchu.wantchu_server2.business.ImageReceivers;


import autovalue.shaded.com.google$.common.base.$Throwables;

import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;

public class FileTransferServer extends ServerSocket implements Runnable{
    private ArrayList<FileTransferClient> clients = null;
    private Thread thread = null;
    private Stack<Exception> errorList = null;
    private File savePath = null;
    private int processSize = 1;
    private FileTransferServerListener listener = null;
    public FileTransferServer(int port,File savePath) throws IOException,FileTransferException{
        this(port,savePath,1);
    }
    /**
     * 생성자
     */
    public FileTransferServer(int port,File savePath,int processSize) throws IOException,FileTransferException, BindException {
        super(port);
        this.savePath = savePath;
        if(!savePath.isDirectory() || !savePath.exists()){
            throw new FileTransferException("File Path wrong!");
        }
        errorList = new Stack<Exception>();
        clients = new ArrayList<FileTransferClient>();
        this.processSize = processSize;
        this.start();
    }
    /**
     * 서버 개시
     */
    public void start() throws FileTransferException{
        thread = new Thread(this);
        thread.start();
    }
    public File getFile(){
        return this.savePath;
    }
    public void setFileTransferServerListener(FileTransferServerListener listener){
        this.listener = listener;
    }
    /**
     * 클라이언트가 접속을 할 때 실행되는 메소드
     */
    public FileTransferClient accept() throws FileTransferException,IOException{
        if (isClosed())
            throw new FileTransferException("Socket is closed");
        if (!isBound())
            throw new FileTransferException("Socket is not bound yet");
        if(this.savePath == null)
            throw new FileTransferException("file path wrong!");
        FileTransferClient s = new FileTransferClient(this.savePath,this.processSize);
        implAccept(s);
        s.serverStart();
        return s;
    }
    /**
     * 멀티 스레드 환경
     */
    @Override
    public void run(){
        while(true){
            try{
                FileTransferClient client = this.accept();
                clients.add(client);
                if(this.listener != null){
                    this.listener.clientConnection(client);
                }
            }catch(IOException e){
                errorList.push(e);
                if(this.listener != null){
                    this.listener.connectionError(e);
                }
            }
        }
    }
    public Exception getLastError(){
        if(errorList.size() > 0){
            Exception e = errorList.pop();
            return e;
        }else{
            return null;
        }
    }
    /**
     * 서버 종료
     */
    public void close() throws IOException{
        if (clients != null) {
            for (Socket client : clients) {
                client.close();
            }
        }
        super.close();
        if(this.listener != null){
            this.listener.connectionClose();
        }
    }
}
