package com.wordpuzzle.Examples.Chap_3;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class MultiEchoServerNIO
{
	private static ServerSocketChannel
									serverSocketChannel;
	private static final int PORT = 1234;

	private static Selector selector;
	/*
	Above Selector used both for detecting new
	connections (on the ServerSocketChannel) and for
	detecting incoming data from existing connections
	(on the SocketChannel).
	*/

	public static void main(String[] args)
	{
		ServerSocket serverSocket;

		System.out.println("Opening port...\n");

		try
		{
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocket = serverSocketChannel.socket();
			/*
			ServerSocketChannel created before
			ServerSocket largely in order to configure
			latter as a non-blocking socket by calling
			the configureBlocking method of the
			ServerSocketChannel with argument of 'false'.

			(A ServerSocket will have a
			ServerSocketChannel only if the latter is
			created first.)
			*/

			InetSocketAddress netAddress =
							    new InetSocketAddress(PORT);

			//Bind socket to port...
			serverSocket.bind(netAddress);

			//Create a new Selector object for detecting
			//input from channels...
			selector = Selector.open();

			//Register ServerSocketChannel with Selector
			//for receiving incoming connections...
			serverSocketChannel.register(selector,
								  	SelectionKey.OP_ACCEPT);

		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
			System.exit(1);
		}

		processConnections();
	}

	private static void processConnections()
	{
		do
		{
			try
			{
				//Get number of events (new connection(s)
				//and/or data transmissions from existing
				//connection(s))...
				int numKeys = selector.select();

				if (numKeys > 0)
				{
					//Extract event(s) that have been
					//triggered ...
					Set eventKeys = selector.selectedKeys();

					//Set up Iterator to cycle though set
					//of events...
					Iterator keyCycler =
								      eventKeys.iterator();

					while (keyCycler.hasNext())
					{
						SelectionKey key =
							 (SelectionKey)keyCycler.next();

						//Retrieve set of ready ops for
						//this key (as a bit pattern)...
						int keyOps = key.readyOps();

						if (
						 (keyOps & SelectionKey.OP_ACCEPT)
						        == SelectionKey.OP_ACCEPT)
						{//New connection.
							acceptConnection(key);
							continue;
						}
						if (
						  (keyOps & SelectionKey.OP_READ)
						         == SelectionKey.OP_READ)
						{//Data from existing client.
							acceptData(key);
						}
					}
				}
			}
			catch(IOException ioEx)
			{
				ioEx.printStackTrace();
				System.exit(1);
			}
		}while (true);
	}

	private static void acceptConnection(
					SelectionKey key) throws IOException
	{//Accept incoming connection and add to list.
		SocketChannel socketChannel;
		Socket socket;

		socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		socket = socketChannel.socket();
		System.out.println("Connection on "
									    + socket + ".");

		//Register SocketChannel for receiving data...
		socketChannel.register(selector,
   								    SelectionKey.OP_READ);

		//Avoid re-processing this event as though it
		//were a new one (next time through loop)...
		selector.selectedKeys().remove(key);
	}

	private static void acceptData(SelectionKey key)
										throws IOException
	{//Accept data from existing connection.
		SocketChannel socketChannel;
		Socket socket;
		ByteBuffer buffer = ByteBuffer.allocate(2048);
		//Above used for reading/writing data from/to
		//SocketChannel.

		socketChannel = (SocketChannel)key.channel();
		buffer.clear();
		int numBytes = socketChannel.read(buffer);
		System.out.println(numBytes + " bytes read.");
		socket = socketChannel.socket();

		if (numBytes==-1)
		//OP_READ event also triggered by closure of
		//connection or error of some kind. In either
		//case, numBytes = -1.
		{
			//Request that registration of this key's
			//channel with its selector be cancelled...
			key.cancel();

			System.out.println("\nClosing socket "
							+ socket + "...");
			closeSocket(socket);
		}
		else
		{
			try
			{
				/*
				Reset buffer pointer to start of buffer,
				prior to reading buffer's contents and
				writing them to the SocketChannel...
				*/
				buffer.flip();
				while (buffer.remaining()>0)
					socketChannel.write(buffer);
			}
			catch (IOException ioEx)
			{
				System.out.println("\nClosing socket "
									+ socket + "...");
				closeSocket(socket);
			}
		}
		//Remove this event, to avoid re-processing it
		//as though it were a new one...
		selector.selectedKeys().remove(key);
	}

	private static void closeSocket(Socket socket)
	{
		try
		{
			if (socket != null)
				socket.close();
		}
		catch (IOException ioEx)
		{
			System.out.println(
					"*** Unable to close socket! ***");
		}
	}
}
