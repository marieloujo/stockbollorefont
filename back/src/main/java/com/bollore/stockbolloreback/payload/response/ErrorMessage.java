package com.bollore.stockbolloreback.payload.response;

import java.util.HashMap;

public class ErrorMessage {

    private HashMap<String, String> messages = new HashMap<String, String>();

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, String> messages) {
        this.messages = messages;
    }
}
