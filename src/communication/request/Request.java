package communication.request;

import communication.response.Response;
import server.Status;

import java.io.Serializable;

/**
 * @version 29/04/16.
 */
public interface Request extends Serializable {
    String getContent();
    void setContent(Object content);
    Response execute();
}
