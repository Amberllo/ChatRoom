package com.chatroom.server;

import com.chatroom.server.model.UserBean;
import com.chatroom.server.protocol.Protocol;
import com.chatroom.server.protocol.ProtocolResult;
import com.chatroom.server.repository.UserRepository;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Administrator on 2017/11/18 0018.
 */
public class ChatServer {

    private String host = "127.0.0.1";
    private int port = 7777;

    public void serverStart(){
        System.out.println("server run");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                //阻塞，等待客户端连接
                Socket socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    class ServerThread extends Thread {
        Socket socket;
        Protocol protocol;
        public ServerThread(Socket socket) {
            this.socket = socket;
            protocol = new Protocol();
        }

        public void run() {
            try {
                while (socket != null && !socket.isClosed()) {



                    InputStream is = socket.getInputStream();
                    if (is.available() > 0) {

                        String json = readStream(socket.getInputStream());
                        System.out.println("request data :"+json);
                        ProtocolResult result = protocol.doPost(json);
                        if(result!=null){
                            write(socket.getOutputStream(),result.toJson());
                        }else{
                            write(socket.getOutputStream(),ProtocolResult.unknowResult());
                        }
                    }
                    sleep(2000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


    /**
     * @功能 读取流
     * @param inStream
     * @return 字节数组
     * @throws Exception
     */
    public static String readStream(InputStream inStream) throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        String json = new String(b,"UTF-8");
        return json;
    }


    public void write(OutputStream outputStream,String json) {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
