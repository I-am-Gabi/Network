package protocol;

import communication.request.Request;
import communication.response.*;
import util.DataBase;

import java.io.IOException;
import java.io.StringReader;

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
                    response.setContent("write the idea name");
                    statement = ProtocolStatement.WAITING_NAME;
                }
                break;
            case WAITING_NAME:
                response = new Notice();
                (new DataBase()).addRegister(input.getContent());
                response.setContent("added idea... choice a service");
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
