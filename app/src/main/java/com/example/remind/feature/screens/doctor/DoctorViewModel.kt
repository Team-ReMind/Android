package com.example.remind.feature.screens.doctor

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(

): BaseViewModel<DoctorContract.Event, DoctorContract.State, DoctorContract.Effect>(
    initialState = DoctorContract.State()
) {
    override fun reduceState(event: DoctorContract.Event) {
        viewModelScope.launch {
            when(event) {
                is DoctorContract.Event.RegisterButtonClicked -> {
                    moveToRegister()
                }
            }
        }
    }

    private fun moveToRegister() {
        viewModelScope.launch {
            postEffect(
                DoctorContract.Effect.NavigateTo (
                    destination = Screens.Doctor.DoctorPatienceRegister.route,
                    navOptions = navOptions {
                        popUpTo(Screens.Doctor.DoctorMain.route)
                    }
                )
            )
        }
    }

}