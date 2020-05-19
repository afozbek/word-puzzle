package com.wordpuzzle;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.logging.Logger;

public class WordSearchServer {
    private final InetSocketAddress listenAddress;
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final Logger logger = Logger.getGlobal();

    private final int PORT;
    private final String HOST_ADDRESS;

    private List<SocketChannel> players;

    public WordSearchServer(String hostAddress, int port) throws IOException {
        PORT = port;
        HOST_ADDRESS = hostAddress;

        players = new ArrayList<SocketChannel>();

        listenAddress = new InetSocketAddress(hostAddress, port);
        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        // bind server socket channel to port
        serverSocketChannel.socket().bind(listenAddress);
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

        WordSearchMainThread mainThread = new WordSearchMainThread();
        mainThread.start();

        logger.info("Server started on: " + PORT);
    }

    private class WordSearchMainThread extends Thread{
        @Override
        public void run() {
            logger.info("THREAD IS RUNNING");

            while (true) {
                // wait for events
                try {
                    int readyCount = selector.select();
                    if (readyCount == 0) {
                        continue;
                    }

                    // process selected keys...
                    Set<SelectionKey> readyKeys = selector.selectedKeys();
                    Iterator<SelectionKey> selectionKeyIterator = readyKeys.iterator();
                    while (selectionKeyIterator.hasNext()) {
                        SelectionKey selectionKey = selectionKeyIterator.next();
                        selectionKeyIterator.remove();

                        if (!selectionKey.isValid()) {
                            continue;
                        }

                        handleClientConnection(selectionKey);
                        handleReadDataFromClient(selectionKey);
                        handleSendDataToClient(selectionKey);
                    }
                } catch (IOException ex) {
                    logger.severe("THREAD EXCEPTION HAPPENED");
                    ex.printStackTrace();
                    break;
                }
            }
        }

        // accept client connection
        private void handleClientConnection(SelectionKey selectionKey) throws IOException {
            if (!selectionKey.isAcceptable()) { // Accept client connections
                return;
            }
            logger.info("Client trying to connect..");

            // TODO: Accept client connection
            ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel clientSocketChannel = serverChannel.accept();

            clientSocketChannel.configureBlocking(false);

            logger.info("Client connected to: " + clientSocketChannel.socket().getRemoteSocketAddress());

            clientSocketChannel.register(selector, SelectionKey.OP_READ);
        }

        // read from the socket channel
        private void handleReadDataFromClient(SelectionKey selectionKey) throws IOException {
           if (!selectionKey.isReadable()) { // Read from client
                return;
           }
           logger.info("Client wants to send data");

           // TODO: Read Data from Client
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int numRead = -1;
            numRead = channel.read(buffer);

            if (numRead == -1) {
                Socket socket = channel.socket();
                SocketAddress remoteAddr = socket.getRemoteSocketAddress();
                System.out.println("Connection closed by client: " + remoteAddr);
                channel.close();
                selectionKey.cancel();
                return;
            }

            byte[] data = new byte[numRead];
            System.arraycopy(buffer.array(), 0, data, 0, numRead);
            System.out.println("Got: " + new String(data));
        }

        // send data to client channel
        private void handleSendDataToClient(SelectionKey selectionKey) throws IOException {
           if (!selectionKey.isWritable()) { // Send to client
                return;
           }

           // TODO: Send Data to Client
        }

    }
}
