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
        clientSocketChannel = SocketChannel.open();

        // To use selector we must set blocking to false
        clientSocketChannel.configureBlocking(false);
        clientSocketChannel.connect(new InetSocketAddress(host, port));

        clientSocketChannel.register(selector, SelectionKey.OP_CONNECT);

        WordSearchClientThread clientThread = new WordSearchClientThread();
        clientThread.start();
    }

    private class WordSearchClientThread extends Thread {
        @Override
        public void run() {
            logger.info("Client Thread started");

            while(true) {
                try {
                    int readyChannels = selector.select();

                    if(readyChannels == 0) continue;

                    Iterator<SelectionKey> selectionKeysIterator = selector.selectedKeys().iterator();

                    while(selectionKeysIterator.hasNext()) {
                        SelectionKey selectionKey = selectionKeysIterator.next();
                        selectionKeysIterator.remove();

                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        if (!selectionKey.isValid()) {
                            System.out.println("Not Valid");
                            continue;
                        }

                        if (selectionKey.isConnectable()) {
                            System.out.println("Connectable");

                            if (client.isConnectionPending()) {
                                try {
                                    client.finishConnect();
                                } catch (IOException ex) {ex.printStackTrace();}
                            }

                            client.register(selector, SelectionKey.OP_WRITE);
                            continue;
                        }

                        if (selectionKey.isWritable()) {
                            System.out.println("Writable");
                            continue;
                        }

                        if (selectionKey.isReadable()) {
                            System.out.println("Readable");
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
