package com.example.remind.feature.screens.auth.splash

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.network.interceptor.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager
): BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>(
    initialState = SplashContract.State()
) {
    init {
        viewModelScope.launch {
            val userType = tokenManager.getUserType().first()
            updateState(
                currentState.copy(
                    userType = userType
                )
            )
        }
    }

    override fun reduceState(event: SplashContract.Event) {
    }

    fun checkUserState() {
        val userType = currentState.userType
        when(userType) {
            "ROLE_DOCTOR" -> navigateToDoctor()
            "ROLE_CENTER" -> navigateToCenter()
            "ROLE_USER" -> navigateToPatience()
            else -> navigateToLogin()
        }
    }

    fun navigateToDoctor() {
        postEffect(
            SplashContract.Effect.NavigateTo(
                destination = Screens.Doctor.DoctorMain.route,
                navOptions = navOptions {
                    popUpTo(Screens.Splash.SplashScreen.route) {
                        inclusive = true
                    }
                }
            ))
    }

    fun navigateToCenter() {
        postEffect(
            SplashContract.Effect.NavigateTo(
                destination = Screens.Center.CenterMain.route,
                navOptions = navOptions {
                    popUpTo(Screens.Splash.SplashScreen.route) {
                        inclusive = true
                    }
                }
            ))
    }

    fun navigateToPatience() {
        postEffect(
            SplashContract.Effect.NavigateTo(
                destination = Screens.Patience.route,
                navOptions = navOptions {
                    popUpTo(Screens.Splash.SplashScreen.route) {
                        inclusive = true
                    }
                }
            ))
    }

    fun navigateToLogin() {
        postEffect(
            SplashContract.Effect.NavigateTo(
                destination = Screens.Register.Login.route,
                navOptions = navOptions {
                    popUpTo(Screens.Splash.SplashScreen.route) {
                        inclusive = true
                    }
                }
            ))
    }


}