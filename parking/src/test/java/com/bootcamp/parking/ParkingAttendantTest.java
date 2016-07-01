package com.bootcamp.parking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test(expected = NoParkingSpaceAvailableException.class)
    public void shouldThrowExceptionWhenNoParkingLotsAreAssignedToAttendant() throws NoParkingSpaceAvailableException {
        attendant = new ParkingAttendant(null);
        Assert.assertNotNull(attendant.park(new Object()));
        fail("Did not throw NoParkingSpaceAvailableException");
    }

    @Test(expected = NoParkingSpaceAvailableException.class)
    public void shouldThrowExceptionWhenAllParkingLotsAreFull() throws NoParkingSpaceAvailableException {
        ParkingLot parkingLotOne = mock(ParkingLot.class);
        when(parkingLotOne.isNotFull()).thenReturn(false);
        ParkingLot parkingLotTwo=mock(ParkingLot.class);
        when(parkingLotTwo.isNotFull()).thenReturn(false);
        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);

        attendant = new ParkingAttendant(parkingLots);
        Assert.assertNotNull(attendant.park(new Object()));
        fail("Did not throw NoParkingSpaceAvailableException");
    }

    @Test
    public void parkingAttendantShouldBeAbleToParkACarWithEvenlyDistributionStrategy() throws Exception {
        ParkingLot parkingLotOne = mock(ParkingLot.class);
        when(parkingLotOne.isNotFull()).thenReturn(true);
        when(parkingLotOne.availableSlots()).thenReturn(2);
        when(parkingLotOne.park(any(Object.class))).thenReturn(new ParkingTicket(1l));
        parkingLots.add(parkingLotOne);

        ParkingLot parkingLotTwo=mock(ParkingLot.class);
        when(parkingLotTwo.isNotFull()).thenReturn(true);
        when(parkingLotOne.availableSlots()).thenReturn(4);
        when(parkingLotTwo.park(any(Object.class))).thenReturn(new ParkingTicket(1l));
        parkingLots.add(parkingLotTwo);

        ParkingLot parkingLotThree=mock(ParkingLot.class);
        when(parkingLotThree.isNotFull()).thenReturn(true);
        when(parkingLotOne.availableSlots()).thenReturn(3);
        when(parkingLotThree.park(any(Object.class))).thenReturn(new ParkingTicket(1l));
        parkingLots.add(parkingLotTwo);

        attendant = new ParkingAttendant(parkingLots, new EvenlyDistributionStrategy());

        Object vehicleOne = new Object();
        attendant.park(vehicleOne);
        verify(parkingLotTwo,times(1)).park(vehicleOne);
    }
}
