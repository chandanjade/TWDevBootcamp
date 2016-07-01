package com.bootcamp.parking.strategy;

import com.bootcamp.parking.NoParkingSpaceAvailableException;
import com.bootcamp.parking.ParkingLot;

import java.util.List;

public interface ParkingLotSelectionStrategy {
    ParkingLot getParkingLot(List<ParkingLot> parkingLots) throws NoParkingSpaceAvailableException;
}
