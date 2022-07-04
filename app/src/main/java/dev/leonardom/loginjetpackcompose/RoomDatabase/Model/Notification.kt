package dev.leonardom.loginjetpackcompose.RoomDatabase.Model

import android.service.quicksettings.Tile
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class Notification{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0
    var idFirebase: String = ""
    var title: String= ""
    var description: String= ""
    var kindCrime: String= ""
    var category: String= ""
    var address: String= ""
    var department: String= ""
    var province: String= ""
    var city: String= ""
    var gradeIncident: String= ""
    var status: String= ""
    var date: String= ""
    var time: String= ""
    var nameUser: String= ""
    var mail: String= ""
    var deleted: Int= 0

    constructor() {}
    constructor(title: String,
                description: String,
                kindCrime: String,
                category: String,
                address: String,
                department: String,
                province: String,
                city: String,
                gradeIncident: String,
                status: String,
                date: String,
                time: String,
                nameUser: String,
                mail: String
    ){
      this.title  =title
       this.description = description
       this.kindCrime = kindCrime
       this.category = category
       this.address = address
       this.department = department
       this.province = province
       this.city = city
       this.gradeIncident = gradeIncident
       this.status = status
       this.date = date
       this.time = time
       this.nameUser = nameUser
       this.mail  =mail
    }

    fun savefirestoreID(idFirebase: String){
        this.idFirebase = idFirebase
    }
 }
