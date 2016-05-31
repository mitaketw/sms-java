package com.mitake.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class SmsSender {
    private static final Logger LOG = LoggerFactory.getLogger(SmsSender.class);
    private static final String URL = "http://smexpress.mitake.com.tw/SmSendGet.asp?";

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
        StringBuffer response = new StringBuffer();
        HttpURLConnection con = null;
        InputStream is = null;

        try {
            URL obj = new URL(URL + query);

            con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            is = con.getInputStream();

            ArrayList<String> lines = (ArrayList<String>) IOUtils.readLines(is);

            for (String line : lines) {
                response.append(line).append(",");
            }

            LOG.debug("===== SmsResult ===== : " + response);
        } catch (Exception e) {
            LOG.error(Constants.EXCEPTION_PREFIX, e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (con != null) {
                    con.disconnect();
                }
            } catch (IOException e) {
                LOG.error(Constants.EXCEPTION_PREFIX, e);
            }
        }

        // print result
        return parserResult(response.toString());
    }

    public SmsResult parserResult(String data) {
        SmsResult result = new SmsResult();

        result.addAllSmsResponse(result.createSmsResponses(data));
        result.setStatusCode(SmsResult.STATUS_CODE_OK);
        result.setSender(SmsDriver.MITAKE.toString());

        for (CHTSmsResult.SmsResponse smsResponse : result.getSmsResponses()) {
            if (!smsResponse.getStatus().equals(
                    SmsResult.SmsStatus.Success.getKey())) {
                result.setStatusCode(SmsResult.SmsStatus.Fail.getKey());
                break;
            }
        }

        return result;
    }
}
