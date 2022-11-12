package co.proexe.ui.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.proexe.R
import co.proexe.model.data.NavigationDrawerItem
import co.proexe.ui.utils.bgColor
import com.google.android.material.composethemeadapter.MdcTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState) {

    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index }
    val listState = rememberLazyListState()

    val items = listOf(
        NavigationDrawerItem(R.string.drawer_main_page, false),
        NavigationDrawerItem(R.string.drawer_tv_programme, false),
        NavigationDrawerItem(R.string.drawer_channels, false),
        NavigationDrawerItem(R.string.drawer_favourites, false),
        NavigationDrawerItem(R.string.drawer_recordings, false),
        NavigationDrawerItem(R.string.drawer_movies, false),
        NavigationDrawerItem(R.string.drawer_series, false),
        NavigationDrawerItem(R.string.drawer_kids, false),
    )

    Column(
        modifier = Modifier.background(brush = bgColor)
    ) {
        Spacer(modifier = Modifier.height(52.dp))
        DrawerHeader()
        Spacer(modifier = Modifier.height(32.dp))
        UserProfile()

        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(top = 20.dp, bottom = 46.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(items) { item ->
                DrawerItem(
                    item = item,
                    index = item.labelId,
                    selected = selectedIndex == item.labelId,
                    onClick = onItemClick,
                    scope = scope,
                    scaffoldState = scaffoldState
                )
            }
        }
    }
}

@Preview(device = "spec:width=283dp,height=812dp,dpi=320", showBackground = true)
//@Preview(device = "id:TC25", showBackground = true)
@Composable
fun DrawerPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    MdcTheme {
        Drawer(scope = scope, scaffoldState = scaffoldState)
    }
}