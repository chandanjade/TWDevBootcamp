package com.bootcamp.parking;

public class NoParkingSpaceAvailableException extends Exception {
    public NoParkingSpaceAvailableException(String msg) {
        super(msg);
    }
}
