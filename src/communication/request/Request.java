package communication.request;

import java.io.Serializable;

/**
 * @version 29/04/16.
 */
public abstract class Request implements Serializable {
    public abstract String getCommand();
    public abstract String getContent();
    public abstract void setContent(Object content);
    public abstract void invoke();
}
