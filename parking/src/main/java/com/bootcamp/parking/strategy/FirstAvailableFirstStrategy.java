package com.bootcamp.parking.strategy;

import com.bootcamp.parking.NoParkingSpaceAvailableException;
import com.bootcamp.parking.ParkingLot;

import java.util.List;

public class FirstAvailableFirstStrategy implements ParkingLotSelectionStrategy {

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) throws NoParkingSpaceAvailableException {
        for (ParkingLot parkingLot: parkingLots) {
            if(parkingLot.isNotFull()) {
                return parkingLot;
            }
        }
        throw new NoParkingSpaceAvailableException("No parking lots available");
    }
}
