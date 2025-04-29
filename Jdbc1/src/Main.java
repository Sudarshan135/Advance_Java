
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String name = "temporary";
        String newUrl = url + name;

        Connection connection = Database.getCon(newUrl);

        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {

            System.out.println("1. Create Database ");
            System.out.println("2. Create Table");
            System.out.println("3. Insert data in  Table");
            System.out.println("4. Display table data ");
            System.out.println("5. Exit ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Database.createDatabase();
                    break;
                case 2:
                    Database.createTable("Data");
                    break;
                case 3:
                    insert();
                    break;
                case 4:
                    Database.display();
                    break;
                case 5:
                    exit(connection);
                    break;
                // Exit the program
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }

    }

    public static void insert() {
        int cont = 1;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Do u want to Enter data 1/0 ");
            cont = Integer.parseInt(br.readLine());

            while (cont == 1) {
                System.out.println("Enter Name : ");
                String sName = br.readLine();

                System.out.println("Enter City : ");
                String sCity = br.readLine();
                Database.insert(sName, sCity);

                System.out.println("Do u want to Enter data 1/0 ");
                cont = Integer.parseInt(br.readLine());

                if (cont != 1 && cont != 0) {
                    System.out.println("Invalid input. Assuming you don't want to continue.");
                    cont = 0;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exit(Connection connection) {
        System.out.println("Exiting the program.");
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}