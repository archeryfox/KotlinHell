package com.sabaka.journal.service

import com.sabaka.journal.model.Ticket
import com.sabaka.journal.repository.TicketRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TicketService(private val ticketRepository: TicketRepository) {

    fun getAllTickets(pageable: Pageable): Page<Ticket> = ticketRepository.findAll(pageable)

    fun getTicketById(id: Long): Ticket =
        ticketRepository.findById(id).orElseThrow { RuntimeException("Ticket not found") }

    fun createTicket(ticket: Ticket): Ticket = ticketRepository.save(ticket)

    fun updateTicket(id: Long, updatedTicket: Ticket): Ticket {
        val ticket = getTicketById(id)
        return ticketRepository.save(ticket.copy(price = updatedTicket.price, user = updatedTicket.user, event = updatedTicket.event))
    }

    fun deleteTicket(id: Long) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id)
        } else {
            throw RuntimeException("Ticket not found")
        }
    }

    fun searchTickets(price: Double, pageable: Pageable): Page<Ticket> =
        ticketRepository.findByPrice(price, pageable)
}
