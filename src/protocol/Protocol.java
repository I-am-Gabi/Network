package protocol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * @version 27/04/16.
 */
public class Protocol implements ProtocolInterface {
    // TODO: convert final int variable in enum state
    private final int WAITING = 0;
    private final int HELLO = 1;
    private final int START_COMUNICATION = 2;
    private final int WAITING_STUDENT_NAME = 3;
    private int state = WAITING;

    private List<String> answers_database;

    public Protocol() {
        answers_database = new ArrayList<>();
    }

    @Override
    public String handleInput(String input) {
        String output = null;
        switch (state) {
            case WAITING:
                output = "choice a service: ";
                output += "(1) list students ";
                output += "(2) add student ";
                state = HELLO;
                break;
            case HELLO:
                try {
                    int id = Integer.parseInt(input);
                    if (id == 1) {
                        output = Arrays.toString(answers_database.toArray());
                        state = HELLO;
                    } else if (id == 2) {
                        output = "write the student name";
                        state = WAITING_STUDENT_NAME;
                    }
                } catch (NumberFormatException ex) {
                    output = handleException(input);
                }
                break;
            case WAITING_STUDENT_NAME:
                answers_database.add(input);
                output = "added student";
                state = HELLO;
                break;
            case START_COMUNICATION:
                try {
                    int id = Integer.parseInt(input);
                    output = answers_database.get(id - 1);
                } catch (NumberFormatException ex) {
                    output = handleException(input);
                }
                break;
        }
        return output;
    }

    // TODO: create a real exception
    // TODO: create a good object to use as response
    private String handleException(String input) {
        String output;
        if (input.equalsIgnoreCase("bye")) {
            output = "BYE";
            state = WAITING;
        } else {
            output = "you need choice an id [ " + 1 + " .. " + answers_database.size() + " ]";
        }
        return output;
    }
}
