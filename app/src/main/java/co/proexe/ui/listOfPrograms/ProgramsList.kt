package co.proexe.ui.listOfPrograms

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults.IndicatorBackgroundOpacity
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.proexe.R
import co.proexe.model.data.TvProgramme
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProgramsList() {
    val viewModel: ProgramsListViewModel = hiltViewModel()
    val poppins = Font(R.font.poppins_semibold)
    val poppinsLight = Font(R.font.poppins_light)
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")


    LaunchedEffect(Unit) {
        viewModel.fetchTvProgramme()
    }

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            viewModel.fetchTvProgramme()
            refreshing = false
        }
    }

    val list by remember(viewModel.itemsState) {
        derivedStateOf {
            viewModel.itemsState.sortedByDescending { it.progressPercent }
        }
    }

    @Composable
    fun EpgListItem(item: TvProgramme) {
        Column {
            val pattern = "HH:mm"

            ListItem(
                text = { Text(item.title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                secondaryText = {
                    Column {
                        Row(
                            modifier = Modifier
                                .height(IntrinsicSize.Min)
                                .fillMaxWidth()
                        ) {
                            Text(SimpleDateFormat(pattern).format(item.startTime))
                            Text(text = "–")
                            Text(SimpleDateFormat(pattern).format(item.endTime))
                            Divider(
                                modifier = Modifier
                                    .fillMaxHeight(0.67f)
                                    .padding(horizontal = 4.dp)
                                    .padding(top = 3.dp)
                                    .width(1.dp),

                                color = MaterialTheme.colors.onSurface
                            )
                            Text(item.type, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text(item.progressPercent.toString(), maxLines = 1, overflow = TextOverflow.Ellipsis)

                        }
                        Spacer(Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = item.progressPercent.toFloat() / 100,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.secondary,
                            backgroundColor = MaterialTheme.colors.onPrimary.copy(alpha = IndicatorBackgroundOpacity)
                        )
                    }
                },
                singleLineSecondaryText = true,
                trailing = {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                },
                icon = {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            )

            Spacer(Modifier.height(16.dp))
            Divider()
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = {
                    Text(
                        text = stringResource(R.string.top_app_bar_title),
                        fontFamily = FontFamily(poppins)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {/* Do Something*/ }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_hamburger),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.requiredSize(24.dp)
                        )
                    }
                }, actions = {
                    IconButton(
                        onClick = {/* Do Something*/ }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White)
                        )

                    }
                    IconButton(
                        onClick = {/* Do Something*/ }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_all),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.requiredSize(24.dp)
                        )

                    }
                })
        }
    ) { innerPadding ->
        when {
            viewModel.loading -> Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(67.dp),
                    strokeWidth = 3.dp,
                    color = MaterialTheme.colors.secondary,
                )
                Spacer(modifier = Modifier.height(133.dp))
            }
            else ->
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = refreshing),
                    onRefresh = { refreshing = true }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colors.primary)
                    ) {
                        items(
                            items = list
                        ) { listItem ->
                            EpgListItem(listItem)
                        }
                    }
                }
        }
    }

}