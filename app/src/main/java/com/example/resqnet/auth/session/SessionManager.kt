package com.example.resqnet.auth.session

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("session_data")
class SessionManager(private val context: Context) {
    companion object {
        private val JWT_TOKEN = stringPreferencesKey("jwt_token")
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences: MutablePreferences ->
            preferences[JWT_TOKEN] = token
        }
    }

    suspend fun getToken() : String? {
        return context.dataStore.data.map { preferences : Preferences -> preferences[JWT_TOKEN] }
            .first();
    }

    suspend fun clearSession() {
        context.dataStore.edit { preferences -> preferences.clear() }
    }
}
