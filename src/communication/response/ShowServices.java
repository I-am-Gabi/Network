package communication.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 29/04/16.
 */
public class ShowServices extends Response {
    private List<String> services;

    public ShowServices() {
        services = new ArrayList<>();
        services.add(" (1) list students");
        services.add(" (2) add students");
    }

    @Override
    public String getContent() {
        return Arrays.toString(services.toArray());
    }

    @Override
    public void setContent(Object content) {
        services = (List<String>) content;
    }
}
