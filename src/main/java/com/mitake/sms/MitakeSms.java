package com.mitake.sms;

import org.apache.commons.lang3.StringUtils;

public class MitakeSms {
    private static String username;
    private static String password;
    private static MitakeSmsSender sender;

    public static void init(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("Please press username and password");
        }

        MitakeSms.username = username;
        MitakeSms.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static MitakeSmsResult send(String to, String message) {
        if (sender == null) {
            sender = new MitakeSmsSender();
        }

        return sender.send(to, message);
    }
}
