package com.kotlin.practice.spa.entity

import javax.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String,
    @Embedded
    var address: Address? = null,
    @OneToMany(mappedBy = "member")
    var orders: MutableList<Order> = mutableListOf()
)
