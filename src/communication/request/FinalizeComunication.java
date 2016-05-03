package communication.request;

/**
 * @version 29/04/16.
 */
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

    @Override
    public void invoke() {

    }
}
