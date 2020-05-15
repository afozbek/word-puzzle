package com.wordpuzzle.Examples.Chap_11.sales;

public class StockPK implements java.io.Serializable
{
   public String code;

   public StockPK()
   {
   	//Default constructor.
   }

   public StockPK(String code)
   {
      this.code = code;
   }

   public boolean equals(Object obj)
   {
      //Check that reference exists and is of correct type...
      if ((obj==null) || (!(obj instanceof StockPK))
         return false;
      else if (((StockPK)obj).code == code)
         return true;
      else
         return false;
   }

   public int hashCode()
   {
      return code.hashCode();
   }
}
