package com.wordpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private Client newClient;

    private ClientGameBoard clientGameBoard;
    private TextField answerField;
    private JButton answerButton;

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
                                client.finishConnect();
                            }

                            client.register(selector, SelectionKey.OP_READ);
                            sendNewClientData();

                            continue;
                        }

                        if (selectionKey.isReadable()) {
                            System.out.println("Readable");
                            readServerMessage();
                        }
                    }


                } catch (IOException | ClassNotFoundException e ) {
                    e.printStackTrace();
                }
            }
        }


        // CLIENT BOARD
        private void readServerMessage() throws IOException, ClassNotFoundException {
            logger.info("Reading SERVER data...");
            SocketChannel clientChannel = clientSocketChannel;

            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            clientChannel.read(byteBuffer);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            objectInputStream.close();

            ServerToClientBoard clientBoard = (ServerToClientBoard) objectInputStream.readObject();
            char[][] characters = clientBoard.getGameCharacters();

            clientGameBoard = new ClientGameBoard("Client Game Board");
            clientGameBoard.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            printCharacters(characters);

            logger.info("Message was readed from client");
        }

        private void sendNewClientData() throws IOException {
            SocketChannel clientChannel = clientSocketChannel;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            Client client = new Client(NICKNAME, 0);
            newClient = client;

            objectOutputStream.writeObject(newClient);
            objectOutputStream.flush();
            objectOutputStream.close();

            byte[] byteArray  = byteArrayOutputStream.toByteArray();
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

            clientChannel.write(byteBuffer);

            logger.info("Client successfully sended data");
        }

        private void printCharacters(char[][] characters) {
            JPanel clientGameBoardPanel = new JPanel();

            clientGameBoardPanel.setLayout(new GridLayout(characters.length + 1, characters[0].length));
            clientGameBoard.add(clientGameBoardPanel);

            for (int r = 0; r < characters.length; r++) {
                for (int c = 0; c < characters[r].length; c++) {
                    JLabel label = new JLabel(String.valueOf(characters[r][c]));
                    clientGameBoardPanel.add(label);
                }
            }

            answerField = new TextField();
            answerButton = new JButton("OK");

            clientGameBoardPanel.add(new JLabel("Kelime Giriniz"));
            clientGameBoardPanel.add(answerField);
            clientGameBoardPanel.add(answerButton);

            answerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        handleAnswerButton();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });


            clientGameBoard.pack();
            clientGameBoard.setVisible(true);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            clientGameBoard.setSize(screenSize.width - 100, screenSize.height - 50);
            clientGameBoard.setLocationRelativeTo(null);
        }

        private void handleAnswerButton() throws IOException {
            String answer = answerField.getText();
            System.out.println(answer);
            // TODO: send answer to server

            SocketChannel clientChannel = clientSocketChannel;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            ClientToServerAnswer newAnswer = new ClientToServerAnswer(NICKNAME, answer);

            objectOutputStream.writeObject(newAnswer);
            objectOutputStream.flush();
            objectOutputStream.close();

            byte[] byteArray  = byteArrayOutputStream.toByteArray();
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

            clientChannel.write(byteBuffer);

            logger.info("Client successfully sended data");

        }
    }
}
