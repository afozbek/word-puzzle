package com.wordpuzzle.Examples.Chap_11.hello;

import java.rmi.*;
import javax.ejb.*;

public interface Hello extends EJBObject
{
   public String greet() throws RemoteException;
}
