package com.sabaka.journal.controller

import com.sabaka.journal.model.Speaker
import com.sabaka.journal.service.SpeakerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/speakers")
@CrossOrigin(origins = ["http://localhost:5173"])
class SpeakerController(private val speakerService: SpeakerService) {

    @GetMapping
    fun getAllSpeakers(pageable: Pageable): Page<Speaker> {
        return speakerService.getAllSpeakers(pageable)
    }

    @GetMapping("/{id}")
    fun getSpeakerById(@PathVariable id: Long): Speaker {
        return speakerService.getSpeakerById(id)
    }

    @PostMapping
    fun createSpeaker(@RequestBody speaker: Speaker): Speaker {
        return speakerService.createSpeaker(speaker)
    }

    @PutMapping("/{id}")
    fun updateSpeaker(@PathVariable id: Long, @RequestBody speaker: Speaker): Speaker {
        return speakerService.updateSpeaker(id, speaker)
    }

    @DeleteMapping("/{id}")
    fun deleteSpeaker(@PathVariable id: Long) {
        speakerService.deleteSpeaker(id)
    }

    @GetMapping("/search")
    fun searchSpeakers(@RequestParam(name = "name") name: String, pageable: Pageable): Page<Speaker> {
        return speakerService.searchSpeakers(name, pageable)
    }
}
