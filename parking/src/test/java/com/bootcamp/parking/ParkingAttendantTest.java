package com.bootcamp.parking;

import com.bootcamp.parking.strategy.EvenlyDistributionStrategy;
import com.bootcamp.parking.strategy.FirstAvailableFirstStrategy;
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
        attendant = new ParkingAttendant(parkingLots, new FirstAvailableFirstStrategy());
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

        attendant = new ParkingAttendant(parkingLots, new FirstAvailableFirstStrategy());
        attendant.park(new Object());
        verify(parkingLotOne, times(0)).park(any(Object.class));
        verify(parkingLotTwo, times(1)).park(any(Object.class));

    }

    @Test(expected = NoParkingSpaceAvailableException.class)
    public void shouldThrowExceptionWhenNoParkingLotsAreAssignedToAttendant() throws NoParkingSpaceAvailableException {
        attendant = new ParkingAttendant(null, new FirstAvailableFirstStrategy());
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

        attendant = new ParkingAttendant(parkingLots, new FirstAvailableFirstStrategy());
        Assert.assertNotNull(attendant.park(new Object()));
        fail("Did not throw NoParkingSpaceAvailableException");
    }

    @Test
    public void parkingAttendantShouldBeAbleToParkACarWithEvenlyDistributionStrategy() throws Exception {
        ParkingLot parkingLotOne = mock(ParkingLot.class);
        when(parkingLotOne.isNotFull()).thenReturn(true);
        when(parkingLotOne.availableSlots()).thenReturn(2);
        when(parkingLotOne.park(any(Object.class))).thenReturn(new ParkingTicket(1l));

        ParkingLot parkingLotTwo=mock(ParkingLot.class);
        when(parkingLotTwo.isNotFull()).thenReturn(true);
        when(parkingLotTwo.availableSlots()).thenReturn(4);
        when(parkingLotTwo.park(any(Object.class))).thenReturn(new ParkingTicket(1l));

        ParkingLot parkingLotThree=mock(ParkingLot.class);
        when(parkingLotThree.isNotFull()).thenReturn(true);
        when(parkingLotThree.availableSlots()).thenReturn(3);
        when(parkingLotThree.park(any(Object.class))).thenReturn(new ParkingTicket(1l));

        when(parkingLotOne.compareTo(parkingLotTwo)).thenReturn(-1);
        when(parkingLotOne.compareTo(parkingLotThree)).thenReturn(-1);
        when(parkingLotTwo.compareTo(parkingLotOne)).thenReturn(1);
        when(parkingLotTwo.compareTo(parkingLotThree)).thenReturn(1);
        when(parkingLotThree.compareTo(parkingLotOne)).thenReturn(1);
        when(parkingLotThree.compareTo(parkingLotTwo)).thenReturn(-1);

        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);
        parkingLots.add(parkingLotThree);

        attendant = new ParkingAttendant(parkingLots, new EvenlyDistributionStrategy());

        Object vehicleOne = new Object();
        attendant.park(vehicleOne);
        verify(parkingLotTwo,times(1)).park(vehicleOne);
    }
}
