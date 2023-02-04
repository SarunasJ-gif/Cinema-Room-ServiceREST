package cinema.model;

import java.util.Objects;

public class ReservedSeat {

    private volatile String token;
    private Seat ticket;


    public ReservedSeat() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservedSeat that = (ReservedSeat) o;
        return Objects.equals(token, that.token) && Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, ticket);
    }
}
