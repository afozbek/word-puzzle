package com.wordpuzzle;

import javax.swing.*;
import java.awt.*;
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
    // INSTANCE VARIABLES
    private final InetSocketAddress listenAddress;
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final Logger logger = Logger.getGlobal();

    private final int PORT;
    private final String HOST_ADDRESS;
    private final int WIN_POINT;
    private final int BOARD_X;
    private final int BOARD_Y;

    public WordSearchServer(String hostAddress, int port, int winPoint, int boardX, int boardY) throws IOException {
        PORT = port;
        HOST_ADDRESS = hostAddress;
        WIN_POINT = winPoint;
        BOARD_X = boardX;
        BOARD_Y = boardY;

//        new KelimeOyunu(winPoint, boardX, boardY);

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

                        if (selectionKey.isAcceptable()) {
                            handleClientConnection(selectionKey);
                            continue;
                        }

                        if (selectionKey.isReadable()) {
                            handleReadDataFromClient(selectionKey);
                            continue;
                        }

                        if (selectionKey.isWritable()) {
                            handleSendDataToClient(selectionKey);
                            continue;
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    logger.severe("SERVER THREAD EXCEPTION HAPPENED");
                    ex.printStackTrace();
                }
            }
        }

        // accept client connection
        private void handleClientConnection(SelectionKey selectionKey) throws IOException {
            logger.info("Client trying to connect..");

            // TODO: Accept client connection
            ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel clientSocketChannel = serverChannel.accept();

            clientSocketChannel.configureBlocking(false);

            logger.info("Client connected to: " + clientSocketChannel.socket().getRemoteSocketAddress());

            clientSocketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("Client is registered as read");
        }

        // read from the socket channel
        private void handleReadDataFromClient(SelectionKey selectionKey) throws IOException, ClassNotFoundException {
           logger.info("Client wants to send data");


        }

        // send data to client channel
        private void handleSendDataToClient(SelectionKey selectionKey) throws IOException {
           // TODO: Send Data to Client
        }

    }
}
