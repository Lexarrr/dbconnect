package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");
        String address = "jdbc:postgresql://localhost:5432/baidarki2";
        String user = "postgres";
        String pwd = "2121";
        Connection con = connectToDB(address, user, pwd);
//        insertPassenger(con, "Fisher", 90);
//        insertKayak(con, 250);
//        callListOfPass(con);
//        callListOfKayak(con);
        insertPassInKayak(con, callListOfPass(con), callListOfKayak(con));
    }

    public static Connection connectToDB(String ad, String us, String pw) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(ad, us, pw);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("Not connected" + ad);
        }

        return connection;
    }

    public static void insertPassenger(Connection con, String fio, int weight) {
        String s = "insert into passenger(fio, weight) values('" + fio + "', " + weight + ");";
        System.out.println(s);

        try {
            con.createStatement().execute(s);
        } catch (SQLException e) {
            System.out.println("the " + s + " failed to execute");
            System.out.println(e.getMessage());
        }
    }

    public static void insertKayak(Connection con, int max_w) {
        String s = "";
        ConditonKayak cond = ConditonKayak.Новая;

        if (max_w < 200) {
            cond = ConditonKayak.В_ремонте;
        }
        if (max_w == 200) {
            cond = ConditonKayak.Исправна;
        }
        s = "insert into kayak(max_weight, condition_kayak) values(" + max_w + ", '" + cond + "'" + ");";
        System.out.println(s);
        try {
            con.createStatement().execute(s);
        } catch (SQLException e) {
            System.out.println("the " + s + " failed to execute");
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<Passenger> callListOfPass(Connection con) {

        String pass = "SELECT id, fio, weight FROM passenger order by weight;";
        int countpassenger = 0;
        ResultSet rsPass;


        ArrayList<Passenger> passengers = null;
        try {
            rsPass = con.createStatement().executeQuery(pass);

            passengers = new ArrayList<Passenger>();
            while (rsPass.next()) {
                int id = rsPass.getInt(1);
                String name = rsPass.getString(2);
                int weight = rsPass.getInt(3);
                passengers.add(new Passenger(id, name, weight));
                countpassenger++;

            }
//            Passenger psw = passengers.get(3);


            System.out.println("Count of Passenger: " + countpassenger);
            System.out.println(passengers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return passengers;
    }

    public static ArrayList<Kayak> callListOfKayak(Connection con) {

        String kayak = "SELECT id, max_weight FROM kayak where max_weight > 0 order by max_weight;";
        int countkayak = 0;

        ResultSet rsKayak = null;
        ArrayList<Kayak> kayaks = null;
        try {
            rsKayak = con.createStatement().executeQuery(kayak);

            kayaks = new ArrayList<>();
            while (rsKayak.next()) {
                int id = rsKayak.getInt(1);
                int max_weight = rsKayak.getInt(2);
                kayaks.add(new Kayak(id, max_weight));
                countkayak++;
            }
            System.out.println("Count of Kayaks: " + countkayak);
            System.out.println(kayaks);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return kayaks;
    }

    public static void insertPassInKayak(Connection con,
                                         ArrayList<Passenger> passengerArrayList,
                                         ArrayList<Kayak> kayakArrayList) {

        String s = "";
        int c = 0;
        Passenger ifw = null;
        Kayak imw = null;
        for (int i = 0; i < passengerArrayList.size(); i++) {
            ifw = passengerArrayList.get(i);
            System.out.println(ifw);
        }
        for (int i = 0; i < kayakArrayList.size(); i++) {
            imw = kayakArrayList.get(i);
            System.out.println(imw);
        }



//        Passenger ifw = null;
//        Kayak imw = kayakArrayList.get(kayakArrayList.size() - 1);
//        if (passengerArrayList != null && !passengerArrayList.isEmpty()) {
//            ifw = passengerArrayList.get(passengerArrayList.size() - 1);
//
//
//        }

//        int id_k;
//        int id_p1;
//        int p1_w = 0;

        int count = 0;
//        Kayak id_k = null;
//        Passenger id_p1 = null;
        int maxwe = imw.max_weight;
//        int countpass = passengerArrayList.size();
        int cp = 0;


        ArrayList<PassInKayak> passInKayakArrayList = new ArrayList<>();
        for (int j = 0; j < passengerArrayList.size(); j++) {
                if (ifw != null && ifw.weight <= maxwe && count < 2) {
                    imw = kayakArrayList.get(count);
                    ifw = passengerArrayList.get(cp);
                    maxwe -= imw.max_weight;
                    passInKayakArrayList.add(new PassInKayak(imw.id, ifw.id));
                    cp++;
                    ifw = passengerArrayList.get(cp);
                }


                if (ifw != null && ifw.weight > maxwe) {
                    imw = kayakArrayList.get(count++);
                    ifw = passengerArrayList.get(cp);
                }
                cp++;
//            ifw = passengerArrayList.get(cp);
                passInKayakArrayList.add(new PassInKayak(imw.id, ifw.id));
                System.out.println(cp);
                System.out.println(passInKayakArrayList);
        }

//        System.out.println(passInKayakArrayList);
    }

}
