package com.pet.model;

public class Message {
    private String content;
    private String type;
    private String sender;
    private Integer currentSessions;
    public Message () {}

    public Message(String content, String type, String sender, Integer currentSessions) {
        this.content = content;
        this.type = type;
        this.sender = sender;
        this.currentSessions = currentSessions;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getCurrentSessions() {
        return currentSessions;
    }

    public void setCurrentSessions(Integer currentSessions) {
        this.currentSessions = currentSessions;
    }
}
