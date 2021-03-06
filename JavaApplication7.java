
package javaapplication7;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;

/**
 *
 * @author ASUS
 */
public class JavaApplication7 {
    public static Model fit(double[] data, double alpha, double beta)
    {
        validateParams(alpha, beta);                       

        double[] smoothedData = new double[data.length];    

        double[] trends = new double[data.length + 1];
        double[] levels = new double[data.length + 1];

        
        smoothedData[0] = data[0];
        trends[0] = data[1] - data[0];
        levels[0] = data[0];

        for (int t = 0; t < data.length; t++) {
            smoothedData[t] = trends[t] + levels[t];
            levels[t+1] = alpha * data[t] + (1 - alpha) * (levels[t] + trends[t]);
            trends[t+1] = beta * (levels[t+1] - levels[t]) + (1 - beta) * trends[t];
        }
        return new Model(smoothedData, trends, levels, calculateSSE(data,smoothedData));
    }

    private static double calculateSSE(double[] data, double[] smoothedData) {
        double sse = 0;
        for (int i = 0; i < data.length; i++) {
            sse+= Math.pow(smoothedData[i] - data[i],2);
        }
        return sse;
    }

    private static void validateParams(double alpha, double beta) {
        if (alpha < 0 || alpha > 1) {
            throw new RuntimeException("The value of alpha must be between 0 and 1");
        }

        if (beta < 0 || beta > 1) {
            throw new RuntimeException("The value of beta must be between 0 and 1");
        }
    }
    
    public static void main(String[] args) {
        double list1[]=new double[100];
         double list2[]=new double[100];
         
        try{
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/prodb","root","");
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery("select * from shortagedata");
         int i=0;
         while(rs.next())
         {
            list1[i]=parseInt(rs.getString(1));
            list2[i]=parseDouble(rs.getString(2)); 
           //System.out.println(rs.getString(1)+"   "+rs.getString(2));
           System.out.println(list1[i]+"   "+list2[i]);  
           i++;
         }
        
          
     }catch(Exception e)
     {
        System.out.println(e.getMessage());
     }
        double testData[];
        testData = list2;
                

        Model model = JavaApplication7.fit(testData, 0.8, 0.2);

        System.out.println("Input values: " + Arrays.toString(testData));
        System.out.println("Smoothed values: " + Arrays.toString(model.getSmoothedData()));
        System.out.println("Trend: " + Arrays.toString(model.getTrend()));
        System.out.println("Level: " + Arrays.toString(model.getLevel()));
        System.out.println("Forecast: " + Arrays.toString(model.forecast(5)));
        System.out.println("Sum of squared error: " + model.getSSE());
    }
     static class Model {
        private final double[] smoothedData;
        private final double[] trends;
        private final double[] levels;
        private final double sse;

        public Model(double[] smoothedData, double[] trends, double[] levels, double sse) {
            this.smoothedData = smoothedData;
            this.trends = trends;
            this.levels = levels;
            this.sse = sse;
        }

        
        double[] forecast(int size) {
            double[] forecastData = new double[size];
            for (int i = 0; i < size; i++) {
                forecastData[i] = levels[levels.length - 1] +2+(i + 1) * -trends[trends.length - 1]*100000;
            }
            return forecastData;
        }

        public double[] getSmoothedData() {
            return smoothedData;
        }

        public double[] getTrend() {
            return trends;
        }

        public double[] getLevel() {
            return levels;
        }

        public double getSSE() {
            return sse;
        }
     }
}




