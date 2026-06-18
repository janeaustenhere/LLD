package com.example.parkingLot.services;


import com.example.parkingLot.enums.PaymentMode;
import com.example.parkingLot.enums.PaymentStatus;
import com.example.parkingLot.model.Ticket;
import com.example.parkingLot.model.Vehicle;
import com.example.parkingLot.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public Ticket createTicket(Vehicle vehicle){
        Ticket ticket = Ticket.builder()
                .vehicle(vehicle)
                .ticketId(UUID.randomUUID().toString())
                .entryTime(LocalDateTime.now())
                .paymentMode(PaymentMode.CASH)
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        return ticketRepository.createTicket(ticket);

    }

    public Ticket getTicketDetails(String ticketId){

        return ticketRepository.getTicketDetails(ticketId);
    }
}
