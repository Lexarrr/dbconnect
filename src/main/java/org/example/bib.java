package org.example;

import java.sql.*;

public class bib {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String address = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pwd = "2121";
        Connection con = connectToDB(address, user, pwd);

        getCountTitleForYear(con);
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
    //    public static void insertReader(Connection con, String fio) {
//        String q = "insert into reader(fio) values( '" + fio + "' );";
//        System.out.println(q);
//        try {
//            con.createStatement().execute(q);
//        } catch (SQLException e) {
//            System.out.println("запрос "+q+" не удалось выполнить");
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static int getReaderCount(Connection con) {
//        String q = "SELECT COUNT(*) FROM reader";
//        System.out.println(q);
//        int amount = -999;
//        try {
//            ResultSet rs = con.createStatement().executeQuery(q);
//            if(rs.next())
//            {
//                amount = rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            System.out.println("запрос "+q+" не удалось выполнить");
//            System.out.println(e.getMessage());
//        }
//        return amount;
//    }
//
//    public static void readReaders(Connection con) {
//        String q = "SELECT * FROM reader";
//        System.out.println(q);
//        int i =0;
//        String name = "";
//        try {
//            ResultSet rs = con.createStatement().executeQuery(q);
//            while(rs.next())
//            {
//                i = rs.getInt(1);
//                name = rs.getString(2);
//                System.out.println("    "+i +"     "+name);
//            }
//
//        } catch (SQLException e) {
//            System.out.println("запрос "+q+" не удалось выполнить");
//            System.out.println(e.getMessage());
//        }
//
//    }

    public static void getTitleOfBook(Connection con) throws SQLException {

        Statement statement = con.createStatement();

        ResultSet resultSet = statement.executeQuery("Select title from book");
        while (resultSet.next()) {

            String ti = resultSet.getString("title");
            System.out.println(ti);

        }

//        for (int i = 0; i <= resultSet.getMetaData().getColumn(); i++) {
//
//        }


    }

    public static int getCountTitleForYear(Connection con) {
        String q = "SELECT title, \"year\" FROM book group by \"year\", title order by \"year\";";
        System.out.println(q);
        int y = -999;
        int count = 0;
        String title = "";
        try {
            ResultSet rs = con.createStatement().executeQuery(q);
            while (rs.next()) {
                count++;
                if (rs.getInt(2) != y) {
                    if (count<1)
                        System.out.println("year: " + y + " title: " + title);
                    y = rs.getInt(2);
                    title = rs.getString(1);


                }
                else {
                    title += ", " + rs.getString(1);
                }
                System.out.println("year: " + y + " title: " + title);
            }

            System.out.println(count);

        } catch (SQLException e) {
            System.out.println("запрос " + q + " не удалось выполнить");
            System.out.println(e.getMessage());
        }
        return y;
    }

//    public static void loopTitleForYear(){
//
//        for (int i = 0; i <= resultSet.getMetaData().getColumn(); i++) {
//
//        }
//
//    }
}
