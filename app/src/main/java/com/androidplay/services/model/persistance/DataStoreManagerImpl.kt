package com.androidplay.services.model.persistance

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.androidplay.services.model.model.Weather
import com.androidplay.services.utils.Constants.DATASTORE_PREF_NAME
import com.androidplay.services.utils.Extensions.toCelsius
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val TEMP_PREF_KEY = stringPreferencesKey("TEMP_PREF")
private val AREA_PREF_KEY = stringPreferencesKey("AREA_KEY")
private val Context.weatherPreferenceDataStore: DataStore<Preferences> by preferencesDataStore(
    name = DATASTORE_PREF_NAME
)

class DataStoreManagerImpl(private val context: Context): DataStoreManager {

    override suspend fun saveData(weather: Weather) {
        context.weatherPreferenceDataStore.edit { preferences ->
            preferences[TEMP_PREF_KEY] = weather.main.temp.toCelsius()
            preferences[AREA_PREF_KEY] = weather.name
        }
    }

    override suspend fun getAreaName(): Flow<String?> {
        return context.weatherPreferenceDataStore.data.map { preferences ->
            preferences[AREA_PREF_KEY]
        }
    }

    override suspend fun getTemperature(): Flow<String?> {
        return context.weatherPreferenceDataStore.data.map { preferences ->
            preferences[TEMP_PREF_KEY]
        }
    }
}