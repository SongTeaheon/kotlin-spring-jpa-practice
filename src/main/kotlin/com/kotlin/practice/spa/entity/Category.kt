package com.kotlin.practice.spa.entity

import javax.persistence.*

@Entity
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    @OneToMany(mappedBy = "category")
    var itemCategories: MutableList<ItemCategory> = mutableListOf(),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    var parent: Category,
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    var child: MutableList<Category> = mutableListOf()
)