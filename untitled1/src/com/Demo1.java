package com;

import sun.text.resources.cldr.es.FormatData_es_419;

public class Demo1 {

    public static void main(String[] args) {

}
}
class ThreadDemo extends Thread {
    public ThreadDemo(String str){


    }
    public void run(){
        for (int i = 0; i < 30; i++) {
            System.out.println(""+this.getName());
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            System.out.println("  / end...");
        }
    }
}
