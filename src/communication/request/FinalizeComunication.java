package communication.request;

/**
 * @version 29/04/16.
 */
@SuppressWarnings("serial")
public class FinalizeComunication extends Request {
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
}
