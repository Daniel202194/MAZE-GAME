package Client;

import java.io.InputStream;
import java.io.OutputStream;
/**
 * interface Class of the strategies of the clients.
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
