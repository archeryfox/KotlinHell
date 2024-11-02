package com.sabaka.journal.controller

import com.sabaka.journal.model.Payment
import com.sabaka.journal.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payments")
class PaymentController(private val paymentService: PaymentService) {

    @GetMapping
    fun getAllPayments(): List<Payment> = paymentService.getAllPayments()

    @GetMapping("/{id}")
    fun getPaymentById(@PathVariable id: Long): ResponseEntity<Payment> {
        val payment = paymentService.getPaymentById(id)
        return if (payment != null) ResponseEntity.ok(payment) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createPayment(@RequestBody payment: Payment): Payment = paymentService.createPayment(payment)

    @PutMapping("/{id}")
    fun updatePayment(@PathVariable id: Long, @RequestBody payment: Payment): ResponseEntity<Payment> {
        val updatedPayment = paymentService.updatePayment(id, payment)
        return if (updatedPayment != null) ResponseEntity.ok(updatedPayment) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deletePayment(@PathVariable id: Long): ResponseEntity<Void> {
        paymentService.deletePayment(id)
        return ResponseEntity.noContent().build()
    }
}
