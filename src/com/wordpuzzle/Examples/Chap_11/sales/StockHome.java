package com.wordpuzzle.Examples.Chap_11.sales;

import javax.ejb.*;
import java.rmi.*;

public interface StockHome extends EJBHome
{
   public Stock create(String code)  
                throws CreateException, RemoteException;
   public Stock findByPrimaryKey(String key)
                throws FinderException, RemoteException;
   //Can provide other 'findBy' methods, if we wish.
   public abstract void remove(Object key)
                throws RemoveException, RemoteException;
}
