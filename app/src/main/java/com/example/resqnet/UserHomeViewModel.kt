package com.example.resqnet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserHomeViewModel : ViewModel() {

    var userName by mutableStateOf("Ayush")
        private set

    var greetingTitle by mutableStateOf("Stay ready")
        private set

    var greetingSubtitle by mutableStateOf("Instant medical help when every second matters.")
        private set

    var emergencyStatusTitle by mutableStateOf("No active emergency right now")
        private set

    var emergencyStatusSubtitle by mutableStateOf("Your alerts and emergency activity will appear here.")
        private set

    var hasActiveEmergency by mutableStateOf(false)
        private set

    var activeEmergencyType by mutableStateOf("Medical emergency")
        private set

    var activeEmergencyLocation by mutableStateOf("Sector 12 market road")
        private set

    var activeEmergencyStatus by mutableStateOf("Volunteers and ambulance have been informed")
        private set

    var recentActivityTitle by mutableStateOf("No recent emergency")
        private set

    var recentActivitySubtitle by mutableStateOf("Your recent alerts and support updates will appear here.")
        private set

    fun showActiveEmergency(
        type: String,
        location: String,
        status: String
    ) {
        hasActiveEmergency = true
        activeEmergencyType = type
        activeEmergencyLocation = location
        activeEmergencyStatus = status
    }

    fun clearActiveEmergency() {
        hasActiveEmergency = false
    }
}
