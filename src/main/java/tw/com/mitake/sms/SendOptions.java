package tw.com.mitake.sms;

import java.util.ArrayList;

public class SendOptions {
    private ArrayList<String> destinations = new ArrayList<String>();
    private String message;

    public void addDestination(String destination) {
        destinations.add(destination);
    }

    public ArrayList<String> getDestinations() {
        return destinations;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
