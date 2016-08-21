package tw.com.mitake.sms.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.mitake.sms.ConnectionResult;
import tw.com.mitake.sms.StatusCode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MitakeSmsQueryMessageStatusResult extends MitakeSmsResult {
    private static final Logger LOG = LoggerFactory.getLogger(MitakeSmsQueryMessageStatusResult.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    private String messageId;
    private StatusCode statusCode;
    private Date date;

    public MitakeSmsQueryMessageStatusResult(ArrayList<String> response) {
        parseResult(response);

        connectionResult = ConnectionResult.OK;
    }

    private void parseResult(ArrayList<String> response) {
        for (String line : response) {
            try {
                String[] parts = line.split("\\t");

                messageId = parts[0];
                statusCode = StatusCode.findByKey(parts[1]);
                date = SDF.parse(parts[2]);

                break;
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }

    public String getMessageId() {
        return messageId;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("ConnectionResult: " + connectionResult.toString() + "\n");

        sb.append("messageId: ").append(messageId)
                .append(", statusCode: ").append(statusCode.getMessage())
                .append(", date: ").append(SDF.format(date));

        return sb.toString();
    }
}
