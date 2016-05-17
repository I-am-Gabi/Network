package communication.request;

import server.Status;

/**
 * @version 29/04/16.
 */
public class InitializeComunication implements Request {
    @Override
    public String getCommand() {
        return "HELO";
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
        return Status.CODE200;
    }
}
