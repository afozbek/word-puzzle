package com.wordpuzzle.Examples.Chap_11.hello;

import java.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class HelloBean implements SessionBean
{
   private SessionContext context;

   public void setSessionContext(SessionContext context)
   {
      this.context = context;
   }

   /*
      The next method corresponds to the create method in 
      the home interface HelloHome.java.
      When the client calls HelloHome.create(), the 
      container allocates an instance of	the EJBean and 
      calls ejbCreate().
   */
   public void ejbCreate ()
   {
      //Left empty.
   }

   public void ejbActivate()
   {
      //Left empty.
   }

   public void ejbPassivate()
   {
      //Left empty.
   }

   public void ejbRemove()
   {
      //Left empty.
   }

   /*
      Now for the business logic.
      Only one simple method in this example...
   */
   public String greet() throws RemoteException
   {
      return("Hello there!");
   }
}
