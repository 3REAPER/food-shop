package ru.pervukhin.food_shop.ui.cart

interface OnClickPlusMinusListener {

    fun onClickPlus(id: Int)

    fun onClickMinus(id: Int)

    fun onZeroCount(id: Int)
}