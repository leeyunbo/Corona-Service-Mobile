package com.leeyunbo.coronaboard.data

data class CoronaBoard(
    var todayConfirmer: Int,
    var todayHealer: Int,
    var confirmer: Int,
    var cealer: Int,
    var healing: Int,
    var dead: Int,
    var addHealingCnt: String,
    var addHealerCnt: String,
    var addConfirmerCnt: String,
    var addDeadCnt: String)

