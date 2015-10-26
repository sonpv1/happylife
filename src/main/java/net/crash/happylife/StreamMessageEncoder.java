package net.crash.happylife;

import net.crash.happylife.Video;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by sonphan on 10/25/15.
 */
public class StreamMessageEncoder implements Encoder.BinaryStream<Video> {
    @Override
    public void encode(Video object, OutputStream os) throws EncodeException, IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(object);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        os.flush();
        os.close();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
