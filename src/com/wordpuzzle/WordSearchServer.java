package com.wordpuzzle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class WordSearchServer {
    private final InetSocketAddress listenAddress;
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final Logger logger = Logger.getGlobal();

    private final int PORT;
    private final String HOST_ADDRESS;

    public WordSearchServer(String hostAddress, int port) throws IOException {
        PORT = port;
        HOST_ADDRESS = hostAddress;

        listenAddress = new InetSocketAddress(hostAddress, port);
        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        // bind server socket channel to port
        serverSocketChannel.socket().bind(listenAddress);
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

        WordSearchMainThread mainThread = new WordSearchMainThread();
        mainThread.start();

        System.out.println("Server started on port >> " + PORT);
    }

    private class WordSearchMainThread extends Thread{
        @Override
        public void run() {
            logger.fine("THREAD IS RUNNING");

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

        private void handleClientConnection(SelectionKey selectionKey) throws IOException {
            if (!selectionKey.isAcceptable()) { // Accept client connections
                return;
            }

            // TODO: Accept client connection
            accept(selectionKey);
        }

        private void handleReadDataFromClient(SelectionKey selectionKey) throws IOException {
           if (!selectionKey.isReadable()) { // Read from client
                return;
           }

           // TODO: Read Data from Client
            read(selectionKey);

        }

        private void handleSendDataToClient(SelectionKey selectionKey) throws IOException {
           if (!selectionKey.isWritable()) { // Send to client
                return;
           }

           // TODO: Send Data to Client
            read(selectionKey);
        }


        // accept client connection
        private void accept(SelectionKey key) throws IOException {
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverChannel.accept();
            channel.configureBlocking(false);

            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            System.out.println("Connected to: " + remoteAddr);

            /*
             * Register channel with selector for further IO (record it for read/write
             * operations, here we have used read operation)
             */
            channel.register(selector, SelectionKey.OP_READ);
        }

        // read from the socket channel
        private void read(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int numRead = -1;
            numRead = channel.read(buffer);

            if (numRead == -1) {
                Socket socket = channel.socket();
                SocketAddress remoteAddr = socket.getRemoteSocketAddress();
                System.out.println("Connection closed by client: " + remoteAddr);
                channel.close();
                key.cancel();
                return;
            }

            byte[] data = new byte[numRead];
            System.arraycopy(buffer.array(), 0, data, 0, numRead);
            System.out.println("Got: " + new String(data));
        }

        private void send(SelectionKey key) throws  IOException {}
    }
}
