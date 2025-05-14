package ticketing;

import operations.OverbookingException;
import java.util.Scanner;
import java.io.*;

public class Ticket implements Bookable, Cancelable {
    Customer customer;
    Boat boat;
    int sBooked;
    String timing,confirm;
    float charges;

    public Ticket(Customer customer, Boat boat) {
        this.customer = customer;
        this.boat = boat;
    }

    public void bookTicket() throws OverbookingException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select timing:\n1. 5pm to 6pm\n2. 6pm to 7pm\n3. 7pm to 8pm\n4. 8pm to 9pm");

        int timech = sc.nextInt();
        sc.nextLine();

        switch (timech) {
            case 1:
                timing = Schedule.timings[0];
                break;
            case 2:
                timing = Schedule.timings[1];
                break;
            case 3:
                timing = Schedule.timings[2];
                break;
            case 4:
                timing = Schedule.timings[3];
                break;
            default:
                timing = Schedule.timings[0];
                break;
        }
        System.out.print("Enter number of seats to book: ");
        sBooked = sc.nextInt();
        sc.nextLine();

        if (sBooked > boat.capacity) {
            throw new OverbookingException("Only " + boat.capacity + " seats available for " + boat.type);
        }

        charges = sBooked * 70;

        System.out.println("Confirm Ticket? Y/N");
        confirm= sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            System.out.println("Booking successful! Receipt saved in sales.txt");
            saveToLog();

        } else {
            cancelTicket();
        }
    }

    public void cancelTicket() {
        System.out.println("Ticket cancelled for " + customer.name);
    }

    void saveToLog() {
        try (FileWriter w = new FileWriter("sales.txt", true)) {
            w.write("----- Ticket Receipt -----\n");
            w.write("Name: " + customer.name + "\n");
            w.write("Phone: " + customer.phone + "\n");
            w.write("Boat Type: " + boat.type + "\n");
            w.write("Seats Booked: " + sBooked + "\n");
            w.write("Timing: " + timing + "\n");
            w.write("Total Cost: Rs. " + charges + "\n");
            w.write("--------------------------\n\n");
        } catch (IOException e) {
            System.out.println("Failed to write to sales log.");
        }
    }
}
