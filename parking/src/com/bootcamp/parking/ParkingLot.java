package com.bootcamp.parking;

import java.util.*;

public class ParkingLot {
    private int capacity;
    private List<NotificationsSubscriber> subscribers;
    private Set<ParkingTicket> issuedParkingTickets;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.subscribers = new ArrayList<>();
        this.issuedParkingTickets = new HashSet<>();
    }

    public ParkingTicket park() throws NoParkingSpaceAvailableException {
        if (this.isFull())
            throw new NoParkingSpaceAvailableException("Parking lot full !!");

        ParkingTicket parkingTicket = new ParkingTicket(new Date().getTime());
        issuedParkingTickets.add(parkingTicket);

        if (this.isFull())
            sendParkingFullNotification();

        return parkingTicket;
    }

    private void sendParkingFullNotification() {
        subscribers.forEach(NotificationsSubscriber::parkingFull);
    }

    public boolean unPark(ParkingTicket parkingTicket) {
        return issuedParkingTickets.remove(parkingTicket);
    }

    private boolean isFull() {
        return issuedParkingTickets.size() >= capacity;
    }

    public void addNotificationsSubscriber(NotificationsSubscriber subscriber) {
        subscribers.add(subscriber);
    }
}
