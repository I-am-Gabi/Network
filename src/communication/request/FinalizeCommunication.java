package communication.request;

import communication.response.Notice;
import communication.response.Response;
import server.Status;

/**
 * @version 29/04/16.
 */
public class FinalizeCommunication implements Request {

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public void setContent(Object content) {
    }

    @Override
    public Response execute() {
        return new Notice("BYE", Status.CODE200);
    }
}
