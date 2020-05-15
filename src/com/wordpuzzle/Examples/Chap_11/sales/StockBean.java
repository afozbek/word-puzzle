package com.wordpuzzle.Examples.Chap_11.sales;

import javax.ejb.*;

public class StockBean implements EntityBean
{
   public String code;	//Persistent
   public int level;	//data.

   public EntityContext context;

   public StockPK ejbCreate(String code, int level)
   {//(In EJB1.0, return type was void.)
      this.code = code;
      this.level = level;
      return null;
   }

   public void ejbPostCreate(String code, int level)
   {
      StockPK = (StockPK)context.getprimaryKey();
      //Could now carry out initialisation of
      //persistent data, via primary key.
   }

   //The next four methods match the business methods
   //defined in the bean's remote interface...

   public String getCode()
   {
      return code;
   }

   public void setCode(String code)
   {
      this.code = code;
   }

   public int getLevel()
   {
      return level;
   }

   public void setLevel(int level)
   {
      this.level = level;
   }

   public void setEntityContext(EntityContext context)
   {
      this.context = context;
   }

   public void unsetEntityContext()
   {
      context = null;
   }

   public void ejbActivate()
   {
      //When EJB server is started, bean instances are
      //created and placed in a 'pool'.
      //When a bean instance is about to be allocated to
      //a client request (thereby becoming 'active'),
      //this method is called.
      //Method left empty here.
   }

   public void ejbPassivate()
   {
      //Bean is about to be deactivated.
      //Method left empty here.
   }

   public void ejbLoad()
   {
      //Bean's persistent data is about to be read from
      //the database.
      //Method left empty here.
   }

   public void ejbStore()
   {
      //Bean's persistent data is about to be written to
      //the database.
      //Method left empty here.
   }

   public void ejbRemove()
   {
      //Bean is about to be dereferenced, prior to
      //garbage collection.
      //Method left empty here.
   }
}
