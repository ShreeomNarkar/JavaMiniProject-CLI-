package ticketing;

public interface Bookable {
    void bookTicket() throws operations.OverbookingException;
}
