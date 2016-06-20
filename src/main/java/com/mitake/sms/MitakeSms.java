package com.mitake.sms;

import com.mitake.sms.listener.OnPostSendListener;
import com.mitake.sms.listener.OnPreSendListener;
import org.apache.commons.lang3.StringUtils;

public class MitakeSms {
    private static String username;
    private static String password;
    private static MitakeSmsSender sender;
    private static boolean init;

    private MitakeSms() {
    }

    public static void init(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            init = false;

            throw new IllegalArgumentException("Please press username and password");
        }

        MitakeSms.username = username;
        MitakeSms.password = password;

        init = true;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static MitakeSmsResult send(String to, String message) {
        return send(to, message, null, null);
    }

    public static MitakeSmsResult send(String to, String message, OnPreSendListener listener) {
        return send(to, message, listener, null);
    }

    public static MitakeSmsResult send(String to, String message, OnPostSendListener listener) {
        return send(to, message, null, listener);
    }

    public static MitakeSmsResult send(String to, String message, OnPreSendListener preListener, OnPostSendListener postListener) {
        if (!init) {
            throw new RuntimeException("Init first");
        }

        if (sender == null) {
            sender = new MitakeSmsSender();
        }

        if (preListener != null) {
            preListener.onPreSend();
        }

        MitakeSmsResult mitakeSmsResult = sender.send(to, message);

        if (postListener != null) {
            postListener.onPostSend();
        }

        return mitakeSmsResult;
    }
}
