package com.bootcamp.parking.strategy;

import com.bootcamp.parking.NoParkingSpaceAvailableException;
import com.bootcamp.parking.ParkingLot;

import java.util.Collections;
import java.util.List;

public class EvenlyDistributionStrategy implements ParkingLotSelectionStrategy{

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) throws NoParkingSpaceAvailableException {
        if(parkingLots != null && parkingLots.size() > 0) {
            Collections.sort(parkingLots, Collections.reverseOrder());
            return parkingLots.get(0);
        }
        throw new NoParkingSpaceAvailableException("No parking lots available");
    }
}
