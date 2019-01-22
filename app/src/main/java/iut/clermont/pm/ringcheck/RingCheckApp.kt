package iut.clermont.pm.ringcheck

import android.app.Application
import iut.clermont.pm.ringcheck.data.persistence.RingCheckDatabase

public class RingCheckApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RingCheckDatabase.setAppContext(this)
    }
}