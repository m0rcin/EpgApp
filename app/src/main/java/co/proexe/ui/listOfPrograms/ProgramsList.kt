package co.proexe.ui.listOfPrograms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.proexe.R
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun ProgramsList() {
    val viewModel: ProgramsListViewModel = hiltViewModel()
    val poppins = Font(R.font.poppins_semibold)
    val poppinsLight = Font(R.font.poppins_light)


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

    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontFamily = FontFamily(poppins)
                    )
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
                            Text(text = listItem.title, fontFamily = FontFamily(poppinsLight))
                        }
                    }
                }
        }
    }

}