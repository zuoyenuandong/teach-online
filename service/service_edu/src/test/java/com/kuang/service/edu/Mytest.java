package com.kuang.service.edu;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Mytest {

    @Test
    public int test01(){
        Scanner scanner = new Scanner(System.in);
        String total = scanner.nextLine();
        int temp;
        int count = 0;
        String goodsLine = scanner.nextLine();
        int totalMoney = Integer.parseInt(total);
        String[] goods = goodsLine.split(" ");
        int[] prices = new int[goods.length];
        for (int i = 0; i < goods.length; i++) {
            prices[i] = Integer.parseInt(goods[i]);
        }
        for (int i = 0; i < goods.length; i++){
            for (int j = i+1; j < goods.length; j++){
                if (prices[i]>prices[j]){
                    temp = prices[i];
                    prices[i] = prices[j];
                    prices[j] = temp;
                }
            }
        }

        for (int i = 0; i < prices.length; i++) {
            if (i==0){
                count = prices[i];
                if (count>totalMoney) return 0;
                continue;
            }
            if (count<totalMoney){
                count+=prices[i];
            }
        }
        return count;
    }

    @Test
    public void test02(){


    }
}
