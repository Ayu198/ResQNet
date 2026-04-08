package com.example.resqnet

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class VolunteerType {
    VERIFIED_MEDICAL,
    COMMUNITY_HELPER
}

data class SkillSection(
    val title: String,
    val subtitle: String,
    val skills: List<String>
)

enum class ExperienceLevel(val label: String) {
    ZERO_TO_ONE("0-1 years"),
    ONE_TO_THREE("1-3 years"),
    THREE_TO_FIVE("3-5 years"),
    FIVE_PLUS("5+ years")
}

class VolunteerOnboardingViewModel : ViewModel() {

    var volunteerType by mutableStateOf<VolunteerType?>(null)
        private set

    val selectedSkills = mutableStateListOf<String>()

    var experienceLevel by mutableStateOf<ExperienceLevel?>(null)
        private set

    var backgroundCategory by mutableStateOf("")
        private set

    var organization by mutableStateOf("")
        private set

    var experienceSummary by mutableStateOf("")
        private set

    var safetyAgreed by mutableStateOf(false)
        private set

    var experienceLevelError by mutableStateOf<String?>(null)
        private set

    var backgroundCategoryError by mutableStateOf<String?>(null)
        private set

    var organizationError by mutableStateOf<String?>(null)
        private set

    var experienceSummaryError by mutableStateOf<String?>(null)
        private set

    fun updateVolunteerType(type: VolunteerType) {
        volunteerType = type
        selectedSkills.clear()
        experienceLevel = null
        backgroundCategory = ""
        organization = ""
        experienceSummary = ""
        safetyAgreed = false
        experienceLevelError = null
        backgroundCategoryError = null
        organizationError = null
        experienceSummaryError = null
    }

    fun isVerifiedMedical(): Boolean {
        return volunteerType == VolunteerType.VERIFIED_MEDICAL
    }

    fun isCommunityHelper(): Boolean {
        return volunteerType == VolunteerType.COMMUNITY_HELPER
    }

    fun toggleSkill(skill: String) {
        if (selectedSkills.contains(skill)) {
            selectedSkills.remove(skill)
        } else {
            selectedSkills.add(skill)
        }
    }

    fun isSkillSelected(skill: String): Boolean {
        return selectedSkills.contains(skill)
    }

    fun canContinue(): Boolean {
        return selectedSkills.isNotEmpty()
    }

    fun screenTitle(): String {
        return when (volunteerType) {
            VolunteerType.VERIFIED_MEDICAL -> "Select medical skills"
            VolunteerType.COMMUNITY_HELPER -> "Select support skills"
            null -> "Select skills"
        }
    }

    fun screenSubtitle(): String {
        return when (volunteerType) {
            VolunteerType.VERIFIED_MEDICAL ->
                "Choose the medical skills you can confidently provide during emergencies."
            VolunteerType.COMMUNITY_HELPER ->
                "Choose the basic support skills you can safely provide nearby."
            null ->
                "Choose the skills that best describe your emergency support ability."
        }
    }

