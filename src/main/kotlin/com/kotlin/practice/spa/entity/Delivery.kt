package com.kotlin.practice.spa.entity

import com.kotlin.practice.spa.enums.DeliveryStatus
import javax.persistence.*

@Entity
class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    var order: Order,
    var address: Address,
    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus
)