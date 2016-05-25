package server.protocol;

import communication.request.Request;
import communication.response.Response;

/**
 * @version 27/04/16.
 */
public interface ProtocolInterface {
    Response handleInput(Request input);
}
