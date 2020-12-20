package com.kotlin.practice.spa.entity.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("Movie")
class Movie(
    var director: String,
    var actor: String
) : Item()