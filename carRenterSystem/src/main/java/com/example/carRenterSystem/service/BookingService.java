package com.example.carRenterSystem.service;

import com.example.carRenterSystem.enums.BookingStatus;
import com.example.carRenterSystem.enums.VehicleStatus;
import com.example.carRenterSystem.enums.VehicleType;
import com.example.carRenterSystem.model.Booking;
import com.example.carRenterSystem.model.Branch;
import com.example.carRenterSystem.model.Vehicle;
import com.example.carRenterSystem.repository.BookingRepository;
import com.example.carRenterSystem.repository.BranchRepository;
import com.example.carRenterSystem.strategy.BookingStrategy;
import com.example.carRenterSystem.strategy.PricingStartegy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BranchRepository branchRepository;
    private final BookingStrategy bookingStrategy;
    private final PricingStartegy pricingStartegy;

    public BookingService(BookingRepository bookingRepository, BranchRepository branchRepository,
                          @Qualifier("leastBookedVehicleStrategy") BookingStrategy bookingStrategy,
                          @Qualifier("hourlyPricingStrategy") PricingStartegy pricingStartegy) {
        this.bookingRepository = bookingRepository;
        this.branchRepository = branchRepository;
        this.bookingStrategy = bookingStrategy;
        this.pricingStartegy = pricingStartegy;
    }

    public Booking createBooking(Booking booking){

        String branchId = booking.getPickUpBranchId();
        Branch branch = branchRepository.getBranchById(branchId);
        List<Vehicle> listOfVehicle = branch.getVehicleMap().get(booking.getVehicleType());

        Vehicle vehicle = bookingStrategy.bookVehicle(listOfVehicle);
        if(vehicle == null){
            throw new RuntimeException("No available vehicle");
        }
        double amount = pricingStartegy.calculatePrice(vehicle,booking.getPickupTime(),booking.getDropTime(),0);
        booking.setAmount(amount);
        booking.setVehicleId(vehicle.getVehicleId());

        return bookingRepository.createBooking(booking);
    }

    public void updateBooking(String bookingId, String bookingStatus){

        bookingRepository.updateBookingStatus(bookingId,bookingStatus);
        Booking booking = bookingRepository.getBookingDetailsById(bookingId);
        Branch pickUpBranch = branchRepository.getBranchById(booking.getPickUpBranchId());
        Branch dropBranch = branchRepository.getBranchById(booking.getDropBranchId());

        if(bookingStatus.equals(String.valueOf(BookingStatus.COMPLETED))){


            branchRepository.removeVehicle(booking.getVehicleId(), pickUpBranch.getBranchId());

            Optional<Vehicle> vehicle =pickUpBranch.getVehicleMap().values().
                    stream().flatMap(vehicleList -> vehicleList.stream())
                    .filter(vehicle1 -> vehicle1.getVehicleId().equals(booking.getVehicleId())).findFirst();

            branchRepository.addVehicle(vehicle.get(), dropBranch.getBranchId());
        }
        if(bookingStatus.equals(String.valueOf(BookingStatus.CANCELLED))){

            Optional<Vehicle> vehicle = pickUpBranch.getVehicleMap().values().stream()
                    .flatMap(vehicleList -> vehicleList.stream())
                    .filter(vehicle1 -> vehicle1.getVehicleId().equals(booking.getVehicleId())).findFirst();

            vehicle.get().setVehicleStatus(VehicleStatus.AVAILABLE);
            vehicle.get().getIsAvailable().compareAndSet(false, true);

        }

    }

    public Booking getBookingDetailsById(String bookingId){

        return bookingRepository.getBookingDetailsById(bookingId);
    }

    public List<Booking> getAllBookings(){

        return bookingRepository.getListOfAllBokkings();
    }




}
