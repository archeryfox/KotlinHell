package com.sabaka.journal.service

import com.sabaka.journal.model.Event
import com.sabaka.journal.repository.EventRepository
import com.sabaka.journal.repository.SpeakerRepository
import com.sabaka.journal.repository.VenueRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val venueRepository: VenueRepository,
    private val speakerRepository: SpeakerRepository
) {

    fun getAllEvents(pageable: Pageable): Page<Event> = eventRepository.findAll(pageable)

    fun getEventById(id: Long): Event =
        eventRepository.findById(id).orElseThrow { RuntimeException("Event not found") }

    fun createEvent(event: Event): Event {
        // Проверяем, существует ли Venue по переданному ID
        val venueId = event.venue?.id ?: throw IllegalArgumentException("Venue ID is required")
        val venue = venueRepository.findById(venueId)
            .orElseThrow { ResourceNotFoundException("Venue not found with id $venueId") }

        // Проверяем, существуют ли Speakers
        val speakers = event.speakers?.map { speaker ->
            val speakerId = speaker.id
            if (speakerId == 0L) {
                throw IllegalArgumentException("Speaker ID is required")
            }
            speakerRepository.findById(speakerId)
                .orElseThrow { ResourceNotFoundException("Speaker not found with id $speakerId") }
        } ?: emptyList()

        // Создаем событие с найденным Venue и Speakers
        val newEvent = event.copy(venue = venue, speakers = speakers)

        return eventRepository.save(newEvent)
    }

    fun updateEvent(id: Long, updatedEvent: Event): Event {
        val event = getEventById(id)
        return eventRepository.save(event.copy(
            name = updatedEvent.name,
            description = updatedEvent.description,
            date = updatedEvent.date,
            speakers = updatedEvent.speakers
        ))
    }

    fun deleteEvent(id: Long) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id)
        } else {
            throw RuntimeException("Event not found")
        }
    }

    fun searchEvents(name: String, pageable: Pageable): Page<Event> =
        eventRepository.findByNameContainingIgnoreCase(name, pageable)
}
