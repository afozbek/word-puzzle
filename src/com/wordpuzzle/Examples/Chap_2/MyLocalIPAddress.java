package com.wordpuzzle.Examples.Chap_2;

import java.net.*;

public class MyLocalIPAddress
{
	public static void main(String[] args)
	{
		try
		{
          	InetAddress address = InetAddress.getLocalHost();
 			System.out.println(address);
        }
        catch (UnknownHostException uhEx)
		{
            System.out.println(
					"Could not find local address!");
        }

    }
}
