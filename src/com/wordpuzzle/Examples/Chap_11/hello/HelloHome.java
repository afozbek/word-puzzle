package com.wordpuzzle.Examples.Chap_11.hello;

import javax.ejb.*;
import java.rmi.*;

public interface HelloHome extends EJBHome
{
   public Hello create()  
                 throws CreateException, RemoteException;
}
