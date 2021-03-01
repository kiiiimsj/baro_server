package com.wantchu.wantchu_server2.business.ImageReceivers;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Stack;

public class FileTransferClient extends Socket implements Runnable{
    private Thread thread = null;
    private InputStream receiver = null;
    private OutputStream sender = null;
    private Stack<Exception> errorList = null;
    private FileTransferAddress address = null;
    private File savefile = null;
    private FileTransferListener listener = null;
    protected FileTransferClient(File savefile,int processSize) throws IOException{
        this.savefile = savefile;
    }
    /**
     * 서버 시작 메소드(보내기, 받기 스트림을 선언한다)
     */
    protected void serverStart() throws IOException {
        thread = new Thread(this);
        receiver = getInputStream();
        sender = getOutputStream();
        errorList = new Stack<Exception>();
        thread.start();
    }
    /**
     * 생성자
     */
    public FileTransferClient(FileTransferAddress address, File savefile) throws IOException{
        this.savefile = savefile;
        this.address = address;
        thread = new Thread(this);
        errorList = new Stack<Exception>();
    }
    /**
     * 접속 메소드
     */
    private void connection() throws IOException{
        super.connect(address);
        receiver = getInputStream();
        sender = getOutputStream();
        thread.start();
    }
    /**
     * 접속 커넥션 종료 메소드
     */
    @Override
    public void close() throws IOException{
        super.close();
        if(listener != null){
            listener.connectionClose();
        }
    }
    /**
     * 리스너 등록
     */
    public void setFileTransferListener(FileTransferListener listener){
        this.listener = listener;
    }
    /**
     * 실행 메소드
     */
    @Override
    public void run() {
        byte[] lengthData = null;
        int length = 0;
        String filename = "";
        FileOutputStream out = null;
        try{
            lengthData = new byte[FileTransferBitConverter.INTBITSIZE];
//파일이름 사이즈를 받는다.
            receiver.read(lengthData,0,lengthData.length);
            length = FileTransferBitConverter.toInt32(lengthData, 0);
//파일 사이즈가 없으면 종료한다.
            if(length == 0){
                return;
            }
// 다운로드 시작 리스너 호출(이벤트 형식)
            if(listener != null){
                listener.downloadStart();
            }
// 파일 이름 설정
            byte[] filenamebyte = new byte[length];
            receiver.read(filenamebyte,0,filenamebyte.length);
            filename = new String(filenamebyte);
            File file = new File(savefile.getPath() + "\\" + filename);
//파일이 있으면 삭제
            if(file.exists()) file.delete();
            out = new FileOutputStream(file);
//파일 사이즈를 받는다.
            receiver.read(lengthData,0,lengthData.length);
            length = FileTransferBitConverter.toInt32(lengthData, 0);
//파일 사이즈가 없으면 종료
            if(length == 0){
                return;
            }
//파일 받기 시작
            receiveWrite(out,length,listener);
// 다운로드 종료 리스너 호출(이벤트 형식)
            if(listener != null){
                listener.downloadComplate();
                listener.fileSaveComplate(savefile.getPath() + "\\" + filename);
            }
        } catch (Exception e) {
// 에러가 발생하면 에러 리스너 호출
            if(listener != null){
                listener.receiveError(e);
            }
            errorList.push(e);
        } finally{
            try{
                if(isConnected()){
                    close();
                }
                if(out != null){
                    out.close();
                }
            }catch(Exception ex){
                if(listener != null){
                    listener.receiveError(ex);
                }
                errorList.push(ex);
            }
        }
    }
    public FileTransferAddress getAddress(){
        return this.address;
    }
    /**
     * 파일 전송 메소드
     */
    public void sendFile(File file) throws FileTransferException,IOException{
// 서버 접속
        connection();
// 파라미터 체크
        if(file == null) {
            throw new FileTransferException("File path not setting");
        }
// 전송 파일 체크
        if(!file.isFile()) {
            throw new FileTransferException("File path not setting");
        }
// 접속 체크
        if(!isConnected()) {
            throw new FileTransferException("Socket is closed");
        }
//파일 이름 체크
        String filename = file.getName();
        if(filename == null){
            throw new FileTransferException("File path not setting");
        }
        FileInputStream in = null;
        byte[] databyte = null;
        byte[] filenamebyte = filename.getBytes();
        try{
// 리스너 업로드 개시 호출
            if(listener != null){
                listener.uploadStart();
            }
            in = new FileInputStream(file);
            byte[] length = FileTransferBitConverter.getBytes(filenamebyte.length);
//파일 이름 사이즈 전송
            sender.write(length,0,FileTransferBitConverter.INTBITSIZE);
//파일 이름 전송
            sender.write(filenamebyte,0,filenamebyte.length);
//파일 사이즈 전송
            length = FileTransferBitConverter.getBytes((int)file.length());
            sender.write(length,0,FileTransferBitConverter.INTBITSIZE);
//파일 전송
            databyte = new byte[(int)file.length()];
            in.read(databyte,0,databyte.length);
            sender.write(databyte,0,databyte.length);
// 리스너 파일 사이즈 호출(이벤트 형식)
            if(listener != null){
                listener.progressFileSizeAction(databyte.length,filenamebyte.length);
            }
// 리스너 업로드 완료 호출
            if(listener != null){
                listener.uploadComplate();
            }
        }catch(IOException e){
            throw e;
        }finally{
            in.close();
        }
    }
    /**
     * 파일 수신 메소드
     */
    private void receiveWrite(FileOutputStream out,int length,FileTransferListener listener)
            throws Exception{
//커넥션 체크
        if(isClosed()) {
            throw new SocketException("socket closed");
        }
        if(!isConnected()) {
            throw new SocketException("socket diconnection");
        }
        byte[] buffer = new byte[4096];
        int progressCount = 0;
        while(progressCount < length){
            int bufferSize = 0;
            while((bufferSize = receiver.read(buffer)) > 0){
                out.write(buffer, 0, bufferSize);
                progressCount += bufferSize;
// 리스너 파일 수신 진행율 호출
                if(listener != null){
                    listener.progressFileSizeAction(progressCount,length);
                }
                if(progressCount >= length){
                    break;
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
}