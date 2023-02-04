package cinema.service;

import cinema.model.ReservedSeat;
import cinema.model.Room;


public class RoomService {

    private final Room room;
    private final ReservedSeat reservedSeat;

    public RoomService(Room room, ReservedSeat reservedSeat) {
        this.room = room;
        this.reservedSeat = reservedSeat;
    }


    public Room findRoom() {
        return room;
    }

    public ReservedSeat findReservedSeat() {
        return reservedSeat;
    }
}
