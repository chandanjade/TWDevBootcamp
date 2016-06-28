package com.bootcamp.parking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingLotTest {

    private ParkingLot parkingLot;
    private int capacity;
    private NotificationsSubscriber notificationsSubscriber;

    @Before
    public void setUp() throws Exception {
        capacity = 5;
        parkingLot = new ParkingLot(capacity);
        notificationsSubscriber = mock(NotificationsSubscriber.class);
        parkingLot.addNotificationsSubscriber(notificationsSubscriber);
    }

    @Test
    public void shouldBeAbleToParkACar() throws Exception {
        Assert.assertNotNull(parkingLot.park());
    }

    @Test
    public void shouldBeAbleToUnParkACar() throws Exception {
        ParkingTicket parkingTicket = parkingLot.park();
        Assert.assertTrue(parkingLot.unPark(parkingTicket));
    }

    @Test
    public void shouldNotBeAbleToUnParkACarWithoutATicket() throws Exception {
        Assert.assertFalse(parkingLot.unPark(null));
    }

    @Test
    public void shouldNotBeAbleToUnParkACarWithUnissuedTicket() throws Exception {
        parkingLot.park();
        Assert.assertFalse(parkingLot.unPark(new ParkingTicket(new Date().getTime())));
    }

    @Test(expected = NoParkingSpaceAvailableException.class)
    public void shouldNotBeAbleToParkWhenParkingLotIsFull() throws Exception {
        for (int i = 0; i < capacity + 1; i++)
            parkingLot.park();
    }

    @Test
    public void shouldNotifySubscribersIfParkingLotIsFull() throws Exception {
        for (int i = 0; i < capacity; i++)
            parkingLot.park();

        verify(notificationsSubscriber, times(1)).parkingFull();
    }

    @Test
    public void shouldNotNotifySubscribersIfParkingLotIsNotFull() throws Exception {
        for (int i = 0; i < capacity - 1; i++)
            parkingLot.park();

        verify(notificationsSubscriber, times(0)).parkingFull();
    }


}
