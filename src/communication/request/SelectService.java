package communication.request;

import server.Status;

/**
 * @version 29/04/16.
 */
public class SelectService implements Request {
    private String id_service;

    @Override
    public String getCommand() {
        return "SELECT_SERVICE";
    }

    @Override
    public String getContent() {
        return id_service;
    }

    @Override
    public void setContent(Object content) {
        this.id_service = (String)  content;
    }

    @Override
    public Status execute() {
        return null;
    }
}
