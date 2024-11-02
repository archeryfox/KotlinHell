package com.sabaka.journal.controller

import com.sabaka.journal.model.Order
import com.sabaka.journal.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderService: OrderService) {

    @GetMapping
    fun getAllOrders(): List<Order> = orderService.getAllOrders()

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Long): ResponseEntity<Order> {
        val order = orderService.getOrderById(id)
        return if (order != null) ResponseEntity.ok(order) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createOrder(@RequestBody order: Order): Order = orderService.createOrder(order)

    @PutMapping("/{id}")
    fun updateOrder(@PathVariable id: Long, @RequestBody order: Order): ResponseEntity<Order> {
        val updatedOrder = orderService.updateOrder(id, order)
        return if (updatedOrder != null) ResponseEntity.ok(updatedOrder) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: Long): ResponseEntity<Void> {
        orderService.deleteOrder(id)
        return ResponseEntity.noContent().build()
    }
}
