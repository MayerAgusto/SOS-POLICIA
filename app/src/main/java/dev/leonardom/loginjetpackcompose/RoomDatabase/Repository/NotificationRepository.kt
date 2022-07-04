package dev.leonardom.loginjetpackcompose.RoomDatabase.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.leonardom.loginjetpackcompose.RoomDatabase.Dao.NotificationDao
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepository (
    private val notificationDao: NotificationDao

) {
    val allNotifications: LiveData<List<Notification>> = notificationDao.getNotifications()
    val searchResult = MutableLiveData<Notification>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertNotification(notification: Notification){
        coroutineScope.launch(Dispatchers.IO){
            notificationDao.insertNotification(notification)
        }
    }
    fun deleteNotification(notification: Notification){
        coroutineScope.launch(Dispatchers.IO) {
            notificationDao.deleteNotification(notification)
        }
    }
    fun findNotificationById(id: Int){
        coroutineScope.launch(Dispatchers.Main) {
            searchResult.value = asyncFind(id).await()
        }
    }

    private fun asyncFind(id: Int): Deferred<Notification?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async notificationDao.getNotificationById(id)
        }
}