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
        String[] data = idea.split(",");
        if (data.length == 5) {
            DBHelper.insertDB(idea.split(","));
            return new Notice("idea added... [add]/[list]", Status.CODE200);
        } else {
            return new Notice("Error: number of arguments", Status.CODE300);
        }
    }
}
