package iut.clermont.pm.ringcheck

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import iut.clermont.pm.ringcheck.data.persistence.RingCheckDatabase

public class RingCheckApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        RingCheckDatabase.setAppContext(this)
    }
}