    fun skillSections(): List<SkillSection> {
        return when (volunteerType) {
            VolunteerType.VERIFIED_MEDICAL -> listOf(
                SkillSection(
                    title = "Core Medical Skills",
                    subtitle = "Foundational emergency response skills.",
                    skills = listOf(
                        "First Aid",
                        "CPR",
                        "AED Usage",
                        "Basic Life Support",
                        "Bleeding Control"
                    )
                ),
                SkillSection(
                    title = "Advanced Response",
                    subtitle = "For trained medical volunteers.",
                    skills = listOf(
                        "Fracture Support",
                        "Trauma Response",
                        "Oxygen Support Handling",
                        "Patient Transport",
                        "Emergency Triage"
                    )
                ),
                SkillSection(
                    title = "Professional Background",
                    subtitle = "If applicable, choose your area of practice.",
                    skills = listOf(
                        "Nursing",
                        "Paramedic / EMT",
                        "Doctor / MBBS",
                        "Physiotherapy",
                        "Pharmacist"
                    )
                )
            )

            VolunteerType.COMMUNITY_HELPER -> listOf(
                SkillSection(
                    title = "Basic Support",
                    subtitle = "Safe, practical help for nearby emergencies.",
                    skills = listOf(
                        "Basic First Aid",
                        "CPR Awareness",
                        "Emergency Calling Support",
                        "Ambulance Coordination",
                        "Patient Transport Assistance"
                    )
                ),
                SkillSection(
                    title = "Scene Assistance",
                    subtitle = "Help keep the situation organized and calm.",
                    skills = listOf(
                        "Crowd Management",
                        "Staying With Victim",
                        "Finding Nearby Hospital",
                        "Elderly Assistance",
                        "Emergency Guidance"
                    )
                )
            )

            null -> emptyList()
        }
    }

    fun backgroundOptions(): List<String> {
        return when (volunteerType) {
            VolunteerType.VERIFIED_MEDICAL -> listOf(
                "Doctor / Medical Student",
                "Nurse",
                "Paramedic / EMT",
                "Hospital Staff",
                "First Aid Trained",
                "Other"
            )

            VolunteerType.COMMUNITY_HELPER -> listOf(
                "School / College Training",
                "NGO Volunteer",
                "Family / Community Trained",
                "Self Learned",
                "First Aid Workshop",
                "Other"
            )

            null -> emptyList()
        }
    }

    fun yearsOptions(): List<ExperienceLevel> {
        return ExperienceLevel.entries
    }

    fun selectExperienceLevel(level: ExperienceLevel) {
        experienceLevel = level
        experienceLevelError = null
    }

    fun selectBackgroundCategory(category: String) {
        backgroundCategory = category
        backgroundCategoryError = null
    }

    fun onOrganizationChange(value: String) {
        organization = value
        organizationError = null
    }

    fun onExperienceSummaryChange(value: String) {
        experienceSummary = value
        experienceSummaryError = null
    }

    fun onSafetyAgreedChange(value: Boolean) {
        safetyAgreed = value
    }

    fun validateExperienceStep(): Boolean {
        var valid = true

        if (isVerifiedMedical() && experienceLevel == null) {
            experienceLevelError = "Please select your experience level"
            valid = false
        } else {
            experienceLevelError = null
        }

        if (backgroundCategory.isBlank()) {
            backgroundCategoryError = "Please select a background category"
            valid = false
        } else {
            backgroundCategoryError = null
        }

        if (isVerifiedMedical() && organization.isBlank()) {
            organizationError = "Please enter your institution or organization"
            valid = false
        } else {
            organizationError = null
        }

        if (experienceSummary.isBlank()) {
            experienceSummaryError = if (isVerifiedMedical()) {
                "Please add a short description of your medical background"
            } else {
                "Please add a short description of your background"
            }
            valid = false
        } else {
            experienceSummaryError = null
        }

        if (isCommunityHelper() && !safetyAgreed) {
            valid = false
        }

        return valid
    }
    // uploading certificates
    var certificatePdfUri by mutableStateOf<Uri?>(null)
        private set

    var certificateFileName by mutableStateOf("")
        private set

    var certificateError by mutableStateOf<String?>(null)
        private set

    fun setCertificatePdf(uri: Uri, fileName: String) {
        certificatePdfUri = uri
        certificateFileName = fileName
        certificateError = null
    }

    fun clearCertificatePdf() {
        certificatePdfUri = null
        certificateFileName = ""
    }

    fun hasCertificatePdf(): Boolean {
        return certificatePdfUri != null
    }

    fun validateCertificateUpload(): Boolean {
        return if (certificatePdfUri == null) {
            certificateError = "Please upload your combined certification PDF"
            false
        } else {
            certificateError = null
            true
        }
    }
}
