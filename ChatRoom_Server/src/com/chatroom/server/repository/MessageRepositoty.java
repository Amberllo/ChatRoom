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

        String sql = "SELECT "+TABLE_MESSAGE+".*, t_user.nickname AS sendername FROM "+TABLE_MESSAGE+" \n" +
                " LEFT JOIN t_user ON "+TABLE_MESSAGE+".sender = t_user.userid " +
                " WHERE ( sender = '"+userid+"' AND receiver = '"+friendid+"' )" +
                " OR ( receiver = '"+userid+"' AND sender = '"+friendid+"' )" +
                " ORDER BY sendtime ASC";
        return getMessageBySql(sql);

    }

    public List<MessageBean> getGroupMessage(String groupid) {
        String sql = "select "+TABLE_MESSAGE+".*,t_user.nickname as sendername from "+TABLE_MESSAGE+" \n" +
                "LEFT JOIN t_group ON "+TABLE_MESSAGE+".receiver = t_group.groupid\n" +
                "LEFT JOIN t_user ON "+TABLE_MESSAGE+".sender = t_user.userid\n" +
                "where t_group.groupid = '"+groupid+"'";
        return getMessageBySql(sql);
    }

    private List<MessageBean> getMessageBySql(String sql) {
        List<MessageBean> messages = new ArrayList<>();
        PreparedStatement statement = dbHelper.execSql(sql);
        if(statement!=null){
            try {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    String msgid = set.getString("msgid");
                    String sender = set.getString("sender");
                    String receiver = set.getString("receiver");
                    String sendername = set.getString("sendername");
                    String sendtime = set.getString("sendtime");
                    String content = set.getString("content");

                    MessageBean messageBean = new MessageBean();
                    messageBean.setMsgid(msgid);
                    messageBean.setSender(sender);
                    messageBean.setSendtime(sendtime);
                    messageBean.setReceiver(receiver);
                    messageBean.setSendername(sendername);
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

    public void sendMessage(MessageBean messageBean) throws SQLException{
        String sql = "insert into "+TABLE_MESSAGE+" (sender,sendtime,receiver,content) VALUE(?,?,?,?)";
        PreparedStatement pstmt = (PreparedStatement) dbHelper.conn.prepareStatement(sql);
        pstmt.setString(1, messageBean.getSender());
        pstmt.setString(2, messageBean.getSendtime());
        pstmt.setString(3, messageBean.getReceiver());
        pstmt.setString(4, messageBean.getContent());
        pstmt.executeUpdate();
        pstmt.close();

    }


}
