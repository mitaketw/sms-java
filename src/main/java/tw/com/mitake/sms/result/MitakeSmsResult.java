package tw.com.mitake.sms.result;

import tw.com.mitake.sms.ConnectionResult;

public class MitakeSmsResult {
    protected ConnectionResult connectionResult;

    public MitakeSmsResult() {
    }

    public MitakeSmsResult(ConnectionResult connectionResult) {
        this.connectionResult = connectionResult;
    }

    public ConnectionResult getConnectionResult() {
        return connectionResult;
    }
}