package com.jordanschnur.deathmessagesplus.logging;

import java.util.HashMap;
import java.util.Map;

public class LoggingContext {
    private Map<String, String> context = new HashMap<String, String>();

    public LoggingContext() {}

    public LoggingContext(Map<String, String> context) {
        this.context = context;
    }

    public String formatContext() {
        StringBuilder output = new StringBuilder();
        output.append("  {\n");
        for (Map.Entry<String, String> entry : context.entrySet()) {
            output
                    .append("    \"")
                    .append(entry.getKey())
                    .append("\": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        output.append("  }");

        return output.toString();
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void addContext(String key, String value) {
        context.put(key, value);
    }

    public void addContext(String key, int value) {
        context.put(key, Integer.toString(value));
    }

    public void addContext(String key, Enum value) {
        context.put(key, value.toString());
    }

    public <T> void addContext(String key, T value) {
        context.put(key, value.toString());
    }

    public void addContext(Map<String, String> context) {
        this.context.putAll(context);
    }

    public void removeContext(String key) {
        context.remove(key);
    }
}
