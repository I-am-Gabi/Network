package communication.response;

import util.DataBase;

import java.util.Arrays;
import java.util.List;

/**
 * @version 29/04/16.
 */
public class ShowIdeas extends Response {
    private List<String> answers_database;

    public ShowIdeas() {
        answers_database = (new DataBase()).getRegisters();
    }

    @Override
    public String getContent() {
        return Arrays.toString(answers_database.toArray());
    }

    @Override
    public void setContent(Object content) {}

}
