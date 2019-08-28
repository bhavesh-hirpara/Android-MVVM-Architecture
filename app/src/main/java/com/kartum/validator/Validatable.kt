package com.kartum.validator

interface Validatable {

    var value: String?
    var msg: String?

    fun isValid() : Boolean
}