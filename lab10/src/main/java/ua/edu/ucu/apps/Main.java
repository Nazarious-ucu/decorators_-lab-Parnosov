package ua.edu.ucu.apps;

public class Main {
    public static void main(String[] args) {
        Document sd = new SmartDocument("C:/Users/1/Downloads/luffy-jumping.png" );
        System.out.println(sd.parse());
    }
}
