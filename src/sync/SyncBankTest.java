/* 
Project:
Purpose Details:
Course:
Author:
Date Developed:
Last Date Changed:
Revision:
*/

package sync;

import sync.Bank;

/**
 * This program shows how multiple threads can safely access a data structure.
 * @version 1.31 2015-06-21
 * @author Cay Horstmann
 */
public class SyncBankTest
{
   public static final int NACCOUNTS = 10;
   public static final double INITIAL_BALANCE = 1000;
   public static final double MAX_AMOUNT = 2000;
   public static final int DELAY = 10;
   
   public static void main(String[] args) throws Exception
   {
      Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
      for (int i = 0; i < NACCOUNTS; i++)
      {
         int fromAccount = i;
         Runnable r = () -> {
            try
            {
               while (true)
               {
                  int toAccount = (int) (bank.size() * Math.random());
                  double amount = MAX_AMOUNT * Math.random();
                  bank.transfer(fromAccount, toAccount, amount);
                  Thread.sleep((int) (DELAY * Math.random()));
               }
            }
            catch (InterruptedException e)
            {
            }            
         };
         Thread t = new Thread(r);
         t.start();
      }
   }
}