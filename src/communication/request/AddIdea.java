package communication.request;

import communication.response.Notice;
import communication.response.Response;
import server.Status;
import util.DBHelper;

/**
 * @version 29/04/16.
 */
public class AddIdea implements Request {
    private String idea;

    @Override
    public String getContent() {
        return idea;
    }

    @Override
    public void setContent(Object content) {
        this.idea = (String) content;
    }

    @Override
    public Response execute() {
        DBHelper.insertDB(idea.split(","));
        return new Notice("idea added... [add]/[list]", Status.CODE200);
    }
}
