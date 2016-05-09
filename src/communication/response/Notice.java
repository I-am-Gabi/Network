package communication.response;

/**
 * @version 29/04/16.
 */
@SuppressWarnings("serial")
public class Notice extends Response {
    private String message;

    @Override
    public String getContent() {
        return message;
    }

    @Override
    public void setContent(Object content) {
        this.message = (String) content;
    }
}
