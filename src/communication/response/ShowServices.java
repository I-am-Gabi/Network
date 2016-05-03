package communication.response;

import protocol.ProtocolStatement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * @version 29/04/16.
 */
public class ShowServices extends Response {
    private List<String> services;

    public ShowServices() {
        services = new ArrayList<>();
        services.add(" [list] list ideas");
        services.add(" [add]  add idea");
    }

    @Override
    public String getContent() {
        return Arrays.toString(services.toArray());
    }

    @Override
    public void setContent(Object content) {
    }
}
