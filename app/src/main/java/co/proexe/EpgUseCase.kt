package co.proexe

import co.proexe.model.data.TvProgramme
import java.util.Date

interface EpgUseCase {
    suspend fun fetchProgrammesForDateTime(dateTime: Date, amount: Int): List<TvProgramme>
    suspend fun fetchDayTiles()
    fun getCurrentTime(): Date
}