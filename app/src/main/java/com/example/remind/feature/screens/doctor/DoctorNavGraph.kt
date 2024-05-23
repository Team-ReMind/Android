package com.example.remind.feature.screens.doctor

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.remind.app.Screens

fun NavGraphBuilder.DoctorGraph(
    navHostController: NavHostController,
    doctorViewModel: DoctorViewModel
) {
    navigation(
        route = Screens.Doctor.route,
        startDestination = Screens.Doctor.DoctorMain.route
    ) {
        composable(route = Screens.Doctor.DoctorMain.route) {
            DoctorMain(navHostController, doctorViewModel)
        }
        composable(route = Screens.Doctor.DoctorPatienceRegister.route) {

            DoctorRegisterScreen(navHostController, doctorViewModel)
        }
        composable(route = Screens.Doctor.PatienceManage.route) {
            PatientManageScreen(navHostController, doctorViewModel)
        }
        composable(route = Screens.Doctor.PrescriptionUpdate.route) {
            DoctorPrescriptionUpdateScreen(navHostController)
        }
        composable(route = Screens.Doctor.ManageMedicine.route) {
            DoctorMedicineManage(navHostController, doctorViewModel)
        }
        composable(route = Screens.Doctor.ManageMood.route) {
            DoctorMoodChaartScreen(navHostController, doctorViewModel)
    }
        composable(route = Screens.Doctor.ExDoctorBottomSheet.route) {
            DoctorBottomSheet(navHostController)
        }
    }
}