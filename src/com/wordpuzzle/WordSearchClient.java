package com.wordpuzzle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class WordSearchClient {
    private final String USERNAME;
    private final int PORT;
    private final String HOST;

    private InetSocketAddress hostAddress;
    private SocketChannel clientSocketChannel;

    private Logger logger = Logger.getGlobal();

    public WordSearchClient(String username, int port, String host) throws IOException {
        USERNAME = username;
        PORT = port;
        HOST = host;

        hostAddress = new InetSocketAddress(host, port);
        clientSocketChannel = SocketChannel.open(hostAddress);

        WordSearchClientThread clientThread = new WordSearchClientThread();
        clientThread.start();
    }

    private class WordSearchClientThread extends Thread {
        @Override
        public void run() {
            // TODO: Send message
            logger.info("Client Thread started");
        }
    }
}
