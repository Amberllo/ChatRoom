package com.chatroom.client.model;

public enum UserState{
        Online("在线"),Offline("离线");
        public String text;
        UserState(String text) {
            this.text = text;
        }
    }