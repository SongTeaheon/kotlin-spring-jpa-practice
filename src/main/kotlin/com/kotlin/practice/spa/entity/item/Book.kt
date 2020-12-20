package com.kotlin.practice.spa.entity.item

import javax.persistence.Entity

@Entity
class Book(
    var author: String,
    var isbn: String
) : Item()