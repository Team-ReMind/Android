package com.example.remind.feature.screens.doctor

import android.annotation.SuppressLint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.remind.app.Screens

@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.DoctorGraph(
    navHostController: NavHostController,
    //doctorViewModel: DoctorViewModel
) {
    navigation(
        route = Screens.Doctor.route,
        startDestination = Screens.Doctor.DoctorMain.route
    ) {
        composable(route = Screens.Doctor.DoctorMain.route) {
            val doctorViewModel: DoctorViewModel = hiltViewModel(navHostController.getBackStackEntry(Screens.Doctor.route))
            DoctorMain(navHostController, doctorViewModel)
        }
        composable(route = Screens.Doctor.DoctorPatienceRegister.route) {
            val doctorViewModel: DoctorViewModel = hiltViewModel(navHostController.getBackStackEntry(Screens.Doctor.route))
            DoctorRegisterScreen(navHostController, doctorViewModel)
        }
        composable(route = Screens.Doctor.PatienceManage.route) {
            val doctorViewModel: DoctorViewModel = hiltViewModel(navHostController.getBackStackEntry(Screens.Doctor.route))
            PatientManageScreen(navHostController, doctorViewModel)
        }
        composable(route = Screens.Doctor.PrescriptionUpdate.route) {
            val doctorViewModel: DoctorViewModel = hiltViewModel(navHostController.getBackStackEntry(Screens.Doctor.route))
            DoctorPrescriptionUpdateScreen(navHostController)
        }
        composable(route = Screens.Doctor.ManageMedicine.route) {
            val doctorViewModel: DoctorViewModel = hiltViewModel(navHostController.getBackStackEntry(Screens.Doctor.route))
            DoctorMedicineManage(navHostController, doctorViewModel)
        }
        composable(route = Screens.Doctor.ManageMood.route) {
            val doctorViewModel: DoctorViewModel = hiltViewModel(navHostController.getBackStackEntry(Screens.Doctor.route))
            DoctorMoodChaartScreen(navHostController, doctorViewModel)
    }
        composable(route = Screens.Doctor.ExDoctorBottomSheet.route) {
            DoctorBottomSheet(navHostController)
        }
    }
}