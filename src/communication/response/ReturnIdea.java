package communication.response;

import server.Status;
import server.model.Idea;
import util.DBHelper;

/**
 * @version 29/04/16.
 */
public class ReturnIdea extends Response {
    private Idea idea;

    public ReturnIdea(Object content, Status status) {
        idea = new Idea();
        idea = DBHelper.selectIdeaByName((String)content);
        this.status = status;
    }

    @Override
    public Idea getContent() {
        return idea;
    }
}
