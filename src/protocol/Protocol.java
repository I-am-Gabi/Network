package protocol;

import communication.request.Request;
import communication.response.*;
import server.Status;
import util.DBHelper;

/**
 * @version 27/04/16.
 */
public class Protocol implements ProtocolInterface {
    @Override
    public Response handleInput(Request input) {
        return input.execute();
    }
}
