package com.mitake.sms;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SmsSender {
    private static final Logger LOG = LoggerFactory.getLogger(SmsSender.class);
    private static final String DEFAULT_URL = "http://smexpress.mitake.com.tw/SmSendGet.asp";

    /* Result key value pair index */
    private static final int PAIR_KEY_INDEX = 0;
    private static final int PAIR_VALUE_INDEX = 1;

    /* Result keys */
    private static final String KEY_RESULT_MSG_ID = "msgid";
    private static final String KEY_RESULT_STATUS_CODE = "statuscode";
    private static final String KEY_RESULT_OVERAGE = "AccountPoint";
    private static final String KEY_RESULT_IS_DUPLICATE = "Duplicate";

    public SmsResult send(Sms sms) {
        String query = sms.buildQuery();
        StringBuffer res = new StringBuffer();
        HttpURLConnection conn = null;
        InputStream is = null;

        try {
            URL url = new URL(DEFAULT_URL + query);

            conn = (HttpURLConnection) url.openConnection();

            // optional default is GET
            conn.setRequestMethod("GET");

            is = conn.getInputStream();

            ArrayList<String> lines = (ArrayList<String>) IOUtils.readLines(is, Charset.defaultCharset());

            for (String line : lines) {
                res.append(line).append(",");
            }

            LOG.debug("===== SmsResult ===== : " + res);
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

        // print result
        return parseResponse(res.toString());
    }

    private SmsResult parseResponse(String result) {
        return null;
    }
}