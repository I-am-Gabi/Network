package protocol;

import communication.request.Request;
import communication.response.*;
import util.DBHelper; 

/**
 * @version 27/04/16.
 */
public class Protocol implements ProtocolInterface {
    private ProtocolStatement statement = ProtocolStatement.WAITING;
    private Response response; 

    public Protocol() {
        response = new ShowServices(); 
    }

    @Override
    public Response handleInput(Request input) {
        if (input.getCommand().equalsIgnoreCase("BYE")) {
            response = new Notice();
            response.setContent("BYE");
            return response;
        }

        switch (statement) {
            case WAITING:
                response = new ShowServices();
                statement = ProtocolStatement.HELLO;
                break;
            case HELLO:
                String id_service = input.getContent();
                if ("list".equalsIgnoreCase(id_service)) {
                    response = new ShowIdeas();
                    statement = ProtocolStatement.HELLO;
                } else if ("add".equalsIgnoreCase(id_service)) { 
                    response = new Notice();
                    response.setContent("write the idea... <idea name>, <idea description>, <technologies>, <student name>, <student email>");
                    statement = ProtocolStatement.WAITING_DATA;
                }
                break;
            case WAITING_DATA:
                DBHelper.insertDB(input.getContent().split(","));
                response = new Notice();
                response.setContent("idea added... [add]/[list]"); 
                statement = ProtocolStatement.HELLO;
                break; 
            default: break;
        }
        return response;
    }
}
