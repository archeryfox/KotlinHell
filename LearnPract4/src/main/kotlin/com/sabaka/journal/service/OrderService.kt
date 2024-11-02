package com.sabaka.journal.service

import com.sabaka.journal.model.Order
import com.sabaka.journal.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {
    fun getAllOrders(): List<Order> = orderRepository.findAll()

    fun getOrderById(id: Long): Order? = orderRepository.findById(id).orElse(null)

    fun createOrder(order: Order): Order = orderRepository.save(order)

    fun updateOrder(id: Long, order: Order): Order? {
        return if (orderRepository.existsById(id)) {
            orderRepository.save(order.copy(id = id))
        } else null
    }

    fun deleteOrder(id: Long) = orderRepository.deleteById(id)
}
