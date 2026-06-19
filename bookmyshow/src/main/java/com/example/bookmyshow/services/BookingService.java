package com.example.bookmyshow.services;

import com.example.bookmyshow.enums.BookingStatus;
import com.example.bookmyshow.model.Booking;
import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.model.SeatInput;
import com.example.bookmyshow.provider.Expire;
import com.example.bookmyshow.provider.LockProvider;
import com.example.bookmyshow.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final LockProvider lockProvider;

    public BookingService(BookingRepository bookingRepository,
                          @Qualifier("inMemoryLockProvider") LockProvider lockProvider) {
        this.bookingRepository = bookingRepository;
        this.lockProvider = lockProvider;
    }

    public Booking createBooking(Booking booking) throws Exception {
        String theatreId = booking.getTheatreId();
        String screenId = booking.getScreenId();
        List<SeatInput> seatList = booking.getSeatList();
        for(SeatInput seatInput : seatList){
            String seatId = seatInput.getSeatId();
            String lock_key = theatreId + "_" + screenId + "_" + seatId;
            if(!lockProvider.tryLock(lock_key, booking.getUserId())) {
                throw new Exception("Seat is already locked , select different seat");
            }
        }
        return bookingRepository.createBooking(booking);
    }

    public Booking confirmBooking(Booking booking) throws Exception {
        String theatreId = booking.getTheatreId();
        String screenId = booking.getScreenId();
        List<SeatInput> seatList = booking.getSeatList();
        for(SeatInput seatInput : seatList){
            String seatId = seatInput.getSeatId();
            String lock_key = theatreId + "_" + screenId + "_" + seatId;
            if(!lockProvider.lockByUser(lock_key, booking.getUserId())){
                throw new Exception("Select seat again");
            }
        }
        return bookingRepository.updateBooking(booking.getBookingId(), BookingStatus.CONFIRMED);
    }

    public Booking getBookingDetails(String bookingId){
        return bookingRepository.getBookingDetailsById(bookingId);
    }
}


