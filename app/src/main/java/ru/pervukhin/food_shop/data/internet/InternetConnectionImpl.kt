package ru.pervukhin.food_shop.data.internet

import ru.pervukhin.food_shop.domain.InternetConnection

class InternetConnectionImpl: InternetConnection {

    override fun hasInternet(): Boolean {
        return try {
            val command = "ping -c 1 google.com"
            Runtime.getRuntime().exec(command).waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }
}