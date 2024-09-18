package com.solodev.codingchallenge.domain.usecase.appentry

import com.solodev.codingchallenge.domain.manager.LocalUserManager


class SaveAppEntry(
    private val localUserManager: LocalUserManager,
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}
