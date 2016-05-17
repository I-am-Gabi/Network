package communication.request;

import server.Status;

import java.io.Serializable;

/**
 * @version 29/04/16.
 */
public interface Request extends Serializable {
    String getCommand();
    String getContent();
    void setContent(Object content);
    Status execute();
}
