package communication.response;

import server.Status;

/**
 * @version 29/04/16.
 */
public class Notice extends Response {
    private String message;

    public Notice(Object content, Status status) {
        this.message = (String) content;
        this.status = status;
    }

    @Override
    public String getContent() {
        return message;
    }
}
