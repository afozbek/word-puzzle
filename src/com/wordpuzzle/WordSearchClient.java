package com.wordpuzzle;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.rmi.UnknownHostException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class WordSearchClient {
    private final String NICKNAME;
    private final int PORT;
    private final String HOST;
    private final WordSearchClient wordSearchClient = this;

    private InetSocketAddress hostAddress;
    private SocketChannel clientSocketChannel;
    private Selector selector;

    private Logger logger = Logger.getGlobal();

    public WordSearchClient(String nickName, int port, String host) throws IOException {
        NICKNAME = nickName;
        PORT = port;
        HOST = host;

        selector = Selector.open();

        hostAddress = new InetSocketAddress(host, port);
        clientSocketChannel = SocketChannel.open(hostAddress);

        WordSearchClientThread clientThread = new WordSearchClientThread();
        clientThread.start();
    }

    private class WordSearchClientThread extends Thread {
        @Override
        public void run() {
            logger.info("Client Thread started");

            String threadName = Thread.currentThread().getName();

            // Send messages to server
            String[] messages = new String[] { threadName + ": msg1", threadName + ": msg2", threadName + ": msg3" };

            try {
                for (int i = 0; i < messages.length; i++) {
                    ByteBuffer buffer = ByteBuffer.allocate(74);
                    buffer.put(messages[i].getBytes());
                    buffer.flip();
                    clientSocketChannel.write(buffer);
                    System.out.println(messages[i]);
                    buffer.clear();
                }
            } catch (IOException ex) {

            }
        }


    }
}
