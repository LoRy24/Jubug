package com.github.lory24.jubug.packets;

public class ChatComponent {
    private final String text;
    private String extraJson;

    public ChatComponent(String text) {
        this.text = text;
    }

    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
    }

    @Override
    public String toString() {
        String jsonChatComponent = "{%s%s}";
        return String.format(jsonChatComponent, text, extraJson == null ? "" : "," + extraJson);
    }
}
