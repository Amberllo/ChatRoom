package com.chatroom.server.repository;

import com.chatroom.server.DBHelper;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class AbstractRepository {
    protected DBHelper dbHelper;
    public AbstractRepository(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }
}
