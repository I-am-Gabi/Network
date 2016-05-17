package communication.request;

import communication.response.Notice;
import communication.response.Response;
import communication.response.ShowIdeas;
import server.Status;

/**
 * @version 29/04/16.
 */
public class SelectService implements Request {
    private String id_service;

    @Override
    public String getContent() {
        return id_service;
    }

    @Override
    public void setContent(Object content) {
        this.id_service = (String) content;
    }

    @Override
    public Response execute() {
        if (id_service.equalsIgnoreCase("add")) {
            return new Notice("write the idea... <idea name>, <idea description>, <technologies>, <student name>, <student email>", Status.CODE200);
        } else if (id_service.equalsIgnoreCase("list")) {
            return new ShowIdeas();
        }
        return null;
    }
}
