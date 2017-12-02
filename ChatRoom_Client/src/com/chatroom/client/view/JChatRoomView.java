package com.chatroom.client.view;

import com.chatroom.client.ChatClient;
import com.chatroom.client.DateUtils;
import com.chatroom.client.model.MessageBean;
import com.chatroom.client.model.UserBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
public class JChatRoomView extends JFrame {
    private ChatClient chatClient;
    private UserBean userBean;
    private UserBean friend;

    private JButton btn_submit;
    private JPanel panel_message;
    private JTextField edt_content;
    private GridBagLayout layout_gridbag;
    JChatRoomView(ChatClient chatClient, UserBean userBean, UserBean friend){
        this.chatClient = chatClient;
        this.userBean = userBean;
        this.friend = friend;
        init();
    }

    private void init(){

        btn_submit = new JButton("send");
        edt_content = new JTextField();
        panel_message = new JPanel();
        layout_gridbag = new GridBagLayout();
        panel_message.setLayout(new BoxLayout(panel_message,BoxLayout.Y_AXIS));

        btn_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width/3;
        int height = screenSize.height/2;
        this.setSize(width,height);
        //让窗口居中显示
        this.setLocation(screenSize.width/2-width/2,screenSize.height/2-height/2);
        this.setTitle(friend.getNickname());
        this.setLayout(layout_gridbag);

        this.add(panel_message);
        this.add(edt_content);
        this.add(btn_submit);

        GridBagConstraints constraints= new GridBagConstraints();//定义一个GridBagConstraints，
        //是用来控制添加进的组件的显示位置
        constraints.fill = GridBagConstraints.BOTH;
        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
        constraints.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        constraints.weightx = 1;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        constraints.weighty=1;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout_gridbag.setConstraints(panel_message, constraints);//设置组件

        constraints.gridwidth=1;
        constraints.weightx = 1;
        constraints.weighty=0;
        layout_gridbag.setConstraints(edt_content, constraints);
        constraints.gridwidth=1;
        constraints.weightx = 0;
        constraints.weighty=0;
        layout_gridbag.setConstraints(btn_submit, constraints);

    }

    private void sendMessage() {
        String text = edt_content.getText();
        if(text!=null && !"".equals(text)){

            MessageBean messageBean = new MessageBean();
            messageBean.setContent(text);
            messageBean.setMsgid(UUID.randomUUID().toString());
            messageBean.setSender(userBean.getUserid());
            messageBean.setReceiver(friend.getUserid());
            messageBean.setSendername(userBean.getNickname());
            messageBean.setSendtime(DateUtils.getTime());

            chatClient.message.sendMessage(messageBean);

            edt_content.setText("");
            edt_content.revalidate();
        }

    }

    public void onShow() {
        setVisible(true);
        getMessage();
    }

    public void getMessage(){
        chatClient.message.getMessage(userBean,friend);
    }

    public void setMessages(List<MessageBean> messages) {
       for (MessageBean msg:messages){
           System.out.println(msg.getContent());
           JLabel text = new JLabel(msg.getSendtime()+ " "+msg.getSendername()+" \n "+msg.getContent()+" \n ");
           panel_message.add(text);
       }
        panel_message.revalidate();
    }
    public static void main(String[] args){
        UserBean userBean = new UserBean();
        userBean.setNickname("张三");
        userBean.setUsername("zhangsan");
        userBean.setUserid(UUID.randomUUID().toString());
        userBean.setPassword("123");

        UserBean friend = new UserBean();
        friend.setNickname("李四");
        friend.setUsername("lisi");
        friend.setUserid(UUID.randomUUID().toString());
        friend.setPassword("123");
        JChatRoomView chatRoomView = new JChatRoomView(null,userBean,friend);
        chatRoomView.setVisible(true);
    }

    public void setMessage(MessageBean message) {

        String userid = userBean.getUserid();
        String friendid = friend.getUserid();

        boolean sendByMe = message.getSender().equals(userid) && message.getReceiver().equals(friendid);
        boolean sendByFriend = message.getSender().equals(friendid) && message.getReceiver().equals(userid);
        if(sendByMe || sendByFriend){
            JLabel text = new JLabel(message.getSendtime()+ " "+message.getSendername()+" \n "+message.getContent()+" \n ");
            panel_message.add(text);
            panel_message.revalidate();
        }

    }
}
