package communication.response;

import server.model.Idea;
import util.DBHelper;

/**
 * @version 29/04/16.
 */
public class ReturnIdea extends Response {
    private Idea idea;

    public ReturnIdea() {
        idea = new Idea();
    }

    @Override
    public Idea getContent() {
        return idea;
    }

    @Override
    public void setContent(Object content) {
        idea = (new DBHelper()).selectIdeaByName((String)content);
    }
}
