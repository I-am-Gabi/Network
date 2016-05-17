package communication.response;

import server.Status;

import java.io.Serializable;

/**
 * @version 29/04/16.
 */
public abstract class Response implements Serializable {
    protected Status status = null;

    public Object getContent() {return null;}
}
