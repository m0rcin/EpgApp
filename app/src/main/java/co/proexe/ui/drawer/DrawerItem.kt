package co.proexe.ui.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import co.proexe.R
import co.proexe.model.data.NavigationDrawerItem
import co.proexe.ui.utils.noPadding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerItem(
    item: NavigationDrawerItem,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {
    val poppins = Font(R.font.poppins_semibold)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    onClick.invoke(index)
                })

    ) {
        Spacer(modifier = Modifier.width(64.dp))
        Text(
            text = stringResource(id = item.labelId),
            color = if (selected) MaterialTheme.colors.secondary else MaterialTheme.colors.onSurface,
            style = LocalTextStyle.current.merge(noPadding),
            fontFamily = FontFamily(poppins),
            modifier = Modifier
                .padding(vertical = 14.dp)

        )
    }
}