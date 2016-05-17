package client;

/**
 * @version 03/05/16.
 */
public class ClientParser {
    public ClientState handleState(String input, ClientState state) {
        if ("list".equalsIgnoreCase(input))
            return state;
        if (ClientState.ADD_IDEA.equals(state))
            return ClientState.ENTER_SERVICE;
        return ClientState.fromString(input.toUpperCase());
    }
}
