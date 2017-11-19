package com.chatroom.server;

import com.chatroom.server.model.UserBean;
import com.chatroom.server.repository.UserRepository;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by Administrator on 2017/11/18 0018.
 */
public class ChatServer {

    public void serverStart(){
        System.out.println("server run");
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
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

//    public static void main(String[] args){
//
//


//
//    }

    class ServerThread extends Thread {
        Socket socket;
        public ServerThread(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {

                PrintWriter wtr = new PrintWriter(socket.getOutputStream());
                BufferedReader rdr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = rdr.readLine();
                System.out.println("从客户端来的信息：" + line);
                wtr.println("你好，服务器已经收到您的信息！'" + line + "'\n");
                wtr.flush();

                DBHelper dbHelper = new DBHelper();
                dbHelper.openDB();
                UserRepository userRepository = new UserRepository(dbHelper);
                List<UserBean> friends = userRepository.getFriends("40cc998f-9b57-4bff-a1de-23c3de0cc785");
                for(UserBean friend:friends){
                    System.out.println("好友："+friend.getNickname()+" 状态: "+friend.getState().text);
                }




                System.out.println("已经返回给客户端！");

                dbHelper.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
