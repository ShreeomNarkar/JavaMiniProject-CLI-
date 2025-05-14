package reporting;

import ticketing.*;
import operations.OverbookingException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Boat pedalBoat = new Boat("Pedal Boat", 5);
        Boat motorBoat = new Boat("Sightseeing Motor Boat", 7);
        Boat rowBoat = new Boat("Row Boat", 10);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = sc.nextLine();

        Customer customer = new Customer(name, phone);

        System.out.println("Select boat type:\n1. Pedal Boat\n2. Sightseeing Motor Boat\n3. Row Boat");
        int boatCh = sc.nextInt();
        sc.nextLine(); 

        Boat selectedBoat;
        switch (boatCh) {
            case 1:
                selectedBoat = pedalBoat;
                break;
            case 2:
                selectedBoat = motorBoat;
                break;
            case 3:
                selectedBoat = rowBoat;
                break;
            default:
                selectedBoat = pedalBoat;
        }

        Ticket ticket = new Ticket(customer, selectedBoat);
        try {
            ticket.bookTicket();
        } catch (OverbookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

    }
}
