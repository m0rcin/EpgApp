package co.proexe.ui.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.proexe.R
import co.proexe.ui.utils.noPadding
import co.proexe.ui.utils.poppins
import co.proexe.ui.utils.poppinsLight

@Composable
fun UserProfile() {
    Divider(
        thickness = Dp.Hairline,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .padding(vertical = 16.dp)
    ) {
        Spacer(modifier = Modifier.width(64.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_account),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .wrapContentHeight()
                .size(56.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.drawer_user_title),
                style = MaterialTheme.typography.subtitle1.merge(noPadding),
                fontFamily = FontFamily(poppins),
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                Text(
                    text = stringResource(id = R.string.drawer_user_subtitle),
                    style = MaterialTheme.typography.body2.merge(noPadding),
                    fontFamily = FontFamily(poppinsLight)
                )
            }
        }
    }
    Divider(
        thickness = Dp.Hairline,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
    )
}