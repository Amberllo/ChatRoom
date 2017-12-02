package com.chatroom.server.core;

import com.chatroom.server.model.BroadcastPacket;
import com.chatroom.server.protocol.Protocol;
import com.chatroom.server.protocol.ProtocolResult;
import com.google.gson.Gson;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.*;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientThread extends Thread {
    public Socket socket;
    public Protocol protocol;

    ConcurrentLinkedQueue<BroadcastPacket> queue = new ConcurrentLinkedQueue<>();

    public ClientThread(ChatServer server,Socket socket) {
        this.socket = socket;
        protocol = new Protocol(server);
    }

    public void run() {
        try {
            while (socket != null && !socket.isClosed()) {


                while (!queue.isEmpty()) {
                    BroadcastPacket packet = queue.poll();
                    write(socket.getOutputStream(),packet.toJson());
                }

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
                sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

        /**
         * @功能 读取流
         * @param inStream
         * @return 字节数组
         * @throws Exception
         */
        public String readStream(InputStream inStream) throws Exception {
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

    public void onBroadcast(String resource, String actin, Object data) {
        BroadcastPacket broadcast = new BroadcastPacket();
        broadcast.setResource(resource);
        broadcast.actin = actin;
        broadcast.resultParams = data;
        queue.add(broadcast);
    }
}
