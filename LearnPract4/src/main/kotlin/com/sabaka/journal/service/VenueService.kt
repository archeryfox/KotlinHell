package com.sabaka.journal.service

import com.sabaka.journal.model.Venue
import com.sabaka.journal.repository.VenueRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class VenueService(private val venueRepository: VenueRepository) {

    fun getAllVenues(pageable: Pageable): Page<Venue> = venueRepository.findAll(pageable)

    fun getVenueById(id: Long): Venue =
        venueRepository.findById(id).orElseThrow { RuntimeException("Venue not found") }

    fun createVenue(venue: Venue): Venue = venueRepository.save(venue)

    fun updateVenue(id: Long, updatedVenue: Venue): Venue {
        val venue = getVenueById(id)
        return venueRepository.save(venue.copy(name = updatedVenue.name, location = updatedVenue.location))
    }

    fun deleteVenue(id: Long) {
        if (venueRepository.existsById(id)) {
            venueRepository.deleteById(id)
        } else {
            throw RuntimeException("Venue not found")
        }
    }

    fun searchVenues(name: String, pageable: Pageable): Page<Venue> =
        venueRepository.findByNameContainingIgnoreCase(name, pageable)
}
