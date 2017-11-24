package com.chatroom.client;

import com.chatroom.client.protocol.Protocol;
import com.chatroom.client.view.JLoginView;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2017/11/18 0018.
 */
public class ChatClient {
    Socket socket;
    public  void showLoginView(){
        new JLoginView(this);
    }

    public void connect() throws IOException {
        socket  = new Socket("127.0.0.1",7777);
    }

    public void write(String json) {


        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
