package net.crash.happylife;

import net.crash.happylife.Video;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by sonphan on 10/25/15.
 */
public class StreamMessageDecoder implements Decoder.BinaryStream<Video> {

    @Override
    public Video decode(InputStream is) throws DecodeException, IOException {
        Video video = null;
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            video = (Video) ois.readObject();
        } catch (ClassNotFoundException exp) {
            exp.printStackTrace();
        }
        is.close();

        return video;
    }

    public StreamMessageDecoder() {
        super();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
