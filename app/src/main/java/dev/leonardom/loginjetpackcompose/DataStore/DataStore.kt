package dev.leonardom.loginjetpackcompose.DataStore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {
    companion object{
        private  val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userData")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val USER_DEPARTMENT_KEY = stringPreferencesKey("user_department")
        val USER_PROVINCE_KEY = stringPreferencesKey("user_province")
        val USER_CITY_KEY = stringPreferencesKey("user_city")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_PASSWORD_KEY = stringPreferencesKey("user_password")
    }

    //get the saved name
    val getName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_NAME_KEY] ?: ""
        }

    //save name into datastore
    suspend fun saveName(name: String) {

        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }
    //get the saved Department
    val getDepartment: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_DEPARTMENT_KEY] ?: ""
        }

    //save Department into datastore
    suspend fun saveDepartment(department: String) {

        context.dataStore.edit { preferences ->
            preferences[USER_DEPARTMENT_KEY] = department
        }
        Log.d("HECHO", department)
    }
    //get the saved Province
    val getProvince: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_PROVINCE_KEY] ?: ""
        }

    //save Province into datastore
    suspend fun saveProvince(province: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PROVINCE_KEY] = province
        }
    }

    //get the saved email
    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }

    //save email into datastore
    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    //get the saved password
    val getPassword: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_PASSWORD_KEY] ?: ""
        }

    //save password into datastore
    suspend fun savePassword(password: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PASSWORD_KEY] = password
        }
    }


    //get the saved City
    val getCity: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_CITY_KEY] ?: ""
        }

    //save City into datastore
    suspend fun saveCity(city: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_CITY_KEY] = city
        }
    }
}