package com.example.remind.core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class RemindColors (
    val slate_800: Color = Color(0xFF1E293B),
    val slate_700: Color = Color(0xFF334155),
    val slate_600: Color = Color(0xFF475569),
    val slate_500: Color = Color(0xFF64748B),
    val slate_400: Color = Color(0xFF94A3B8),
    val slate_300: Color = Color(0xFFCBD5E1),
    val slate_200: Color = Color(0xFFE2E8F0),
    val slate_100: Color = Color(0xFFF1F5F9),
    val slate_50: Color = Color(0xFFF8FAFC),
    val main_7: Color = Color(0xFF2886A4),
    val main_6: Color = Color(0xFF49B3D5),
    val main_5: Color = Color(0xFF80CAE2),
    val main_4: Color = Color(0xFFA4E1F4),
    val main_3: Color = Color(0xFFB6E1EE),
    val main_2: Color = Color(0xFFDBF0F7),
    val main_1: Color = Color(0xFFF7FDFF),
    val sub_4: Color = Color(0xFF9D5652),
    val sub_3: Color = Color(0xFFF78D86),
    val sub_2: Color = Color(0xFFF8A094),
    val sub_1: Color = Color(0xFFFDF5F4),
    val sub_gray: Color = Color(0xFFD6D3D1),
    val point_3: Color = Color(0xFFFFCB14),
    val point_2: Color = Color(0xFFF3A83B),
    val point_1: Color = Color(0xFFFFE896),
    val text: Color = Color(0xFF303030),
    val icon: Color = Color(0xFF4B5563),
    val grayscale_3: Color = Color(0xFF939393),
    val grayscale_2: Color = Color(0xFFEEEEEE),
    val grayscale_1: Color = Color(0xFFF3F4F6),
    val white: Color = Color(0xFFFFFFFF),

)

val LocalColors = staticCompositionLocalOf { RemindColors() }