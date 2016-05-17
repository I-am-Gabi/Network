package communication.request;

import server.Status;
import util.DBHelper;

/**
 * @version 29/04/16.
 */
public class AddIdea implements Request {
    private String idea;

    @Override
    public String getCommand() {
        return "ADD";
    }

    @Override
    public String getContent() {
        return idea;
    }

    @Override
    public void setContent(Object content) {
        this.idea = (String) content;
    }

    @Override
    public Status execute() {
        DBHelper.insertDB(idea.split(","));
        return Status.CODE200;
    }
}
