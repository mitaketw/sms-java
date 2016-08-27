package tw.com.mitake.sms.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.mitake.sms.ConnectionResult;
import tw.com.mitake.sms.MitakeSmsSender;
import tw.com.mitake.sms.StatusCode;

import java.util.ArrayList;
import java.util.Date;

public class MitakeSmsQueryMessageStatusResult extends MitakeSmsResult {
    private static final Logger LOG = LoggerFactory.getLogger(MitakeSmsQueryMessageStatusResult.class);

    private ArrayList<QueryMessageStatusResult> results = new ArrayList<QueryMessageStatusResult>();

    public MitakeSmsQueryMessageStatusResult(ArrayList<String> response) {
        parseResult(response);

        connectionResult = ConnectionResult.OK;
    }

    private void parseResult(ArrayList<String> response) {
        for (String line : response) {
            try {
                String[] parts = line.split("\\t");

                QueryMessageStatusResult result = new QueryMessageStatusResult();

                result.messageId = parts[0];
                result.statusCode = StatusCode.findByKey(parts[1]);
                result.date = MitakeSmsSender.SDF.parse(parts[2]);

                results.add(result);
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }

    public ArrayList<QueryMessageStatusResult> getResults() {
        return results;
    }

    public class QueryMessageStatusResult {
        private String messageId;
        private StatusCode statusCode;
        private Date date;

        @Override
        public String toString() {
            return "messageId: " + messageId + ", statusCode: " + statusCode.getMessage() + ", date: " + MitakeSmsSender.SDF.format(date);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("ConnectionResult: " + connectionResult.toString() + "\n");

        for (QueryMessageStatusResult result : results) {
            sb.append(result.toString()).append("\n");
        }

        return sb.toString();
    }
}
