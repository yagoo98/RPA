package com.emprogria.rpa.SOE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main<config> {
//    private static final Logger myLogger = LoggerFactory.getLogger(Main.class);
//    static String[] keywords = {
//            "SAP", "ERP",
//            "數位轉型",
//            "資料分析",
//            "RPA"
//    };
//    public static void main1(String[] args) throws IOException {
//        Properties prop = new Properties();
//        prop.load(new FileReader(new File("SOE.properties")));
//        String keywords=prop.getProperty("keywords");
//        String leadings=prop.getProperty("leadings");
//        String driverPath=prop.getProperty("driverPath");
//        String survivalTime=prop.getProperty("survivalTime");
//        String numOfTries= prop.getProperty("numOfTries ");
//        String waitPageTime= prop.getProperty("waitPageTime");
//
//    }


//    String leadings = prop.getProperty("leadings");
//    static int survivalTime = 30;
//    static int numOfTries = 15;
//    private static final String driverPath = "C:/Users/Java/selenium-java-4.1.0/chromedriver.exe";
//    private static final String[] leadings = {
//            "碩益科技",
//            "丰益科技",
//            "陳元勛",
//            "光復南路",
//            "台北市"
//    };

    public static void main(String[] args) throws InterruptedException, IOException {
        Properties prop = new Properties();
        prop.load(new FileReader(new File("SOE.properties")));
        String keyword=prop.getProperty("keywords");
        String[] keywords = keyword.split(",");
        String leading=prop.getProperty("leadings");
        String[] leadings = leading.split(",");
        String driverPath=prop.getProperty("driverPath");
        long survivalTime = Long.parseLong(prop.getProperty("survivalTime"));
        int numOfTries=Integer.parseInt(prop.getProperty("numOfTries"));
        int waitPageTimes=Integer.parseInt(prop.getProperty("waitPageTime"));


        Random random = new Random();

        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(survivalTime, TimeUnit.SECONDS);

        for (int i = 0; i < numOfTries; i++) {
            int pos = random.nextInt(leadings.length);
            int pos2 = random.nextInt(keywords.length);
            int n;
            int[] num = new int[leadings.length];
            int[] num1 = new int[keywords.length];
            int[] arr = new int[pos];
            int[] arr1 = new int[pos2];
            for(int j= 0; j < num.length; j++) {
                num[j] = j ;
            }
            for(int j = 0; j < arr.length; j++) {
                n = (int)(Math.random()*(num.length-j));
                arr[j] = num[n];
                for(int k = n; k < num.length-1; k++) {
                    num[k] = num[k+1];
                }
            }
            for(int j= 0; j < num1.length; j++) {
                num1[j] = j ;
            }
            for(int j = 0; j < arr1.length; j++) {
                n = (int)(Math.random()*(num1.length-j));
                arr1[j] = num1[n];
                for(int k = n; k < num1.length-1; k++) {
                    num1[k] = num1[k+1];
                }
            }
//            System.out.println(Arrays.toString(arr));
            StringBuilder sb=new StringBuilder();
            for (int j = 0; j < arr.length; j++){
                sb.append(leadings[arr[j]]).append(" ");

            }
            for (int j = 0; j < arr1.length; j++){
                sb.append(keywords[arr1[j]]).append(" ");

            }
            String myKeyWords = sb.toString();
//            System.out.println(myKeyWords);
            sb.setLength(0);
//            String myKeyWords = leadings[pos]+ " " + keywords[pos2];

            driver.get("https://www.google.com/search?q=" + myKeyWords);
            long waitPageTime = waitPageTimes*3 + random.nextInt(1000 * (keywords.length + leadings.length));
            Thread.sleep(waitPageTime);
        }

        driver.quit();
    }
}
