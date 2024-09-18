package com.solodev.codingchallenge.presentation.screens.onboarding

sealed class OnboardingEvent {
    data object SaveAppEntry : OnboardingEvent()
}
