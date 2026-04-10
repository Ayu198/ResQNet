package com.example.resqnet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class VolunteerHomeViewModel : ViewModel() {

    var volunteerName by mutableStateOf("Ayush")
        private set

    var volunteerRole by mutableStateOf("Verified Medical Volunteer")
        private set

    var titleMessage by mutableStateOf("Responder console")
        private set

    var subtitleMessage by mutableStateOf("Stay active, review alerts, and respond within minutes.")
        private set

    var isActive by mutableStateOf(true)
        private set

    var skillsSummary by mutableStateOf("Verified for First Aid, CPR, and Basic Life Support")
        private set

    var statusMessage by mutableStateOf("You'll receive critical medical alerts in your nearby area.")
        private set

    var isVerified by mutableStateOf(true)
        private set

    var hasActiveAlert by mutableStateOf(true)
        private set

    var alertType by mutableStateOf("Possible cardiac distress")
        private set

    var alertLocation by mutableStateOf("Sector 12 market road")
        private set

    var alertDistance by mutableStateOf("450 m away")
        private set

    var alertPriority by mutableStateOf("High priority")
        private set

    var alertSupportMessage by mutableStateOf("Ambulance and hospital have already been informed.")
        private set

    var alertInstructionMessage by mutableStateOf("Review the case details before you accept the alert.")
        private set

    var acceptedToday by mutableStateOf("03")
        private set

    var responseRating by mutableStateOf("4.8")
        private set

    var recentResponseTitle by mutableStateOf("Accident support completed successfully")
        private set

    var recentResponseSubtitle by mutableStateOf("Yesterday • 1.1 km away • Hospital handoff confirmed")
        private set

    fun onToggleActive(enabled: Boolean) {
        isActive = enabled
    }

    fun markAlertAccepted() {
        hasActiveAlert = false
    }

    fun showSampleAlert(
        type: String,
        location: String,
        distance: String,
        priority: String
    ) {
        hasActiveAlert = true
        alertType = type
        alertLocation = location
        alertDistance = distance
        alertPriority = priority
    }
}
