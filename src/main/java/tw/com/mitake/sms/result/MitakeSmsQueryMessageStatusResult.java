package tw.com.mitake.sms.result;

import tw.com.mitake.sms.ConnectionResult;

import java.util.ArrayList;

public class MitakeSmsQueryMessageStatusResult extends MitakeSmsResult {
    public MitakeSmsQueryMessageStatusResult(ArrayList<String> response) {
        parseResult(response);

        connectionResult = ConnectionResult.OK;
    }

    private void parseResult(ArrayList<String> response) {
        for (String line : response) {
            System.out.println(line);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("ConnectionResult: " + connectionResult.toString() + "\n");

        return sb.toString();
    }

}
