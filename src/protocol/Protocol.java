package protocol;

/**
 * @version 27/04/16.
 */
public class Protocol implements ProtocolInterface {
    private final int WAITING = 0;
    private final int START_COMUNICATION = 1;
    private int state = WAITING;

    private String[] answers_test = { "Adrien", "Nicolas", "Gabriela", "Cesar" };

    @Override
    public String handleInput(String input) {
        String output = null;
        switch (state) {
            case WAITING:
                output = "choice a id... ";
                state = START_COMUNICATION;
                break;
            case START_COMUNICATION:
                try {
                    int id = Integer.parseInt(input);
                    output = answers_test[id - 1];
                } catch (NumberFormatException ex) {
                    if (input.equalsIgnoreCase("bye")) {
                        output = "BYE";
                        state = WAITING;
                    } else {
                        output = "you need choice an id [ " + 1 + " .. " + answers_test.length + " ]";
                    }
                }
                break;
        }
        return output;
    }
}
