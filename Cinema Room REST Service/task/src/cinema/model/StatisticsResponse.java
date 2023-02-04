package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsResponse {

    @JsonProperty("current_income")
    private int currentIncomes;
    @JsonProperty("number_of_available_seats")
    private int availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int purchasedTickets;

    public StatisticsResponse(int currentIncomes, int availableSeats, int purchasedTickets) {
        this.currentIncomes = currentIncomes;
        this.availableSeats = availableSeats;
        this.purchasedTickets = purchasedTickets;
    }

    public int getCurrentIncomes() {
        return currentIncomes;
    }

    public void setCurrentIncomes(int currentIncomes) {
        this.currentIncomes = currentIncomes;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(int purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }
}
