/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.icetask;

/**
 *
 * @author ivang
 */
public class IceTask {

    public static void main(String[] args) {
        int [][] sales = {{100, 150, 70}, {88, 92, 103}, {75, 45, 90}, {65, 95, 175}};
        String [] quarters = {"Q1", "Q2", "Q3", "Q4"};
        String [] brands = {"NIKE", "ADIDAS", "REEBOK"};
        
        int numBrands = brands.length;
        int [] totals = new int[numBrands];
        double [] averages = new double[numBrands];
        int[] min = new int[numBrands];
        int [] max = new int[numBrands];
 
        System.out.println("ULTIMATE SHOE SALES");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-15s", "QUARTER");
        for (int brand = 0; brand < brands.length; brand++){
            System.out.printf("%-15s", brands[brand]);
        }
        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        for (int quarter = 0; quarter < sales.length; quarter++){
            System.out.printf("%-15s", quarters[quarter]);
            for (int brand = 0; brand < sales[quarter].length; brand++){
                System.out.printf("%-15d", sales[quarter][brand]);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------");
        
        for ( int brand = 0; brand < numBrands; brand++){
            min[brand] = sales[0][brand];
            max[brand] = sales[0][brand];
        }
        for (int brand = 0 ; brand < numBrands; brand++){
            for (int quarter = 0; quarter < sales.length; quarter++){
                int value = sales[quarter][brand];
                totals[brand] += value;
                
                if ( value < min[brand]){
                    min[brand] = value;
                }
                if (value > max[brand]){
                    max[brand] = value;    
                            
                }
            }
            averages[brand] = (double) totals[brand] / sales.length;
        }
        System.out.printf("%-15s", "TOTAL:");
        for (int totalValue : totals){
            System.out.printf("%-15d", totalValue);
        }
        System.out.println();
        
        System.out.printf("%-15s", "AVERAGE:");
        for(double average : averages){
            System.out.printf("%-15.1f", average);
        }
        System.out.println();
        
        System.out.printf("%-15s", "MIN:");
        for (int minValue : min){
            System.out.printf("%-15d", minValue);
        }
        System.out.println();
        
        System.out.printf("%-15s", "MAX:");
        for (int maxValue : max){
            System.out.printf("%-15d", maxValue);
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------");
    }
}
