package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String address = "jdbc:postgresql://localhost:5432/baidarki2";
        String user = "postgres";
        String pwd = "2121";
        Connection con = connectToDB(address, user, pwd);
        insertPassenger(con, "Данте", 179, 15);
    }
    public static Connection connectToDB(String ad, String us, String pw){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(ad, us, pw);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("Not connected" + ad);
         }

        return connection;
    }

    public static void insertPassenger(Connection con, String fio, int weight, int id){
        String s = "insert into passenger(id, fio, weight) values(" + id + ", '"+ fio + "', " + weight + ");";
        System.out.println(s);

        try {
            con.createStatement().execute(s);
        } catch (SQLException e) {
            System.out.println("the " + s + " failed to execute");
        }
    }

    public static void insertKayak(Connection con){

    }


}
