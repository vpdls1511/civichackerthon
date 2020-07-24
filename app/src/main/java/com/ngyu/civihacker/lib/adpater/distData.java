package com.ngyu.civihacker.lib.adpater;

import java.util.Comparator;

public class distData implements Comparable<distData> {
    String title;
    String addr;
    String content;
    Double dis;

    public distData(String title, String addr, String content, Double dis){
        this.title = title;
        this.addr = addr;
        this.content = content;
        this.dis = dis;
    }

    public String title(){
        return title;
    }
    public String addr(){
        return addr;
    }
    public String content(){
        return content;
    }
    public Double dis(){
        return dis;
    }

    @Override
    public int compareTo(distData o) {
        if(this.dis < o.dis){
            return -1;
        }else if(this.dis > o.dis){
            return 1;
        }else{
            return 0;
        }
    }
}
