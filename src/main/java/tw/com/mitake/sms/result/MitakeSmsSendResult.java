package tw.com.mitake.sms.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.mitake.sms.ConnectionResult;
import tw.com.mitake.sms.StatusCode;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MitakeSmsSendResult extends MitakeSmsResult {
    private static final Logger LOG = LoggerFactory.getLogger(MitakeSmsSendResult.class);
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("\\[(\\d+)\\]");
    private static final Pattern FIELD_PATTERN = Pattern.compile("(\\w+)=([\\w\\p{InCJKUnifiedIdeographs}]+)");
    private static final Pattern ACCOUNT_POINT_PATTERN = Pattern.compile("AccountPoint=(\\d+)");
    private static final String MSGID = "msgid";
    private static final String STATUSCODE = "statuscode";

    private ArrayList<SmsResult> results;
    private int accountPoint;

    public MitakeSmsSendResult(ArrayList<String> response, String to) {
        parseResult(response, to);

        connectionResult = ConnectionResult.OK;
    }

    private void parseResult(ArrayList<String> response, String to) {
        results = new ArrayList<SmsResult>();

        SmsResult result = null;

        for (String line : response) {
            Matcher phoneNumberMatcher = PHONE_NUMBER_PATTERN.matcher(line);

            if (phoneNumberMatcher.find()) {
                result = new SmsResult();

                results.add(result);

                if (phoneNumberMatcher.group(1).length() != 10) {
                    result.phoneNumber = to;
                } else {
                    result.phoneNumber = phoneNumberMatcher.group(1);
                }
            }

            Matcher fieldMatcher = FIELD_PATTERN.matcher(line);

            if (fieldMatcher.find()) {
                String group1Value = fieldMatcher.group(1);
                String group2Value = fieldMatcher.group(2);

                if (group1Value.equals(MSGID)) {
                    result.messageId = group2Value;
                }

                if (group1Value.equals(STATUSCODE)) {
                    result.statusCode = StatusCode.findByKey(group2Value);
                }
            }

            Matcher accountPointMatcher = ACCOUNT_POINT_PATTERN.matcher(line);

            if (accountPointMatcher.find()) {
                accountPoint = Integer.valueOf(accountPointMatcher.group(1));
            }
        }
    }

    public ArrayList<SmsResult> getResults() {
        return results;
    }

    public int getAccountPoint() {
        return accountPoint;
    }

    public class SmsResult {
        private String phoneNumber;
        private String messageId;
        private StatusCode statusCode;

        @Override
        public String toString() {
            return "phoneNumber: " + phoneNumber + ", messageId: " + messageId + ", statusCode: " + statusCode.getMessage();
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("ConnectionResult: " + connectionResult.toString() + "\n");

        for (SmsResult result : results) {
            sb.append(result.toString()).append("\n");
        }

        sb.append("accountPoint: ").append(accountPoint);

        return sb.toString();
    }
}