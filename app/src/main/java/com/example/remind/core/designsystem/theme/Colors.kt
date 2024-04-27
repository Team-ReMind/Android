package com.example.remind.core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

class Colors (
    val Black: Color = Color(0xFF000000),
    val g1: Color = Color(0xFF334155),
    val g2: Color = Color(0xFF475569),
    val g3: Color = Color(0xF64748B),
    val g4: Color = Color(0xFF94A3B8),
    val g5: Color = Color(0xFFCBD5E1),
    val g6: Color = Color(0xFFE2E8F0),
    val g7: Color = Color(0xFFF1F5F9),
    val g8: Color = Color(0xFFF8FAFC),

    val m1: Color = Color(0xFF2886A4),
    val m2: Color = Color(0xFF49B3D5),
    val m3: Color = Color(0xFF80CAE2),
    val m4: Color = Color(0xFFA4E1F4),
    val m5: Color = Color(0xFFB6E1EE),
    val m6: Color = Color(0xFFDBF0F7),
    val m7: Color = Color(0xFFF7FDFF),

    val p1: Color = Color(0xFFFFCB14),
    val p2: Color = Color(0xFFF3A83B),
    val p3: Color = Color(0xFFFFE896),
    val s1: Color = Color(0xFFF8A094),
    val s2: Color = Color(0xFFF78D86),
    val text: Color = Color(0xFF1E293B),
    val button: Color = Color(0xFF475569),
    val icon: Color = Color(0xFF4B5563)

)

val LocalColors = staticCompositionLocalOf { Colors() }