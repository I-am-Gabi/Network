package communication.response;

import server.model.Idea;
import util.DBHelper;

import java.util.List;

/**
 * @version 29/04/16.
 */
public class ShowIdeas extends Response {
    private List<Idea> ideas;

    public ShowIdeas() {
        ideas = DBHelper.selectDB();
    }

    @Override
    public List<Idea> getContent() {
        return ideas;
    }
}
