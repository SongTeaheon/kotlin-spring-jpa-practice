package com.kotlin.practice.spa.entity

import com.kotlin.practice.spa.entity.item.Item
import java.math.BigDecimal
import javax.persistence.*

@Entity
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    var order: Order,
    @ManyToOne
    @JoinColumn(name = "itemId")
    var item: Item,
    var orderAmount: BigDecimal = BigDecimal.ZERO,
    var count: Int = 0
)