package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false,
) {

    class Builder {

        private var id: String? = null
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = Date()
        private var isOnline: Boolean = false

        fun id(id:String) = apply { this.id = id }
        fun firstName(firstName:String) = apply { this.firstName = firstName }
        fun lastName(lastName:String) = apply { this.lastName = lastName }
        fun avatar(avatar:String) = apply { this.avatar = avatar }
        fun rating(rating:Int) = apply { this.rating = rating }
        fun respect(respect:Int) = apply { this.respect = respect }
        fun lastVisit(lastVisit:Date) = apply { this.lastVisit = lastVisit }
        fun respect(isOnline:Boolean) = apply { this.isOnline = isOnline }
        fun build() :User {
            val newId = id ?: getNewId()
            return User(newId, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }
    }

    constructor(id:String, firstName: String?, lastName:String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null)

    constructor(id:String) : this (id, null, null)

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            return if (fullName.isNullOrBlank()) {
                User(id = "$lastId")
            } else {
                val (firstName, lastName) = Utils.parseFullName(fullName)
                User(id = "$lastId", firstName = firstName, lastName = lastName)
            }
        }
        fun getNewId():String = "${++lastId}"
    }
}

