package com.leeyunbo.coronaboard.data

data class CoronaBoard(
    var todayConfirmer: String,
    var todayHealer: String,
    var confirmer: String,
    var healer: String,
    var healing: String,
    var dead: String,
    var addHealingCnt: String,
    var addHealerCnt: String,
    var addConfirmerCnt: String,
    var addDeadCnt: String)

