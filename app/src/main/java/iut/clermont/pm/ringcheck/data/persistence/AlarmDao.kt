package iut.clermont.pm.ringcheck.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import iut.clermont.pm.ringcheck.data.model.Alarm

@Dao
interface AlarmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(alarms: List<Alarm>)
}