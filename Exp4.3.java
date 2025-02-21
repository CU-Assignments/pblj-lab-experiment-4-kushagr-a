import java.util.*;

class TicketBookingSystem {
    private final boolean[] seats;

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats]; // false means unbooked
    }

    // Synchronized method to prevent double booking
    public synchronized boolean bookSeat(int seatNumber, String userName) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println(userName + ": Invalid seat number!");
            return false;
        }
        if (seats[seatNumber - 1]) {
            System.out.println(userName + ": Seat " + seatNumber + " is already booked!");
            return false;
        }
        seats[seatNumber - 1] = true;
        System.out.println(userName + " booked seat " + seatNumber);
        return true;
    }
}

class User extends Thread {
    private final TicketBookingSystem bookingSystem;
    private final int seatNumber;
    private final String userName;

    public User(TicketBookingSystem bookingSystem, int seatNumber, String userName, int priority) {
        this.bookingSystem = bookingSystem;
        this.seatNumber = seatNumber;
        this.userName = userName;
        setPriority(priority);
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(seatNumber, userName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        int totalSeats = 5;
        TicketBookingSystem system = new TicketBookingSystem(totalSeats);

        // Creating multiple users
        Thread user1 = new User(system, 1, "Anish (VIP)", Thread.MAX_PRIORITY);
        Thread user2 = new User(system, 2, "Kushagra (Regular)", Thread.NORM_PRIORITY);
        Thread user3 = new User(system, 3, "Ayush (VIP)", Thread.MAX_PRIORITY);
        Thread user4 = new User(system, 4, "tushar (Regular)", Thread.MIN_PRIORITY);
        Thread user5 = new User(system, 5, "ishika (VIP)", Thread.MAX_PRIORITY);
        Thread user6 = new User(system, 1, "rohit (Regular)", Thread.NORM_PRIORITY); // Should fail (already booked)

        // Starting all threads
        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();
        user6.start();
    }
}
