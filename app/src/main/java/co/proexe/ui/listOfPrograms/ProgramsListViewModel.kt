package co.proexe.ui.listOfPrograms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.proexe.EpgUseCase
import co.proexe.model.data.TvProgramme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramsListViewModel @Inject constructor(
    private val useCase: EpgUseCase,
) : ViewModel(), LifecycleObserver {

    private val _itemsState = mutableStateListOf<TvProgramme>()
    val itemsState: List<TvProgramme> = _itemsState

    var loading by mutableStateOf(true)

    private val _showErrorMsg = mutableStateOf(false)
    val showErrorMsg = _showErrorMsg


    private val _emptyProgramsList = mutableStateOf(false)
    val emptyProgramsList = _emptyProgramsList

    private var errorMessage by mutableStateOf("")
    private val handler = CoroutineExceptionHandler { _, _ ->
        errorMessage = "tu zrwóciłbym błąd po stronie api" +
                "((exception as HttpException).response() as Response).raw()"
        _showErrorMsg.value = true
    }

    private fun <T> SnapshotStateList<T>.swapList(list: List<T>) {
        clear()
        addAll(list)
    }

    private var fetchingTvProgrammeJob: Job? = null
    suspend fun fetchTvProgramme() {
        fetchingTvProgrammeJob = viewModelScope.launch(handler) {

            loading = true
            _itemsState.clear()
            delay(789L)
            val dateNow = useCase.getCurrentTime()
            val tvProgramme = useCase.fetchProgrammesForDateTime(dateNow, 10)
            _itemsState.swapList(tvProgramme)
            _emptyProgramsList.value = tvProgramme.isEmpty()
            loading = false
        }
    }

    fun cancelFetchingTvProgrammeJob() {
        fetchingTvProgrammeJob?.cancel()
    }
}