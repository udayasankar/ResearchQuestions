package com.company.overlapping;

import java.util.*;

public class MaxMeetings {
    public static void main(String[] args)
    {
        List<Integer> s = Arrays.asList(1, 3, 0, 5, 8, 5 );
        List<Integer> f = Arrays.asList( 2, 4, 6, 7, 9, 9);
        MaxMeetingService maxMeetingService=new MaxMeetingService();
        System.out.println(maxMeetingService.findMaxAttend(s,f));
    }
}

class MaxMeetingService
{
    public int findMaxAttend(List<Integer> s,List<Integer> f)
    {
        List<Service> ls=new ArrayList<>();
        int count=0;
        for(int i=0;i<s.size();i++)
        {
            Service service=new Service();
            service.setSt(s.get(i));
            service.setEt(f.get(i));
            ls.add(service);
        }
        TimeService timeService=new TimeService();
        Collections.sort(ls, timeService);
        for(int i=0;i<ls.size();i++)
        {
            System.out.println(ls.get(i).getSt()+" : "+ls.get(i).getEt());
        }
        int tempT=ls.get(0).getEt();
       for(int i=1;i<s.size();i++)
       {
            if(ls.get(i).getSt()>tempT)
            {
                tempT = ls.get(i).getEt();
                count++;
            }
       }
       return count;
     }
}

class Service
{
    int st;
    int et;

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public int getEt() {
        return et;
    }

    public void setEt(int et) {
        this.et = et;
    }
}

class TimeService implements Comparator<Service>
{
    @Override
    public int compare(Service o1, Service o2) {
        if(o1.getEt()<o2.getEt()) return -1;
        else if(o1.getEt()>o2.getEt()) return 1;
        return 0;
    }
}
