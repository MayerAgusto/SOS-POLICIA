package dev.leonardom.loginjetpackcompose.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import dev.leonardom.loginjetpackcompose.RoomDatabase.NotificationDataBase
import dev.leonardom.loginjetpackcompose.RoomDatabase.Repository.NotificationRepository

class MainViewModel(application: Application): ViewModel() {

    var allNotifications: LiveData<List<Notification>>? = null
    private var repository: NotificationRepository? = null
    var searchResult: MutableLiveData<Notification>? = null

    init {
        val notificationDb = NotificationDataBase.getInstance(application)
        val notificationDao = notificationDb.notificationDao()
        repository = NotificationRepository(notificationDao)
        allNotifications = repository!!.allNotifications
        searchResult = repository!!.searchResult
    }

    fun insertNotification(notification: Notification){
        repository!!.insertNotification(notification)
    }

    fun findNotificationById(id: Int){
        repository!!.findNotificationById(id)
    }

    fun deleteNotification(notification: Notification){
        repository!!.deleteNotification(notification)
    }
}