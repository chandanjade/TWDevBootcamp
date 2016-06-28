package com.bootcamp.parking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(10);

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



}
