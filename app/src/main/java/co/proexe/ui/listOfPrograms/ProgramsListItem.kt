package co.proexe.ui.listOfPrograms

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ListItem
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import co.proexe.model.data.TvProgramme
import co.proexe.ui.utils.noPadding
import coil.compose.AsyncImage
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProgramsListItem(item: TvProgramme) {
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
                        Text(
                            buildAnnotatedString {
                                append(text = SimpleDateFormat(pattern, Locale.UK).format(item.startTime))
                                append(text = " – ")
                                append(text = SimpleDateFormat(pattern, Locale.UK).format(item.endTime))
                                append(text = " | ")
                            },
                            modifier = Modifier.alignByBaseline(),
                            style = LocalTextStyle.current.merge(noPadding),
                        )
                        Text(
                            buildAnnotatedString {
                                append(text = item.type)
                            },
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.alignByBaseline(),
                            style = LocalTextStyle.current.merge(noPadding),
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = item.progressPercent.toFloat() / 100,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colors.secondary,
                        backgroundColor = MaterialTheme.colors.onPrimary.copy(alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity)
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