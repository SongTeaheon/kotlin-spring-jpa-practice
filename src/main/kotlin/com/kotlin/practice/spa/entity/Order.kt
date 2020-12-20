package com.kotlin.practice.spa.entity

import com.kotlin.practice.spa.enums.OrderStatus
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    var member: Member,
    @OneToMany(mappedBy = "order")
    var orderItems: MutableList<OrderItem> = arrayListOf(),
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery")
    var delivery: Delivery,
    var orderDate: Date,
    @Enumerated(EnumType.STRING)
    var status: OrderStatus
)