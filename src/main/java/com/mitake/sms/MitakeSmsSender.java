package com.mitake.sms;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MitakeSmsSender {
    private static final Logger LOG = LoggerFactory.getLogger(MitakeSmsSender.class);
    private static final String DEFAULT_URL = "http://smexpress.mitake.com.tw/SmSendGet.asp";

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_MESSAGE = "smbody";
    private static final String KEY_DESTINATION = "dstaddr";
    private static final String KEY_ENCODING = "encoding";
    private static final String KEY_SCHEDULE = "dlvtime";
    private static final String KEY_TIME_TO_LIVE = "vldtime";
    private static final String KEY_RESPONSE = "response";
    private static final String KEY_DEST_NAME = "DestName";
    private static final String KEY_CLIENT_ID = "ClientID";

    public MitakeSmsResult send(String to, String message) {
        InputStream is = null;
        HttpURLConnection conn = null;

        try {
            URL url = buildUrl(to, message);

            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            is = conn.getInputStream();

            ArrayList<String> lines = (ArrayList<String>) IOUtils.readLines(is, Charset.defaultCharset());

            StringBuffer res = new StringBuffer();

            for (String line : lines) {
                res.append(line).append(",");
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }

        return null;
    }

    private URL buildUrl(String to, String message) throws Exception {
        HashMap<String, String> maps = new HashMap<String, String>();

        maps.put(KEY_USERNAME, MitakeSms.getUsername());
        maps.put(KEY_PASSWORD, MitakeSms.getPassword());
        maps.put(KEY_DESTINATION, to);
        maps.put(KEY_ENCODING, "UTF8");
        maps.put(KEY_MESSAGE, encode(message, maps.get(KEY_ENCODING)));

        StringBuffer sb = new StringBuffer();

        for (Map.Entry<String, String> entry : maps.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        URL url = new URL(DEFAULT_URL + "?" + StringUtils.removeEnd(sb.toString(), "&"));

        return url;
    }

    private String encode(String message, String encoding) throws Exception {
        message = URLEncoder.encode(message, encoding);

        message = message.replace("+", "%20");

        return message;
    }
}