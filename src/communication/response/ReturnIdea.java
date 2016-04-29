package communication.response;

import util.DataBase;

/**
 * @version 29/04/16.
 */
public class ReturnIdea extends Response {
    private String idea;

    public ReturnIdea() {
        idea = "";
    }

    @Override
    public String getContent() {
        return idea;
    }

    @Override
    public void setContent(Object content) {
        idea = (new DataBase()).getRegister((int)content);
    }
}
