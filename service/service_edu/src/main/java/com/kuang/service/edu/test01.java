package com.kuang.service.edu;

import java.util.Scanner;

public class test01 {
    static Object o = new Object();
    static int count = 1;
    static int m;
    static int n;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] nums = line.split(" ");
        m = Integer.parseInt(nums[0]);
        n = Integer.parseInt(nums[1]);
        for (int i=0;i<n;i++)
        {
            new Thread(()->{
                while (count<m){
                    synchronized (o){
                        while (!((count-1)%n == Integer.parseInt(Thread.currentThread().getName()))){
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (count!=(m+1)){
                            System.out.println(count);
                            count++;
                        }else {
                            break;
                        }
                        o.notifyAll();
                    }
                }
            },""+i).start();
        }
    }
}
