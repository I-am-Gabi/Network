package protocol;

import communication.request.Request;
import communication.response.*;
import util.FileHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * @version 27/04/16.
 */
public class Protocol implements ProtocolInterface {
    private ProtocolStatement statement = ProtocolStatement.WAITING;
    private Response response;
    private int index_data;
    private List<String> arguments;

    public Protocol() {
        response = new ShowServices();
        index_data = 0;
        arguments = new ArrayList<>(Arrays.asList("name", "description", "technologies", "student name", "student email"));
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
                    index_data = 0;
                    response = new Notice();
                    response.setContent("write the idea " + arguments.get(index_data++));
                    statement = ProtocolStatement.WAITING_DATA;
                }
                break;
            case WAITING_DATA:
                (new FileHelper()).addRegister(input.getContent());
                response = new Notice();
                response.setContent("write the " + arguments.get(index_data++));
                if (index_data == arguments.size())
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
