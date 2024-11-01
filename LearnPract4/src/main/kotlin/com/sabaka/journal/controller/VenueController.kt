package com.sabaka.journal.controller

import com.sabaka.journal.model.Venue
import com.sabaka.journal.service.VenueService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/venues")
@CrossOrigin(origins = ["http://localhost:5173"])
class VenueController(private val venueService: VenueService) {

    @GetMapping
    fun getAllVenues(pageable: Pageable): Page<Venue> {
        return venueService.getAllVenues(pageable)
    }

    @GetMapping("/{id}")
    fun getVenueById(@PathVariable id: Long): Venue {
        return venueService.getVenueById(id)
    }

    @PostMapping
    fun createVenue(@RequestBody venue: Venue): Venue {
        return venueService.createVenue(venue)
    }

    @PutMapping("/{id}")
    fun updateVenue(@PathVariable id: Long, @RequestBody venue: Venue): Venue {
        return venueService.updateVenue(id, venue)
    }

    @DeleteMapping("/{id}")
    fun deleteVenue(@PathVariable id: Long) {
        venueService.deleteVenue(id)
    }

    @GetMapping("/search")
    fun searchVenues(@RequestParam(name = "name") name: String, pageable: Pageable): Page<Venue> {
        return venueService.searchVenues(name, pageable)
    }
}
