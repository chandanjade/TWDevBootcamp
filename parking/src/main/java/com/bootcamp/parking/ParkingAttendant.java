package com.bootcamp.parking;

import com.bootcamp.parking.strategy.EvenlyDistributionStrategy;
import com.bootcamp.parking.strategy.ParkingLotSelectionStrategy;

import java.util.List;

public class ParkingAttendant {

    private final List<ParkingLot> parkingLots;
    private final ParkingLotSelectionStrategy parkingStrategy;

    public ParkingAttendant(List<ParkingLot> parkingLots, ParkingLotSelectionStrategy strategy) {
        this.parkingLots = parkingLots;
        this.parkingStrategy = strategy;
    }

    public ParkingTicket park(Object vehicle) throws NoParkingSpaceAvailableException {
        if (this.parkingLots == null || this.parkingLots.isEmpty()) {
            throw new NoParkingSpaceAvailableException("No parking lots available to park");
        }
        ParkingLot parkingLot = parkingStrategy.getParkingLot(parkingLots);

        return parkingLot.park(vehicle);
    }


}
