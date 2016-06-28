package com.bootcamp.parking;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private int capacity;
    private Set<ParkingTicket> issuedParkingTickets;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        issuedParkingTickets = new HashSet<>();
    }

    public ParkingTicket park() throws NoParkingSpaceAvailableException {
        if (issuedParkingTickets.size() >= capacity) throw new NoParkingSpaceAvailableException();
        ParkingTicket parkingTicket = new ParkingTicket(new Date().getTime());
        issuedParkingTickets.add(parkingTicket);
        return parkingTicket;
    }

    public boolean unPark(ParkingTicket parkingTicket) {
        return issuedParkingTickets.remove(parkingTicket);
    }

}
