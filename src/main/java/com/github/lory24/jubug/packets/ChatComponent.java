package com.github.lory24.jubug.packets;

import lombok.Getter;

public class ChatComponent {
    @Getter private final String text;

    public ChatComponent(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        String jsonChatComponent = "{\"text\":\"%s\"}";
        return String.format(jsonChatComponent, text);
    }
}
