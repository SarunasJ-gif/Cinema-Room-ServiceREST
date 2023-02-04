package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Room {
    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumns;
    @JsonProperty("available_seats")
    private Set<Seat> availableSeats;

    @JsonIgnore
    private Set<ReservedSeat> reservedSeatsList;

    @JsonIgnore
    private int totalIncomes;

    public Room() {}
    public Room(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new TreeSet<>();
            for (int i = 1; i <= totalRows; i++) {
                for (int j = 1; j <= totalColumns; j++) {
                    Seat seat = new Seat(i, j);
                    availableSeats.add(seat);
                }
            }
        this.reservedSeatsList = new HashSet<>();
            this.totalIncomes = availableSeats.stream().map(Seat::getPrice).reduce(Integer::sum).orElse(0);
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public Set<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Set<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void removeSeat(Seat seat) {
        availableSeats.remove(seat);
    }

    public void addSeat(Seat seat) {
        availableSeats.add(seat);
    }

    public Set<ReservedSeat> getReservedSeatsList() {
        return reservedSeatsList;
    }

    public void setReservedSeatsList(Set<ReservedSeat> reservedSeatsList) {
        this.reservedSeatsList = reservedSeatsList;
    }

    public void removeReservedSeat(ReservedSeat reservedSeat) {
        reservedSeatsList.remove(reservedSeat);
    }

    public void addReservedSeats(ReservedSeat reservedSeat) {
        reservedSeatsList.add(reservedSeat);
    }

    public int getTotalIncomes() {
        return totalIncomes;
    }
}
