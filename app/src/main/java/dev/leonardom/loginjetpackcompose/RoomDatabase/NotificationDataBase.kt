package dev.leonardom.loginjetpackcompose.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.leonardom.loginjetpackcompose.RoomDatabase.Dao.NotificationDao
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import dev.leonardom.loginjetpackcompose.RoomDatabase.Utils.DATABASE

@Database(
    entities = [Notification::class],
    version = 1,
    exportSchema = false
)
abstract class NotificationDataBase: RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
    companion object{
        private var INSTANCE: NotificationDataBase? = null
        fun getInstance(context: Context): NotificationDataBase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotificationDataBase::class.java,
                        DATABASE,
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}