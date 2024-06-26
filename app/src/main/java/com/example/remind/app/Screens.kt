package com.example.remind.app

sealed class Screens(val route: String) {
    object Splash: Screens("splash") {
        object SplashScreen: Screens("splash_screen")
    }
    object Register: Screens("register") {
        object Login: Screens("login")
        object SelectType: Screens("selecttype")
        object OnBoardingUserInfo: Screens("onboarding_userinfo")
        object OnBoardingPatience: Screens("patience_onboarding")
        object OnBoardingCheckDoctor: Screens("doctor_1")
        object OnBoardingLoadingDoctor: Screens("doctor_2")
        object OnBoardingCenter: Screens("center_onboarding")
        object OnBoardingFinal: Screens("final_onboarding")
    }

    object Doctor: Screens("doctor") {
        object DoctorMain: Screens("DoctorMain")
        object DoctorPatienceRegister: Screens("PatienceRegister_doctor")
        object PatienceManage: Screens("PatienceManage_doctor")
        object PrescriptionUpdate: Screens("PatienceUpdate_doctor")
        object ManageMedicine: Screens("PatienceMedicine_doctor")
        object ManageMood: Screens("PatienceMood_doctor")
        object ExDoctorBottomSheet: Screens("doctor_example")
    }
    object Center: Screens("center") {
        object CenterMain: Screens("CenterMain")
        object CenterPatienceRegister: Screens("PatienceRegister_center")
    }
    object Patience: Screens("patience") {
        object Home: Screens("HomeScreen") {
            object WritingMoodStep1: Screens("writing_step1")
            object WritingMoodStep2: Screens("writing_step2")
            object WritingMoodStep2Feeling: Screens("writing_step2_1")
            object WritingMoodStep2Last: Screens("writing_step2_2")
            object WritingMoodStep3: Screens("writing_step3")
            object SplashCheering: Screens("cheering")
        }
        object MoodChart: Screens("MoodChart") {
            object ExMoodChartBottomSheet:Screens("bottomsheet")
        }
        object Medicine: Screens("Medicine") {
            object AlarmSetting: Screens("alarm_setting")
        }
        object MyPage: Screens("MyPage")
    }
}
