package com.code.damahe.system.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.code.damahe.system.app.Config.Pref
import kotlinx.coroutines.flow.map

class PreferenceUtil(private val context: Context) {

    companion object {
        private val Context.newsDatastore: DataStore<Preferences> by preferencesDataStore(name = Pref.PREFERENCE_NAME)
        val onBoarding = booleanPreferencesKey(Pref.ON_BOARDING)
    }

    val checkOnBoardingStatus = context.newsDatastore.data.map {
        it[onBoarding] ?: true
    }

    suspend fun saveOnBoardingStatus(value: Boolean) {
        context.newsDatastore.edit {
            it[onBoarding] = value
        }
    }

}