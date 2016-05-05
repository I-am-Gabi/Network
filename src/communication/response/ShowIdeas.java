package communication.response;

import server.model.Idea;
import util.DBHelper;

import java.util.List;

/**
 * @version 29/04/16.
 */
@SuppressWarnings("serial")
public class ShowIdeas extends Response {
    private List<Idea> ideas;

    public ShowIdeas() {
        ideas = DBHelper.selectDB();
    }

    @Override
    public List<Idea> getContent() {
        return ideas;
    }

    @Override
    public void setContent(Object content) {}
}
