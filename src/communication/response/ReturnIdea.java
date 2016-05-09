package communication.response;

import server.model.Idea;
import util.DBHelper;

/**
 * @version 29/04/16.
 */
@SuppressWarnings("serial")
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
        idea = DBHelper.selectIdeaByName((String)content);
    }
}
