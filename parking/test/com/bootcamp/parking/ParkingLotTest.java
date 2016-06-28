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

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(5);
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
    public void shouldNotBeAbleToUnParkACarWithUnissuedTicket() throws Exception {
        parkingLot.park();
        Assert.assertFalse(parkingLot.unPark(new ParkingTicket(new Date().getTime())));
    }

    @Test
    public void shouldNotBeAbleToUnParkACarWithNullTicket() throws Exception {
        Assert.assertFalse(parkingLot.unPark(null));
    }

    @Test(expected = NoParkingSpaceAvailableException.class)
    public void shouldNotBeAbleToParkWhenParkingLotIsFull() throws Exception {
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
    }

    @Test
    public void isFullShouldReturnFalseIfParkingLotIsNotFull() throws Exception {
        ParkingOwner parkingOwner = mock(ParkingOwner.class);
        parkingLot.subscribeNotifications(parkingOwner);
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        verify(parkingOwner, times(0)).notifyOnParkingFull();
    }

    @Test
    public void isFullShouldReturnTrueIfParkingLotIsFull() throws Exception {
        ParkingOwner parkingOwner = mock(ParkingOwner.class);
        parkingLot.subscribeNotifications(parkingOwner);
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        verify(parkingOwner, times(1)).notifyOnParkingFull();
    }

    @Test
    public void notifySecurityOnParkingFull() throws Exception {
        NotificationsSubscriber securityPersonal = mock(SecurityPersonal.class);
        parkingLot.subscribeNotifications(securityPersonal);
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        verify(securityPersonal, times(1)).notifyOnParkingFull();
    }

    @Test
    public void shouldNotifyAllSubscribersIfParkingFull() throws Exception {
        NotificationsSubscriber securityPersonal = mock(SecurityPersonal.class);
        NotificationsSubscriber parkingOwner = mock(ParkingOwner.class);
        parkingLot.subscribeNotifications(securityPersonal);
        parkingLot.subscribeNotifications(parkingOwner);
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        parkingLot.park();
        verify(securityPersonal, times(1)).notifyOnParkingFull();
        verify(parkingOwner, times(1)).notifyOnParkingFull();

    }



}
