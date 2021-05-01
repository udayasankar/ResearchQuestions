package com.company.examplethread;

import java.awt.*;
import java.util.concurrent.Semaphore;

public class OddEven {
    public static void main(String[] args)
    {
        OddEvenFacade oe=new OddEvenFacade();
        Thread t1=new Thread(new EvenPrinting(oe,10),"Even");
        Thread t2=new Thread(new OddPrinting(oe,10),"Odd");

        t1.start();
        t2.start();
    }
}

class OddEvenFacade
{
    Semaphore even = new Semaphore(1);
    Semaphore odd = new Semaphore(0);

    public void printEven(int n)
    {
        try {
            even.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" "+n+" ");
        odd.release();
    }

    public void printOdd(int n)
    {
        try {
            odd.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" "+n+" ");
        even.release();
    }
}

class EvenPrinting implements  Runnable
{
    int n;
    OddEvenFacade oe;
    public EvenPrinting(OddEvenFacade oe,int n)
    {
        this.n= n;
        this.oe=oe;
    }

    @Override
    public void run() {
        for(int i =2;i<=n;i+=2)
        {
            oe.printEven(i);
        }

    }
}

class OddPrinting implements Runnable
{
    int n;
    OddEvenFacade oe;
    public OddPrinting(OddEvenFacade oe,int n)
    {
        this.n=n;
        this.oe=oe;
    }

    @Override
    public void run() {
        for(int i =1;i<=n;i+=2)
        {
            oe.printOdd(i);
        }
    }
}
