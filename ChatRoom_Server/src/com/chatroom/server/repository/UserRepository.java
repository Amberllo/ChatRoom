package com.chatroom.server.repository;

import com.chatroom.server.DBHelper;
import com.chatroom.server.model.UserBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class UserRepository extends AbstractRepository{
    private static final String TABLE_USER = "t_user";
    private static final String TABLE_USER_STATE = "t_user_state";



    public UserRepository(DBHelper dbHelper){
        super(dbHelper);
    }

    public List<UserBean> getFriends(String uid){
        List<UserBean> friends = new ArrayList<>();
        String sql = "SELECT "+TABLE_USER+".*, "+TABLE_USER_STATE+".state FROM "+TABLE_USER+"\n" +
                "LEFT JOIN "+TABLE_USER_STATE+" ON "+TABLE_USER+".userid = "+TABLE_USER_STATE+".userid \n" +
                "WHERE t_user.userid NOT IN ( '"+uid+"' ) ORDER BY username";
        PreparedStatement statement = dbHelper.execSql(sql);
        if(statement!=null){
            try {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    String userid = set.getString("userid");
                    String username = set.getString("username");
                    String password = set.getString("password");
                    String nickname = set.getString("nickname");
                    int state = set.getInt("state");
                    UserBean userBean = new UserBean();
                    userBean.setUserid(userid);
                    userBean.setNickname(nickname);
                    userBean.setPassword(password);
                    userBean.setUsername(username);
                    userBean.setState(state==0? UserBean.UserState.Offline: UserBean.UserState.Online);
                    friends.add(userBean);

                }//显示数据
                set.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return friends;

    }

    public UserBean auth(String username, String password) {
        String sql = "select * from t_user where username = '"+username+"' and password = '"+password+"'";
        UserBean userBean = null;
        PreparedStatement statement = dbHelper.execSql(sql);
        if(statement!=null){
            try {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    String _userid = set.getString("userid");
                    String _username = set.getString("username");
                    String _password = set.getString("password");
                    String _nickname = set.getString("nickname");
                    userBean = new UserBean();
                    userBean.setUserid(_userid);
                    userBean.setNickname(_nickname);
                    userBean.setPassword(_password);
                    userBean.setUsername(_username);

                }//显示数据
                set.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return userBean;
    }

    public void userState(String userid,boolean online) {
        String sql = "update "+TABLE_USER_STATE+" SET state=? where userid = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) dbHelper.conn.prepareStatement(sql);
            pstmt.setInt(1, online?1:0);
            pstmt.setString(2, userid);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
