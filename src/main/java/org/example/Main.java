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
        ResultSet rsPass = null;


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
            Passenger psw = passengers.get(3);


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

            kayaks = new ArrayList<Kayak>();
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
        Passenger ifw = null;
        Kayak imw = kayakArrayList.get(kayakArrayList.size() - 1);
        if (passengerArrayList != null && !passengerArrayList.isEmpty()) {
            ifw = passengerArrayList.get(passengerArrayList.size() - 1);


        }

        int id_k = imw.id;
        int id_p1 = ifw.id;
        int p1_w = ifw.weight;
        int id_p2 = ifw.id;
        int p2_w = ifw.weight;
        Kayak boatnum = kayakArrayList.get(0);

        ArrayList<PassInKayak> passInKayakArrayList = new ArrayList<PassInKayak>();
        for (int j = 0; j < passengerArrayList.size(); j++) { // перебор пассажиров
            if (ifw.weight <= imw.max_weight) {
                passInKayakArrayList.add(new PassInKayak(id_k, id_p1, id_p2));
                j++;
                id_k++;
                if (imw.max_weight - p1_w >= p2_w) {
                    passInKayakArrayList.add(new PassInKayak(id_k, id_p1, id_p2));
                } else {
                    j++;
                }
                if (imw.max_weight - p1_w <= p2_w) {
                        id_k++;

                }
            }


//            passInKayakArrayList.add(new PassInKayak(id_k, id_p1, id_p2));


        }
        System.out.println(passInKayakArrayList);

    }
}


// for (int i = 0; i < rs.getInt(1); i++) {
//        idk = rest.getInt(1);
//        }

//while (rs.next()) {
//
//        count++;
//
//
//        if (rest.getInt(2) + rest.getInt(2) <= rs.getInt(2) && rest.getInt(1) != rest.getInt(1) ){
//        String ins = "insert into pass_in_kayak(id_kayak, id_pass1) values(" + rs.getInt(1) + rest.getInt(1) + + rest.getInt(1) + ");";
//        System.out.println(ins);
//        }
//        else {
//        String ins = "insert into pass_in_kayak(id_kayak, id_pass1) values(" + rs.getInt(1) + rest.getInt(1) + ");";
//        System.out.println(ins);
//        }
//
//        }

//try {
//        ResultSet rs = con.createStatement().executeQuery(kayiweight);
//        ResultSet rest = con.createStatement().executeQuery(passiweight);
//        ResultSet countres = con.createStatement().executeQuery(countPass);
//        ResultSet countrest = con.createStatement().executeQuery(countKayak);
//
//        int coukayak = countrest.getInt(1);
//        int coupas = countres.getInt(1);
//
//        while (countrest.next() && countres.next() && rs.next() && rest.next()){
//        count++;
//
//        for (int i = 0; i < coukayak; i++) {
//        for (int j = 0; j < coupas; j++) {
//        if (rs.getInt(2) > 0) {
//        String ins = "insert into Pass_in_Kayak(id_Kayak, id_pass1) values(" + rs.getInt(1) + ", " + rest.getInt(1) + ");";
//        System.out.println(ins);
//
//        }
//        }
//        }
//        }
//
//        } catch (SQLException e) {
//        System.out.println(e.getMessage());
//        }


// ResultSet resultSet = con.createStatement().executeQuery(kayak);
//
//        for (int i = 0; i < resultSet.getInt(1); i++) {
//        if (resultSet.getInt(3) > 0) {
//        if (i < 1) {
//        int[] ins = new int[resultSet.getInt(1)];
//        System.out.println(Arrays.toString(ins));
//        }
//        }
//        }
//
//while (rsKayak.next() && rsPass.next()) {
//        count++;
//        System.out.println(count);
//        if (count < 1) {
//        System.out.println("id_k: " + rsKayak.getInt(1) + "id_p: " + rsPass.getInt(1));
//        } else {
//        for (int i = 1; i <= coukayak; i++) {
//        for (int j = 1; j <= coupas; j++) {
//        if (rsKayak.getInt(2) > 0) {
//        s = "insert into Pass_in_Kayak(id_Kayak, id_pass1) values(" + rsKayak.getInt(1) + ", " + rsPass.getInt(1) + ");";
//        System.out.println(s);
//        con.createStatement().execute(s);
//
//        }
//        }
//        }
//        }
//
//        }