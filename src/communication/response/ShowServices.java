package communication.response;

/**
 * @version 29/04/16.
 */
public class ShowServices extends Response {
    private String services;

    public ShowServices() {
        services = "[add]/[list]";
    }

    @Override
    public String getContent() {
        return services;
    }
}
