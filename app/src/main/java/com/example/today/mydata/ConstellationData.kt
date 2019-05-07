package com.example.today.mydata

class ConstellationData {

    data class Data(
        val name: String,
        val TODAY_WORD: String,
        val LUCKY_NUMERAL: String,
        val LUCKY_COLOR: String,
        val LUCKY_DIRECTION: String,
        val LUCKY_TIME: String,
        val LUCKY_ASTRO: String,
        val STAR_ENTIRETY: String,
        val DESC_ENTIRETY: String,
        val STAR_LOVE: String,
        val DESC_LOVE: String,
        val STAR_WORK: String,
        val DESC_WORK: String,
        val STAR_MONEY: String,
        val DESC_MONEY: String

    )

    data class Item(
        val image: Int,
        val name: String
    )

    data class Lucky(
        val star: String = "",
        val desc: String = ""

    )


}
