package co.proexe

import co.proexe.model.data.TvProgramme
import co.proexe.model.repository.LocalEpgRepository
import co.proexe.model.repository.TimeRepository
import java.util.Date
import javax.inject.Inject

class EpgUseCaseImpl @Inject constructor(
    private val localEpgRepository: LocalEpgRepository,
    private val timeRepository: TimeRepository
) : EpgUseCase {

    override suspend fun fetchProgrammesForDateTime(dateTime: Date, amount: Int): List<TvProgramme> {
        return localEpgRepository.getProgrammesForDateTime(dateTime, amount)
    }

    override suspend fun fetchDayTiles() {
        return timeRepository.getDayTiles()
    }

    override fun getCurrentTime(): Date {
        return timeRepository.getCurrentTime()
    }
}