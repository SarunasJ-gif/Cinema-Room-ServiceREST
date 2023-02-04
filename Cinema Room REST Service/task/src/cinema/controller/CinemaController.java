package cinema.controller;

import cinema.model.StatisticsResponse;
import cinema.service.RoomService;
import cinema.model.ReservedSeat;
import cinema.model.Room;
import cinema.model.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//@RequestMapping("/cinema")
public class CinemaController {

    RoomService service = new RoomService(new Room(9, 9), new ReservedSeat());


    @GetMapping("/seats")
    public Room getSeats() {
        return service.findRoom();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeats(@RequestBody Seat seat) {
        Room room = service.findRoom();
        ReservedSeat reservedSeat = service.findReservedSeat();
        if (seat.getRow() > room.getTotalRows() ||
                seat.getColumn() > room.getTotalColumns()
                || seat.getRow() <= 0 || seat.getColumn() <= 0) {
            return ResponseEntity.badRequest().
                    body(Map.of("error", "The number of a row or a column is out of bounds!"));
        } else {
            if (room.getAvailableSeats().contains(seat)) {
                room.removeSeat(seat);
                String token = String.valueOf(UUID.randomUUID());
                reservedSeat.setToken(token);
                reservedSeat.setTicket(seat);
                room.addReservedSeats(reservedSeat);
                return ResponseEntity.status(HttpStatus.OK).body(reservedSeat);
            } else {
                return ResponseEntity.badRequest().
                        body(Map.of("error", "The ticket has been already purchased!"));
            }
        }
    }


    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody String token) {
        Room room = service.findRoom();
            String tkn = token.split(":")[1].replaceAll("\"", "").replace("}", "");
            for (ReservedSeat ticket : room.getReservedSeatsList()) {
                if (ticket.getToken().equals(tkn)) {
                    room.addSeat(ticket.getTicket());
                    room.removeReservedSeat(ticket);
                    return ResponseEntity.status(HttpStatus.OK).body(Map.of("returned_ticket", ticket.getTicket()));
                }
            }
            return ResponseEntity.badRequest().
                    body(Map.of("error", "Wrong token!"));

    }


    @PostMapping("/stats")
    public ResponseEntity<?> adminAccount(@RequestParam(value = "password",
            required = false) String password) {
        Room room = service.findRoom();
        final String CORRECT_PASSWORD = "super_secret";
        if (password == null || !password.equals(CORRECT_PASSWORD)) {
            return ResponseEntity.status(401).body(Map.of("error", "The password is wrong!"));
        } else {
//                    Optional<Integer> incomes = room.getReservedSeatsList().stream()
//                            .map(x -> x.getTicket().getPrice()).reduce(Integer::sum);
//                    int currentIncomes = incomes.orElse(8);
            Optional<Integer> incomes = room.getAvailableSeats().stream().map(Seat::getPrice).reduce(Integer::sum);
            int ctIncomes = incomes.orElse(8);
            int totalIncomes = room.getTotalIncomes();
            int currentIncomes = totalIncomes - ctIncomes;
            int numberOfAvailableSeats = room.getAvailableSeats().size();
            int numberOfPurchasedTickets = room.getReservedSeatsList().size();
            StatisticsResponse statistics = new StatisticsResponse(
                    currentIncomes, numberOfAvailableSeats, numberOfPurchasedTickets);
            return ResponseEntity.status(HttpStatus.OK).body(statistics);
            }
        }
}
