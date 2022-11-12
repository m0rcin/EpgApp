package co.proexe.ui.dropDown

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import co.proexe.R
import co.proexe.model.data.MenuItemData
import co.proexe.ui.utils.bgColor

@Composable
fun DropDown(
    listItems: List<MenuItemData>,
    scaffoldState: ScaffoldState
) {
    var expanded by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf(listItems.first().text) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Image(
            painter = painterResource(R.drawable.ic_all),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.requiredSize(24.dp)
        )
    }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {

        DropdownMenu(
            modifier = Modifier
                .requiredSizeIn(minWidth = 178.dp, maxWidth = 200.dp)
                .background(brush = bgColor),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            offset = DpOffset(x = (-22).dp, y = (-22).dp),
            properties = PopupProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            listItems.forEach { menuItemData ->

                val isSelected = menuItemData.text == category
                val style = if (isSelected) {
                    MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.secondary,
                        fontSize = 15.sp
                    )
                } else {
                    MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 14.sp
                    )
                }

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        category = menuItemData.text
                    },
                    enabled = scaffoldState.drawerState.isClosed
                ) {

                    Image(
                        painter = painterResource(menuItemData.menuIcon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.requiredSize(24.dp)
                    )

                    Spacer(modifier = Modifier.width(width = 12.dp))

                    Text(
                        text = stringResource(id = menuItemData.text),
                        style = style
                    )
                }
            }
        }
    }
}
