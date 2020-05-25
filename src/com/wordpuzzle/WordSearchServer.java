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

import static com.wordpuzzle.WordSearch.createWordSearch;

public class WordSearchServer {
    // INSTANCE VARIABLES
    private final InetSocketAddress listenAddress;
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final Logger logger = Logger.getGlobal();

    private List<SocketChannel> clientChannels;
    private List<Client> clients;


    private final int PORT;
    private final String HOST_ADDRESS;
    private final int WIN_POINT;
    private final int BOARD_X;
    private final int BOARD_Y;

    private char[][] boardCharacters;
    private List<String> solutions;

    final static String dictionaryFileName = "statics/dictionary.txt";

//    private final List<String> gameWords;

    public WordSearchServer(String hostAddress, int port, int winPoint, int boardX, int boardY) throws IOException {
        PORT = port;
        HOST_ADDRESS = hostAddress;
        WIN_POINT = winPoint;
        BOARD_X = boardX;
        BOARD_Y = boardY;

        clientChannels = new ArrayList<>();
        clients = new ArrayList<>();

//        new KelimeOyunu(winPoint, boardX, boardY);

        listenAddress = new InetSocketAddress(hostAddress, port);
        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        // bind server socket channel to port
        serverSocketChannel.socket().bind(listenAddress);
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

        // TODO: Bu aşamaya geldiyse socket başarılı bir şekilde oluşmuş
        // TODO: Board dataları hazırla
        WordSearch.Grid grid = createWordSearch(WordSearch.readWords(dictionaryFileName, BOARD_X, BOARD_Y));
        boardCharacters = grid.cells;
        solutions = grid.solutions;

        for(String solution:solutions){
            System.out.println(solution);
        }

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
//                            handleSendDataToClient();
                            continue;
                        }

                        if (selectionKey.isReadable()) {
                            handleReadDataFromClient(selectionKey);
                        }

//                        if (selectionKey.isWritable()) {
//                            handleSendDataToClient(selectionKey);
//                        }
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

            sendInitialDataToClient(clientSocketChannel);

            // Add Channel To list
            clientChannels.add(clientSocketChannel);
        }

        // read from the socket channel
        private void handleReadDataFromClient(SelectionKey selectionKey) throws IOException, ClassNotFoundException {
            logger.info("Reading client data...");
            SocketChannel clientChannel = (SocketChannel) selectionKey.channel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            clientChannel.read(byteBuffer);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            objectInputStream.close();

            Object newObject = objectInputStream.readObject();
            Class newClass = newObject.getClass();

            if (newClass == Client.class) {
                Client newClient = (Client) newObject;

                System.out.println("SERVER" + Client.class.getName() + " okudu");
                clients.add(newClient);
            } else if (newClass == ClientToServerAnswer.class) {
                ClientToServerAnswer clientToServerAnswer = (ClientToServerAnswer) newObject;

                String answer = clientToServerAnswer.getAnswer();
                String nickName = clientToServerAnswer.getNickName();
                // TODO: Kullanıcıya bak eğer varsa cevabını kontrol et doğruysa puan ver
                System.out.println("SERVER" + ClientToServerAnswer.class.getName() + " okudu");

                Client ourClient = null;
                for (Client client:clients) {
                    if (client.getNickName().equals(nickName)) {
                        ourClient = client;
                    }
                }

                if (ourClient == null) {
                    // TODO:
                } else {
                    boolean isAnswerCorrect = false;
                    for (String solution:solutions) {
                        if (solution.equals(answer)) {
                            isAnswerCorrect = true;
                        }
                    }

                    if (isAnswerCorrect) {
                        ourClient.setScore(ourClient.getScore() + 1);
                        // TODO: BİLDİĞİNE DAİR MESAJ GÖNDEREBİLİR MİSİN DEVELOPER ARKADAŞIM
                        if (WIN_POINT == ourClient.getScore()) {
                            JOptionPane.showMessageDialog(null, "Tebrikler " + nickName + ". Oyunu Kazandınız..............");

                        } else {
                            JOptionPane.showMessageDialog(null, "Tebrikler " + nickName + ". Doğru tahmin ettiniz. Puanınız. " + ourClient.getScore());
                        }
                    } else {
                        // TODO: HATA MESAJI GÖNDEREBİLİR MİSİN DEVELOPER ARKADAŞIM
                        JOptionPane.showMessageDialog(null, "Maalesef cevabınız doğru değil");
                    }
                }

            }


            logger.info("Message was readed from SERVER");
        }

        // send data to client channel
        private void handleSendDataToClient() throws IOException {
            logger.info("Server wants to send data to client");
//            SocketChannel clientChannel = serverSocketChannel;
            for(SocketChannel socketChannel: clientChannels) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(new Client("Server Furkan", 200));
                objectOutputStream.flush();
                objectOutputStream.close();

                byte[] byteArray  = byteArrayOutputStream.toByteArray();
                ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
                socketChannel.write(byteBuffer);
            }
            logger.info("Server successfully sended data");
        }

        private void sendInitialDataToClient(SocketChannel clientSocketChannel) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            ServerToClientBoard initialGameBoard = new ServerToClientBoard(boardCharacters);

            objectOutputStream.writeObject(initialGameBoard);
            objectOutputStream.flush();
            objectOutputStream.close();

            byte[] byteArray  = byteArrayOutputStream.toByteArray();
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
            clientSocketChannel.write(byteBuffer);
        }

    }
}
