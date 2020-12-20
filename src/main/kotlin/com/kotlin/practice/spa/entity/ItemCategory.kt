package com.kotlin.practice.spa.entity

import com.kotlin.practice.spa.entity.item.Item
import javax.persistence.*

@Entity
class ItemCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    var category: Category,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    var item: Item
)