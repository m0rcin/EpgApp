package co.proexe.ui.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import co.proexe.R

val bgColor = Brush.radialGradient(
    colors = listOf(
        Color(0xFF30303A),
        Color(0xFF1E1E24),
    ),
    radius = 678.dp.value,
    center = Offset(
        x = -2.dp.value,
        y = -93.dp.value
    ),
)
val poppins = Font(R.font.poppins_semibold)
val poppinsBold = Font(R.font.poppins_bold)
val poppinsLight = Font(R.font.poppins_light)

val noPadding = TextStyle(
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)