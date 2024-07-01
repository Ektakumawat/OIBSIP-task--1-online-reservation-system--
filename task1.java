import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class task1 {
    // Data storage
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, String> reservations = new HashMap<>();
    private static Map<String, String> cancellations = new HashMap<>();

    public static void main(String[] args) {
        // Predefined users
        users.put("user1", "password1");
        users.put("user2", "password2");

        Scanner scanner = new Scanner(System.in);

        // Login form
        System.out.println("Welcome to the Online Reservation System");
        System.out.print("Enter login username: ");
        String loginID = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authenticate(loginID, password)) {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("\n1. Make a Reservation");
                System.out.println("2. Cancel a Reservation");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                if (choice == 1) {
                    makeReservation(scanner);
                } else if (choice == 2) {
                    cancelReservation(scanner);
                } else if (choice == 3) {
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid login ID or password.");
        }

        scanner.close();
    }

    private static boolean authenticate(String loginID, String password) {
        return users.containsKey(loginID) && users.get(loginID).equals(password);
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter departure place: ");
        String from = scanner.nextLine();
        System.out.print("Enter destination place: ");
        String to = scanner.nextLine();

        String reservationDetails = "Name: " + name + ", Train Number: " + trainNumber + ", Train Name: " + trainName +
                ", Class Type: " + classType + ", Date of Journey: " + dateOfJourney +
                ", From: " + from + ", To: " + to;
        String pnrNumber = generatePNR();
        reservations.put(pnrNumber, reservationDetails);

        System.out.println("Reservation successful! Your PNR number is " + pnrNumber);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter your PNR number: ");
        String pnrNumber = scanner.nextLine();

        if (reservations.containsKey(pnrNumber)) {
            System.out.println("Reservation Details: " + reservations.get(pnrNumber));
            System.out.print("Do you want to cancel this reservation? (yes/no): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                cancellations.put(pnrNumber, reservations.remove(pnrNumber));
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR number.");
        }
    }

    private static String generatePNR() {
        return "PNR" + (reservations.size() + 1);
    }
}