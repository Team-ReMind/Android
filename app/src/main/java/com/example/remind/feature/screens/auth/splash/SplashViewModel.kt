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
            "ROLE_DOCTOR" -> navigateToRoute(Screens.Doctor.DoctorMain.route, Screens.Splash.SplashScreen.route)
            "ROLE_CENTER" -> navigateToRoute(Screens.Center.CenterMain.route, Screens.Splash.SplashScreen.route)
            "ROLE_PATIENT" -> navigateToRoute(Screens.Patience.route, Screens.Splash.SplashScreen.route)
            else -> navigateToRoute(Screens.Register.Login.route, Screens.Splash.SplashScreen.route)
        }
    }

    fun navigateToRoute(destination: String, current: String) {
        postEffect(
            SplashContract.Effect.NavigateTo(
                destination = destination,
                navOptions = navOptions {
                    popUpTo(current) {
                        inclusive = true
                    }
                }
            ))
    }



}