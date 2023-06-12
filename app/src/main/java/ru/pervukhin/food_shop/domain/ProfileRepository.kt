package ru.pervukhin.food_shop.domain

interface ProfileRepository {

    fun getDate(): String

    fun getProfileImage(): Int
}