package co.proexe.model.data

import java.util.UUID

data class MenuItemData(
    val id: UUID = UUID.randomUUID(),
    val menuIcon: Int,
    val text: Int
)