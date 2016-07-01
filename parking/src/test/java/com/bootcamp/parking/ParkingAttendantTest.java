package com.bootcamp.parking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
        when(parkingLot.isNotFull()).thenReturn(true);
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
        ParkingLot parkingLotOne = mock(ParkingLot.class);
        when(parkingLotOne.isNotFull()).thenReturn(false);
        when(parkingLotOne.park(any(Object.class))).thenReturn(new ParkingTicket(1l));
        parkingLots.add(parkingLotOne);

        ParkingLot parkingLotTwo=mock(ParkingLot.class);
        when(parkingLotTwo.isNotFull()).thenReturn(true);
        when(parkingLotTwo.park(any(Object.class))).thenReturn(new ParkingTicket(1l));
        parkingLots.add(parkingLotTwo);

        attendant = new ParkingAttendant(parkingLots);
        attendant.park(new Object());
        verify(parkingLotOne, times(0)).park(any(Object.class));
        verify(parkingLotTwo, times(1)).park(any(Object.class));

    }
}
