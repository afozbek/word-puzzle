package com.wordpuzzle.Examples.Chap_11.hello;

import javax.naming.*;
import javax.rmi.*;
import java.util.Properties;

public class HelloClient
{
   public static void main(String[] args)
   {
      try
      {
         //Step 1:
         Context context = getInitialContext();
         //Above method needs to be defined for a specific
         //container, and so contains some vendor-specific
         //code.

         //Step 2:
         Object homeRef = context.lookup("HelloHome");

         //Step 3:
         HelloHome home = 
             (HelloHome)PortableRemoteObject.narrow(
                                homeRef,HelloHome.class);
         //EJB1.0 simply used a cast, instead of the above.
	     //The use of PortableObject.narrow() is required 
         //to support RMI over IIOP.

         //Step 4:
         Hello hello = home.create();

         //Step 5 (call to method greet):
         System.out.println("Output from bean: " +
                                              hello.greet());
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public static Context getInitialContext()
                                      throws NamingException
   {
      Properties props = new Properties();

      //Second argument below holds vendor-specific string.
      //Here, the string refers to WebLogic's EJB 
      //container.
      props.put(Context.INITIAL_CONTEXT_FACTORY,
                    "weblogic.jndi.WLInitialContextFactory");

      //Again, the second argument below is a vendor- 
      //specific string indicating the WebLogic protocol
      //'t3' and WebLogic's default server port of 7001.
      props.put(Context.PROVIDER_URL,"t3://localhost:7001");
      return new InitialContext(props);
   }
}
