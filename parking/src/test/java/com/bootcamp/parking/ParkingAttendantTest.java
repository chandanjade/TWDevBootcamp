package com.bootcamp.parking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingAttendantTest {

    private List<ParkingLot> parkingLots;
    private ParkingAttendant attendant;

    @Before
    public void setUp() throws Exception {
        parkingLots = new ArrayList<>();

    }

    @Test
    public void parkingAttendantShouldBeAbleToParkACar() throws Exception {
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.park(any(Object.class))).thenReturn(new ParkingTicket(1l));
        parkingLots.add(parkingLot);
        attendant = new ParkingAttendant(parkingLots);
        Assert.assertNotNull(attendant.park(new Object()));
    }

    @Test(expected = NoParkingSpaceAvailableException.class)
    public void shouldNotAbleToParkWhenNoParkingLotsAreAvailable() throws NoParkingSpaceAvailableException {
        attendant = new ParkingAttendant(null);
        Assert.assertNotNull(attendant.park(new Object()));
        fail("Did not throw NoParkingSpaceAvailableException");
    }

    @Test
    public void shouldBeAbleToParkInSecondLotIfFirstLotIsFull() throws NoParkingSpaceAvailableException {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.park(any(Object.class))).thenReturn(null);
        parkingLots.add(parkingLot1);

        ParkingLot parkingLot2=mock(ParkingLot.class);
        ParkingTicket parkingTicket = new ParkingTicket(1l);
        when(parkingLot2.park(any(Object.class))).thenReturn(parkingTicket);
        parkingLots.add(parkingLot2);

        attendant = new ParkingAttendant(parkingLots);

    }
}
