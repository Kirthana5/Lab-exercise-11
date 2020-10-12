/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labex_11;

import java.util.Scanner;

/**
 *
 * @author Kirthana Balasubramanian
 */
public class bank_application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic here
        
        //obj creation
        balance bal=new balance();
        credit cc=new credit(bal,5000);
        debit dd=new debit(bal,6500);       
    }
    
}

//common thread
class balance
{
    boolean value=false;
    double balance=150000;
    synchronized void credit_amount(double amount)
    {
        while(value)
        try
        {
            wait();
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
        System.out.println("=======================\nBANK BALANCE: Rs."+balance);
        balance+=amount;
        System.out.println("Balance after crediting Rs."+amount+" : Rs."+balance);
        value=true;
        notify();    
    }
    
    synchronized public void debit_amount(double amount)
    {
        while(!value)
            try{
                wait();
            }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
        System.out.println("=======================\nBANK BALANCE: Rs."+balance);
        balance-=amount;
        System.out.println("Balance after debited Rs."+amount+" : Rs."+balance);
        value=false;
        notify();
       
      
    }
    
}

//thread 1
class credit extends Thread
{
    Scanner val=new Scanner(System.in);
    double amount;
    balance b;

    public credit(balance b,double amount) {
        this.b = b;
        this.amount=amount;
        start();
    }
    
    public void run()
    {
        
        b.credit_amount(amount);
        
    }
}

//thread 2
class debit extends Thread
{
    Scanner val=new Scanner(System.in);
    double amount;
    balance b;

    public debit(balance b,double amount) {
        this.b = b;
        this.amount=amount;
        start();
    }
    
    public void run()
    {
      
        b.debit_amount(amount);
        
    }
}