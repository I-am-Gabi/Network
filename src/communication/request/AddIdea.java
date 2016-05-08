package communication.request;

/**
 * @version 29/04/16.
 */
@SuppressWarnings("serial")
public class AddIdea extends Request {
    private String idea;

    @Override
    public String getCommand() {
        return "ADD";
    }

    @Override
    public String getContent() {
        return idea;
    }

    @Override
    public void setContent(Object content) {
        this.idea = (String) content;
    } 
}
