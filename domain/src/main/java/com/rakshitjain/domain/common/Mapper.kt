package com.rakshitjain.domain.common

abstract class Mapper<in T,E>{

    abstract fun mapFrom(from: T): E
}