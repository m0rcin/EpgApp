package co.proexe.ui.listOfPrograms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.proexe.R
import co.proexe.model.data.DayTile
import co.proexe.ui.utils.bgColor
import co.proexe.ui.utils.noPadding
import co.proexe.ui.utils.poppins
import com.google.android.material.composethemeadapter.MdcTheme
import kotlinx.coroutines.launch

@Composable
fun DaysListRow(days: List<DayTile>) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var horizontalFirstVisibleItemIndex = remember { derivedStateOf { 2 } }
    var horizontalFirstVisibleItemScrollOffset = remember { derivedStateOf { 0 } }
    var day by remember { mutableStateOf(2) }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Dp.Hairline),
        modifier = Modifier.background(bgColor),
        state = lazyListState
    ) {
        itemsIndexed(items = days) { index, item ->
            val isSelected = index == day
            val style = if (isSelected) {
                MaterialTheme.typography.caption.copy(
                    fontFamily = FontFamily(poppins),
                    color = MaterialTheme.colors.secondary,
                    fontSize = 12.sp
                ).merge(noPadding)
            } else {
                MaterialTheme.typography.caption.copy(
                    fontFamily = FontFamily(poppins),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.33f),
                    fontSize = 12.sp,
                ).merge(noPadding)
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(IntrinsicSize.Max)
                    .background(MaterialTheme.colors.background)
                    .clickable(
                        enabled = true,
                        onClick = {
                            day = index
                            horizontalFirstVisibleItemIndex = derivedStateOf { index }
                            horizontalFirstVisibleItemScrollOffset = derivedStateOf { index }
                            scope.launch {
                                lazyListState.animateScrollToItem(
                                    horizontalFirstVisibleItemIndex.value, horizontalFirstVisibleItemScrollOffset.value
                                )
                            }
                        }
                    )
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = stringResource(item.dayLabel),
                    style = style,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp, vertical = 18.dp)
                )
            }
        }
    }
    LaunchedEffect(horizontalFirstVisibleItemIndex, horizontalFirstVisibleItemScrollOffset) {
        lazyListState.animateScrollToItem(horizontalFirstVisibleItemIndex.value, horizontalFirstVisibleItemScrollOffset.value)
    }
}

@Preview(device = "spec:width=375px,height=1280px,dpi=240")
@Composable
fun DaysListItemPrev() {
    val days = listOf(
        DayTile(-2, R.string.day_before_yesterday),
        DayTile(-1, R.string.yesterday),
        DayTile(0, R.string.today),
        DayTile(1, R.string.tomorrow),
        DayTile(2, R.string.day_after_tomorrow)
    )

    MdcTheme {
        DaysListRow(days)
    }
}