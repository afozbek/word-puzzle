package com.wordpuzzle.Examples.Chap_11.sales;

import java.rmi.*;
import javax.ejb.*;

public interface Stock extends EJBObject 
{
   public String getCode() throws RemoteException;
   public void setCode(String code) throws RemoteException;
   public int getLevel() throws RemoteException;
   public void setLevel(int level) throws RemoteException;
}
