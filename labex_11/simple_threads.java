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
public class simple_threads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        //obj creation
        Number no=new Number();
        
        //passing obj of thread 1 to thread 2,3
        div_by_five dd=new div_by_five(no);      
        average aa=new average(no);
                 
    }    
}

//Thread 1----------->Getting N numbers as Input
class Number implements Runnable
{
    Scanner val=new Scanner(System.in);
    int N,num[];

    public Number() 
    {
        System.out.println("Enter N value:");
        N=val.nextInt();
        num=new int[N];
        new Thread(this).start();
    }
  
    public void run()
    {
        System.out.println("Enter "+N+" numbers:");
        for(int i=0;i<N;i++)
        {
            num[i]=val.nextInt();
        }
    }     
}

//Thread 2------------->Printing numbers that are divisible by 5
class div_by_five implements Runnable
{
    Number n;
    
    public div_by_five(Number n) 
    {
        this.n=n;
        new Thread(this).start();        
    }
    
    public void run()
    {
        try 
        {
            Thread.sleep(8000);
        } 
        catch (InterruptedException e) 
        {
            System.out.println(e);
        }
        System.out.println("Numbers divisible by 5 are:");
        for(int i:n.num)
        {
            if(i%5==0)
                System.out.print(i+" ");
        }
    }
}

//Thread 3-------->Computing average
class average implements Runnable
{
    Number n;
    int sum=0;
    public average(Number n) {
        this.n=n;
        new Thread(this).start();
    }
    
    public void run()
    {        
        try 
        {
            Thread.sleep(8000);
        }
        catch (InterruptedException e) 
        {
            System.out.println(e);
        }
        for(int i:n.num)
        {
           sum+=i;
        }
        System.out.println("\nAverage: "+(double)(sum/n.num.length));
    }
}