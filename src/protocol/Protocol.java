package protocol;

import communication.request.Request;
import communication.response.*;
import util.DataBase;

import java.io.IOException;

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
                int id_service = Integer.parseInt(input.getContent());
                if (id_service == 1) {
                    response = new ShowIdeas();
                    statement = ProtocolStatement.HELLO;
                } else if (id_service == 2) {
                    response = new Notice();
                    response.setContent("write the idea name");
                    statement = ProtocolStatement.WAITING_NAME;
                }
                break;
            case WAITING_NAME:
                response = new Notice();
                (new DataBase()).addRegister(input.getContent());
                response.setContent("added student");
                statement = ProtocolStatement.HELLO;
                break;
            case START:
                response = new ReturnIdea();
                response.setContent(input.getContent());
                break;
        }
        return response;
    }
}
