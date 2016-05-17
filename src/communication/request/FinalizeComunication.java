package communication.request;

import server.Status;

/**
 * @version 29/04/16.
 */
public class FinalizeComunication implements Request {
    @Override
    public String getCommand() {
        return "BYE";
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
        return Status.CODE300;
    }
}
