package com.kotlin.practice.spa.entity.item

import com.kotlin.practice.spa.entity.ItemCategory
import com.kotlin.practice.spa.entity.OrderItem
import java.math.BigDecimal
import javax.persistence.*
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var price: BigDecimal = BigDecimal.ZERO,
    var stockQuantity: Int = 0,
    @OneToMany(mappedBy = "item")
    var orderItem: MutableList<OrderItem> = mutableListOf(),
    @OneToMany(mappedBy = "item")
    var itemCatgories: MutableList<ItemCategory> = mutableListOf()
) {
    constructor() : this(
        id = null,
        name = "",
        price = BigDecimal.ZERO,
        stockQuantity = 0,
        orderItem = mutableListOf(),
        itemCatgories = mutableListOf()
    )
}