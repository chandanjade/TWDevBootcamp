package com.bootcamp.parking;

import java.util.*;

public class ParkingLot {
    private final int capacity;
    private List<NotificationsSubscriber> subscribers;
    private Set<ParkingTicket> issuedParkingTickets;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.subscribers = new ArrayList<>();
        this.issuedParkingTickets = new HashSet<>();
    }

    public void addNotificationsSubscriber(NotificationsSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public ParkingTicket park(Object vehicle) throws NoParkingSpaceAvailableException {
        if (isFull())
            throw new NoParkingSpaceAvailableException("Parking lot full !!");

        ParkingTicket parkingTicket = new ParkingTicket(new Date().getTime());

        issuedParkingTickets.add(parkingTicket);

        if (isFull())
            sendParkingFullNotification();

        return parkingTicket;
    }

    public boolean unPark(ParkingTicket parkingTicket) {
        boolean isFullBeforeUnPark = isFull();
        boolean isUnParked = issuedParkingTickets.remove(parkingTicket);
        if (isFullBeforeUnPark && isUnParked) {
            sendParkingAvailableNotification();
        }
        return isUnParked;
    }

    private void sendParkingFullNotification() {
        subscribers.forEach(NotificationsSubscriber::parkingFull);
    }

    private void sendParkingAvailableNotification() {
        subscribers.forEach(NotificationsSubscriber::parkingAvailable);
    }

    boolean isFull() {
        return issuedParkingTickets.size() >= capacity;
    }
}
