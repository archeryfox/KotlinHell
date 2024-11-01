package com.sabaka.journal.service

import com.sabaka.journal.model.Speaker
import com.sabaka.journal.repository.SpeakerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SpeakerService(private val speakerRepository: SpeakerRepository) {

    fun getAllSpeakers(pageable: Pageable): Page<Speaker> = speakerRepository.findAll(pageable)

    fun getSpeakerById(id: Long): Speaker =
        speakerRepository.findById(id).orElseThrow { RuntimeException("Speaker not found") }

    fun createSpeaker(speaker: Speaker): Speaker = speakerRepository.save(speaker)

    fun updateSpeaker(id: Long, updatedSpeaker: Speaker): Speaker {
        val speaker = getSpeakerById(id)
        return speakerRepository.save(speaker.copy(name = updatedSpeaker.name, bio = updatedSpeaker.bio))
    }

    fun deleteSpeaker(id: Long) {
        if (speakerRepository.existsById(id)) {
            speakerRepository.deleteById(id)
        } else {
            throw RuntimeException("Speaker not found")
        }
    }

    fun searchSpeakers(name: String, pageable: Pageable): Page<Speaker> =
        speakerRepository.findByNameContainingIgnoreCase(name, pageable)
}
