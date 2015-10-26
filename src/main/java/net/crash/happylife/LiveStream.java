/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crash.happylife;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author cool
 */
@ServerEndpoint(value="/livevideo")
public class LiveStream {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void whenOpening(Session session) throws IOException, EncodeException {
        session.setMaxBinaryMessageBufferSize(1024 * 1024 * 8);
        sessions.add(session);
    }

    @OnMessage
    public void processVideo(byte[] imageData, Session session) {
        System.out.println("Insite process Video");
        try {
            // Wrap a byte array into a buffer
            ByteBuffer buf = ByteBuffer.wrap(imageData);
            for (Session session2 : sessions) {
                session2.getBasicRemote().sendBinary(buf);
            }
        } catch (Throwable ioe) {
            System.out.println("Error sending message " + ioe.getMessage());
        }
    }

    @OnClose
    public void whenClosing(Session session) {
        System.out.println("Goodbye !");
        sessions.remove(session);
    }

}

