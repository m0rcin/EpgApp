package co.proexe.ui.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import co.proexe.ui.utils.noPadding
import co.proexe.ui.utils.poppinsBold

@Composable
fun DrawerHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(64.dp))
        Text(
            buildAnnotatedString {
                append(text = "tv online")
            },
            style = MaterialTheme.typography.h4.merge(noPadding),
            modifier = Modifier.alignByBaseline(),
            fontFamily = FontFamily(poppinsBold)
        )
        Box(
            modifier = Modifier
                .alignBy { it.measuredHeight }
                .offset(x = 2.dp, y = 1.dp)
                .size(size = 8.dp)
                .background(color = MaterialTheme.colors.secondary, shape = CircleShape)
        )
    }
}