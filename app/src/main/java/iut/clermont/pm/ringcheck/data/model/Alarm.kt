package iut.clermont.pm.ringcheck.data.model

import java.time.LocalDateTime

data class Alarm (
        val alarmId: Int,
        var name: String,
        var dateStart: LocalDateTime,
        var endData: LocalDateTime,
        var checkElemList: List<CheckElem>
)