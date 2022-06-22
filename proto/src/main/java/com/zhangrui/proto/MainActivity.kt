package com.zhangrui.proto

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.migrations.SharedPreferencesView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
    }

    private val Context.userPreferencesStore: DataStore<UserModel> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = UserSerializer,
        produceMigrations = { context ->
            listOf(
                SharedPreferencesMigration(
                    context,
                    USER_PREFERENCES_NAME
                ) { _: SharedPreferencesView, currentData: UserModel ->
                    currentData
                }
            )
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}