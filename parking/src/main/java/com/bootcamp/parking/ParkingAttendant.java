package com.bootcamp.parking;

import java.util.List;

public class ParkingAttendant {

    private final List<ParkingLot> parkingLots;

    public ParkingAttendant(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Object vehicle) throws NoParkingSpaceAvailableException {
        if (this.parkingLots == null || this.parkingLots.isEmpty()) {
            throw new NoParkingSpaceAvailableException("No parking lots available to park");
        }

        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isNotFull())
                return parkingLot.park(new Object());

        }

        return null;
    }


}
