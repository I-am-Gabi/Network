package communication.response;

/**
 * @version 29/04/16.
 */
@SuppressWarnings("serial")
public class ShowServices extends Response {
    private String services;

    public ShowServices() {
        services = "[add]/[list]";
    }

    @Override
    public String getContent() {
        return services;
    }

    @Override
    public void setContent(Object content) {
    }
}
