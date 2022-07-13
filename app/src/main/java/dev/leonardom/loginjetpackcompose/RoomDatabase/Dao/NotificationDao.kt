package dev.leonardom.loginjetpackcompose.RoomDatabase.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Query("SELECT * FROM Notification Where deleted=0")
    fun getNotifications(): LiveData<List<Notification>>

    @Query("SELECT * FROM Notification WHERE id = :id")
    fun getNotificationById(id: Int):Notification?

    @Update
    fun update(notification: Notification)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotification(notification: Notification)

    @Delete
    fun deleteNotification(notification: Notification)

}