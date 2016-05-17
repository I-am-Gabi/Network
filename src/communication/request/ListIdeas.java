package communication.request;

import server.Status;
import util.DBHelper;

/**
 * @version 29/04/16.
 */
public class ListIdeas implements Request {

    @Override
    public String getCommand() {
        return "LIST";
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public void setContent(Object content) {
    }

    @Override
    public Status execute() {
        System.out.print(DBHelper.selectDB());
        return Status.CODE200;
    }
}
