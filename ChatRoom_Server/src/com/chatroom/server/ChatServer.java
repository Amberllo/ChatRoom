package com.chatroom.server;

import com.chatroom.server.model.UserBean;
import com.chatroom.server.protocol.Protocol;
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

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                while (socket != null && !socket.isClosed()) {



                    InputStream is = socket.getInputStream();
                    if (is.available() > 0) {

                        byte[] data = readStream(socket.getInputStream());
                        String json = new String(data,"UTF-8");
                        System.out.println(json);
                        Protocol.doPost(json);
                    }
                    System.out.println("server is waiting ...");
                    sleep(2000);
                }


//                DBHelper dbHelper = new DBHelper();
//                dbHelper.openDB();
//                UserRepository userRepository = new UserRepository(dbHelper);
//                List<UserBean> friends = userRepository.getFriends("40cc998f-9b57-4bff-a1de-23c3de0cc785");
//                for(UserBean friend:friends){
//                    System.out.println("好友："+friend.getNickname()+" 状态: "+friend.getState().text);
//                }
//                dbHelper.close();
//                System.out.println("已经返回给客户端！");


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
    public static byte[] readStream(InputStream inStream) throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        return b;
    }


}
