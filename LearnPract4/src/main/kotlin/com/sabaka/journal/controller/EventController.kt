package com.sabaka.journal.controller

import com.sabaka.journal.model.Event
import com.sabaka.journal.service.EventService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = ["http://localhost:5173"])
class EventController(private val eventService: EventService) {

    @GetMapping
    fun getAllEvents(pageable: Pageable): Page<Event> {
        return eventService.getAllEvents(pageable)
    }

    @GetMapping("/{id}")
    fun getEventById(@PathVariable id: Long): Event {
        return eventService.getEventById(id)
    }

    @PostMapping
    fun createEvent(@RequestBody event: Event): Event {
        println(event)
        return eventService.createEvent(event)
    }


    @PutMapping("/{id}")
    fun updateEvent(@PathVariable id: Long, @RequestBody event: Event): Event {
        return eventService.updateEvent(id, event)
    }

    @DeleteMapping("/{id}")
    fun deleteEvent(@PathVariable id: Long) {
        eventService.deleteEvent(id)
    }

    @GetMapping("/search")
    fun searchEvents(@RequestParam(name = "name") name: String, pageable: Pageable): Page<Event> {
        return eventService.searchEvents(name, pageable)
    }
}
