package communication.request;

import communication.response.Response;
import communication.response.ShowServices;
import server.Status;

/**
 * @version 29/04/16.
 */
public class InitializeCommunication implements Request {

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public void setContent(Object content) {
    }

    @Override
    public Response execute() {
        return new ShowServices();
    }
}
