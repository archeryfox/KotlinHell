package com.sabaka.journal.controller

import com.sabaka.journal.model.Ticket
import com.sabaka.journal.repository.EventRepository
import com.sabaka.journal.repository.UserRepository
import com.sabaka.journal.service.TicketService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = ["http://localhost:5173"])
class TicketController(
    private val ticketService: TicketService,
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository
) {

    @GetMapping
    fun getAllTickets(pageable: Pageable): Page<Ticket> {
        return ticketService.getAllTickets(pageable)
    }

    @GetMapping("/{id}")
    fun getTicketById(@PathVariable id: Long): Ticket {
        return ticketService.getTicketById(id)
    }

    @PostMapping
    fun createTicket(@RequestBody ticketRequest: Ticket): Ticket {
        println(ticketRequest)
        val event = eventRepository.findById(ticketRequest.event?.id ?: 0)
            .orElseThrow { ResourceNotFoundException("Event not found with id ${ticketRequest.event?.id}") }

        val user = userRepository.findById(ticketRequest.user?.id ?: 0)
            .orElseThrow { ResourceNotFoundException("User not found with id ${ticketRequest.user?.id}") }

        // Устанавливаем event и user, которые уже существуют в базе
        val ticketToSave = ticketRequest.copy(event = event, user = user)

        // Сохраняем Ticket
        val savedTicket = ticketService.createTicket(ticketToSave)
        return savedTicket
    }

    @PutMapping("/{id}")
    fun updateTicket(@PathVariable id: Long, @RequestBody ticket: Ticket): Ticket {
        return ticketService.updateTicket(id, ticket)
    }

    @DeleteMapping("/{id}")
    fun deleteTicket(@PathVariable id: Long) {
        ticketService.deleteTicket(id)
    }

    @GetMapping("/search")
    fun searchTickets(@RequestParam(name = "price") price: Double, pageable: Pageable): Page<Ticket> {
        return ticketService.searchTickets(price, pageable)
    }
}
