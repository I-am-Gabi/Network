package communication.request;

/**
 * @version 29/04/16.
 */
public class SelectIdea extends Request {
    private String id;

    @Override
    public String getCommand() {
        return "SELECT_IDEA";
    }

    @Override
    public String getContent() {
        return id;
    }

    @Override
    public void setContent(Object content) {
        this.id = (String) content;
    }

    @Override
    public void invoke() {

    }
}
