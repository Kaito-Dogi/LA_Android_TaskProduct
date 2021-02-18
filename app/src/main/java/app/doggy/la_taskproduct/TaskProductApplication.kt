package app.doggy.la_taskproduct

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

class TaskProductApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)

        if (BuildConfig.DEBUG) {
            Log.d("TaskProductApplication", "TaskProductApplication has been created.")
        }

    }
}