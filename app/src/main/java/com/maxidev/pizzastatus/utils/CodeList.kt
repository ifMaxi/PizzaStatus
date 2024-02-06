package com.maxidev.pizzastatus.utils

object CodeList {
    val listOfCodes = listOf(
        "200", "204", "306", "400", "404", "500"
    )

    private val restOfCodes = listOf(
        "100", "101", "102", "103", "110", "201", "202", "203", "204", "206", "207", "300", "301",
        "302", "303", "304", "305", "307", "308", "401", "402", "403", "406", "410", "414", "416",
        "417", "418", "420", "422", "423", "425", "429", "506", "507", "521", "530",
    )

    val fullListOfCodes = (listOfCodes + restOfCodes).sorted()
}