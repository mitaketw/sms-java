package tw.com.mitake.sms.result;

import tw.com.mitake.sms.ConnectionResult;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MitakeSmsQueryResult extends MitakeSmsResult {
    private static final Pattern ACCOUNT_POINT_PATTERN = Pattern.compile("AccountPoint=(\\d+)");

    private int accountPoint;

    public MitakeSmsQueryResult(ArrayList<String> response) {
        parseResult(response);

        connectionResult = ConnectionResult.OK;
    }

    private void parseResult(ArrayList<String> response) {
        for (String line : response) {
            Matcher accountPointMatcher = ACCOUNT_POINT_PATTERN.matcher(line);

            if (accountPointMatcher.find()) {
                accountPoint = Integer.valueOf(accountPointMatcher.group(1));

                break;
            }
        }
    }

    public int getAccountPoint() {
        return accountPoint;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("ConnectionResult: " + connectionResult.toString() + "\n");

        sb.append("accountPoint: ").append(accountPoint);

        return sb.toString();
    }
}