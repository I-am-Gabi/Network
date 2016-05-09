package communication.request;

/**
 * @version 29/04/16.
 */
@SuppressWarnings("serial")
public class ListIdeas extends Request {

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
}
