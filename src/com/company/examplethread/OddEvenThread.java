package com.company.examplethread;

public class OddEvenThread {
    public static void main(String[] args)
    {
        Printer p=new Printer();
        Thread t1=new Thread(new TaskEvenOdd(p,10,false),"Odd");
        Thread t2=new Thread(new TaskEvenOdd(p,10,true),"Even");
        t1.start();
        t2.start();
    }
}

class TaskEvenOdd implements Runnable
{
    boolean taskFlag;
    Printer p;
    int max;

    public TaskEvenOdd(Printer p,int max, boolean taskFlag)
    {
        this.p=p;
        this.max=max;
        this.taskFlag=taskFlag;
    }

    @Override
    public void run() {
        int num = taskFlag ? 2 : 1;
        while(num<=max)
        {
            if(taskFlag)
            {
                p.printEven(num);
            }
            else
            {
                p.printOdd(num);
            }
            num += 2;
        }

    }
}

class Printer
{
    private  boolean isOdd;

    synchronized void printEven(int num)
    {
        System.out.println(Thread.currentThread().getName() + isOdd);
        while(!isOdd)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + num);
        isOdd = false;
        notify();
    }

    synchronized void printOdd(int num)
    {
        System.out.println(Thread.currentThread().getName() + isOdd);
        while(isOdd)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + num);
        isOdd = true;
        notify();
    }
}
