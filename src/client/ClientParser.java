package client;

/**
 * @version 03/05/16.
 */
public class ClientParser {
    public ClientState handleState(String input, ClientState state) {
        if ("BYE".equalsIgnoreCase(input))
            return ClientState.QUIT;
        if (ClientState.ADD_IDEA.equals(state) || "list".equalsIgnoreCase(input))
            return state;
        return ClientState.fromString(input.toUpperCase());
    }
}
