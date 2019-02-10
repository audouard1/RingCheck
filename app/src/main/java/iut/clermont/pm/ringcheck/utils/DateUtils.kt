package iut.clermont.pm.ringcheck.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class DateUtils {
    companion object {
        fun convertDateFromView(fecha: String): ZonedDateTime {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val date = LocalDate.parse(fecha, formatter)

            return date.atStartOfDay(ZoneId.systemDefault())
        }
    }

}