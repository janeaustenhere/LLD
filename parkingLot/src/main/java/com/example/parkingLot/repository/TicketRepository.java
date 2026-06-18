package com.example.parkingLot.repository;


import com.example.parkingLot.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class TicketRepository {

    Map<String, Ticket> ticketMap = new HashMap<>();

    public Ticket createTicket(Ticket ticket){
        ticketMap.put(ticket.getTicketId(),ticket);
        return ticket;

    }

    public Ticket updateTicket(Ticket ticket){
        return null;
    }

    public Ticket getTicketDetails(String ticketId){
        return ticketMap.get(ticketId);
    }
}
