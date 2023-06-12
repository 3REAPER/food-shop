package ru.pervukhin.food_shop.domain

interface GeoAndDateRepository {

    fun getDate(): String

    fun getGeo(): String
}