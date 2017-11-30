package com.chatroom.server.repository;

import com.chatroom.server.DBHelper;
import com.chatroom.server.model.MessageBean;
import com.chatroom.server.model.UserBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
public class MessageRepositoty extends AbstractRepository{

    private final static String TABLE_MESSAGE = "t_message";
    public MessageRepositoty(DBHelper dbHelper) {
        super(dbHelper);
    }

    public List<MessageBean> getMessage(String userid, String friendid) {
        List<MessageBean> messages = new ArrayList<>();
        String sql = "SELECT t_message.*, t_user.nickname AS receivername FROM t_message " +
                " LEFT JOIN t_user ON t_message.receiver = t_user.userid " +
                " WHERE ( sender = '"+userid+"' AND receiver = '"+friendid+"' )" +
                " OR ( receiver = '"+userid+"' AND sender = '"+friendid+"' )" +
                " ORDER BY sendtime ASC";

        PreparedStatement statement = dbHelper.execSql(sql);
        if(statement!=null){
            try {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    String msgid = set.getString("msgid");
                    String sender = set.getString("sender");
                    String receiver = set.getString("receiver");
                    String receivername = set.getString("receivername");
                    String sendtime = set.getString("sendtime");
                    String content = set.getString("content");

                    MessageBean messageBean = new MessageBean();
                    messageBean.setMsgid(msgid);
                    messageBean.setSender(sender);
                    messageBean.setSendtime(sendtime);
                    messageBean.setReceiver(receiver);
                    messageBean.setReceivername(receivername);
                    messageBean.setContent(content);
                    messages.add(messageBean);
                }//显示数据
                set.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return messages;
    }
}
