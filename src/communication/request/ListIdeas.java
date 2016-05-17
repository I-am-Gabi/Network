package communication.request;

import communication.response.Response;
import communication.response.ShowIdeas;
import server.Status;
import util.DBHelper;

/**
 * @version 29/04/16.
 */
public class ListIdeas implements Request {

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public void setContent(Object content) {
    }

    @Override
    public Response execute() {
        return new ShowIdeas();
    }
}
