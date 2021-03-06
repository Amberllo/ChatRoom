package com.chatroom.client.view;

import com.chatroom.client.ChatClient;
import com.chatroom.client.model.UserBean;
import com.chatroom.client.protocol.Protocol;
import com.chatroom.client.protocol.ProtocolResult;
import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class JLoginView extends JFrame {


    JTextField edt_username;
    JTextField edt_password;
    JButton button;
    ChatClient chatClient;
    public JLoginView(ChatClient chatClient){
        this.chatClient = chatClient;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setSize(200,200);
        //让窗口居中显示
        this.setLocation(screenSize.width/2-200/2,screenSize.height/2-200/2);


        JPanel contentPane=new JPanel();  
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        this.setContentPane(contentPane);  
        contentPane.setLayout(new GridLayout(3,1,5,5));

        JPanel panel_username = new JPanel();
        JPanel panel_password = new JPanel();
        JPanel panel_button = new JPanel();

        contentPane.add(panel_username);
        contentPane.add(panel_password);
        contentPane.add(panel_button);

        edt_username  =new JTextField();
        edt_username.setColumns(10);
        edt_password  =new JPasswordField();
        edt_password.setColumns(10);
        button = new JButton("登录");

        panel_username.add(new JLabel("姓名："));
        panel_username.add(edt_username);

        panel_password.add(new JLabel("密码："));
        panel_password.add(edt_password);
        panel_button.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = edt_username.getText();
                String password = edt_password.getText();

                chatClient.user.auth(username,password);
            }
        });
    }

    public void onShow(){
        setVisible(true);
    }

    public void onHide(){
        setVisible(false);
    }
}  