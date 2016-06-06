package com.mitake.sms;

import org.apache.commons.io.Charsets;
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

            ArrayList<String> response = (ArrayList<String>) IOUtils.readLines(is, "big5");

            LOG.debug("response: {}", response);

            return parseResult(response, to);
        } catch (Exception e) {
            LOG.error(e.getMessage());

            return null;
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
    }

    private MitakeSmsResult parseResult(ArrayList<String> response, String to) {
        return new MitakeSmsResult(response, to);
    }

    private URL buildUrl(String to, String message) throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();

        map.put(KEY_USERNAME, MitakeSms.getUsername());
        map.put(KEY_PASSWORD, MitakeSms.getPassword());
        map.put(KEY_DESTINATION, to);
        map.put(KEY_ENCODING, "UTF8");
        map.put(KEY_MESSAGE, encode(message, map.get(KEY_ENCODING)));

        StringBuffer sb = new StringBuffer();

        for (Map.Entry<String, String> entry : map.entrySet()) {
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