package com.kotlin.practice.spa.entity.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("album")
class Album(
    var artist: String,
    var etc: String
) : Item()