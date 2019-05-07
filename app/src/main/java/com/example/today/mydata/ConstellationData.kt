package com.example.today.mydata

import java.io.Serializable

class ConstellationData {

    data class Data(
        var name: String,
        var TODAY_WORD: String,
        var LUCKY_NUMERAL: String,
        var LUCKY_COLOR: String,
        var LUCKY_DIRECTION: String,
        var LUCKY_TIME: String,
        var LUCKY_ASTRO: String,
        var STAR_ENTIRETY: String,
        var DESC_ENTIRETY: String,
        var STAR_LOVE: String,
        var DESC_LOVE: String,
        var STAR_WORK: String,
        var DESC_WORK: String,
        var STAR_MONEY: String,
        var DESC_MONEY: String

    ):Serializable

    data class Item(
        var image: Int,
        var name: String
    )

    data class Lucky(
        var star: String = "",
        var desc: String = ""

    )


}
