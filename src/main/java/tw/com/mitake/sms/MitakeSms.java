package tw.com.mitake.sms;

import org.apache.commons.lang3.StringUtils;
import tw.com.mitake.sms.listener.OnPostSendListener;
import tw.com.mitake.sms.listener.OnPreSendListener;
import tw.com.mitake.sms.result.MitakeSmsQueryAccountPointResult;
import tw.com.mitake.sms.result.MitakeSmsQueryMessageStatusResult;
import tw.com.mitake.sms.result.MitakeSmsSendResult;

public class MitakeSms {
    private static String username;
    private static String password;
    private static MitakeSmsSender sender = new MitakeSmsSender();
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

    public static MitakeSmsSendResult send(String to, String message) {
        return send(to, message, null, null);
    }

    public static MitakeSmsSendResult send(String to, String message, OnPreSendListener listener) {
        return send(to, message, listener, null);
    }

    public static MitakeSmsSendResult send(String to, String message, OnPostSendListener listener) {
        return send(to, message, null, listener);
    }

    public static MitakeSmsSendResult send(String to, String message, OnPreSendListener preListener, OnPostSendListener postListener) {
        if (!init) {
            throw new RuntimeException("Init first");
        }

        if (preListener != null) {
            preListener.onPreSend();
        }

        MitakeSmsSendResult result = sender.send(to, message);

        if (postListener != null) {
            postListener.onPostSend();
        }

        return result;
    }

    public static MitakeSmsQueryAccountPointResult queryAccountPoint() {
        if (!init) {
            throw new RuntimeException("Init first");
        }

        MitakeSmsQueryAccountPointResult result = sender.queryAccountPoint();

        return result;
    }

    public static MitakeSmsQueryMessageStatusResult queryMessageStatus(String messageId) {
        if (!init) {
            throw new RuntimeException("Init first");
        }

        MitakeSmsQueryMessageStatusResult result = sender.queryMessageStatus(messageId);

        return result;
    }
}