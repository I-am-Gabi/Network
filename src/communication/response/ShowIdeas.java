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
        ideas = (new DBHelper()).selectDB();
    }

    @Override
    public String getContent() {
        String ideasString = "";
        for (Idea i: ideas) {
            ideasString += (i.toString() + "\n");
        }
        return ideasString;
    }

    @Override
    public void setContent(Object content) {}
}
