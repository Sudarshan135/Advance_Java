//25 April 2025

import java.sql.*;

public class Database {
    static Connection con;

    public static Connection getCon(String url) {

        try {
            //loading driver
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Class loaded");

//            creating connection
            String user = "root";
            String password = "root135";
            con = DriverManager.getConnection(url, user, password);

            if (con.isClosed()) {
                System.out.println("Not Done");
            } else {
                System.out.println("Connection Established ");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }


    public static void createDatabase() {
        String name = "Temporary";
        String query = "Create database if not exists " + name;
        try (
                Statement st = con.createStatement()) {
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void deleteDatabase(String database_name) {
        String sql = "Drop database " + database_name;
        try (Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void showDatabase() {
        String sql = "Show databases";
        try (Statement st = con.createStatement()) {
          ResultSet rs =st.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void createTable(String tName) {
        boolean f = false;
        String sql = "Create Table IF NOT EXISTS " + tName + "( id int primary key AUTO_INCREMENT ,name varchar(22), city varchar(11))";

        try (Statement statement = con.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("Table '" + tName + "' created.");
            f = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(String name, String city) {
        String sql = "Insert into data(name,city) Values(?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, city);
            ps.executeUpdate();
            System.out.println("Inserted: " + name + " - " + city);

        } catch (SQLException s) {
            System.out.println(s);
        }

    }

    public static void display() {
        String tName = "data";
        String sql = "Select * From " + tName;

        try (Statement st = con.createStatement();
        ) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.print(rs.getInt(1) + " : ");
                System.out.print(rs.getString(2) + " : ");
                System.out.print(rs.getString(3) + "\n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("___________________________");
    }

}
