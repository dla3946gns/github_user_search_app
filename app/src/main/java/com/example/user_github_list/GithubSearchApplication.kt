package com.example.user_github_list

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GithubSearchApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // 다크 모드 해제
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}