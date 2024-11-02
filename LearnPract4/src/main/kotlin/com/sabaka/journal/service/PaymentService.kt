package com.sabaka.journal.service

import com.sabaka.journal.model.Payment
import com.sabaka.journal.repository.PaymentRepository
import org.springframework.stereotype.Service

@Service
class PaymentService(private val paymentRepository: PaymentRepository) {
    fun getAllPayments(): List<Payment> = paymentRepository.findAll()

    fun getPaymentById(id: Long): Payment? = paymentRepository.findById(id).orElse(null)

    fun createPayment(payment: Payment): Payment = paymentRepository.save(payment)

    fun updatePayment(id: Long, payment: Payment): Payment? {
        return if (paymentRepository.existsById(id)) {
            paymentRepository.save(payment.copy(id = id))
        } else null
    }

    fun deletePayment(id: Long) = paymentRepository.deleteById(id)
}
