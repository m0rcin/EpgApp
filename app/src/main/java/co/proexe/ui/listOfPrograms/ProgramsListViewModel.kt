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
import co.proexe.R
import co.proexe.model.data.MenuItemData
import co.proexe.model.data.TvProgramme
import co.proexe.model.data.TvProgrammeCategory
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
        errorMessage = "tu zwróciłbym błąd po stronie api" +
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

    fun getMenuItemsList(): List<MenuItemData> {

        return TvProgrammeCategory.values().map {

            val imageResource = when (it) {
                TvProgrammeCategory.ALL -> R.drawable.ic_all
                TvProgrammeCategory.KIDS -> R.drawable.ic_kids
                TvProgrammeCategory.EDUCATIONAL -> R.drawable.ic_edu
                TvProgrammeCategory.MOVIES_AND_SERIES -> R.drawable.ic_movie
                TvProgrammeCategory.INFO -> R.drawable.ic_info
                TvProgrammeCategory.MUSIC -> R.drawable.ic_music
                TvProgrammeCategory.GENERAL -> R.drawable.ic_general
                TvProgrammeCategory.SPORT -> R.drawable.ic_sport
                TvProgrammeCategory.LIFESTYLE -> R.drawable.ic_styl
                else -> R.drawable.ic_fav
            }

            val stringResource = when (it) {
                TvProgrammeCategory.ALL -> R.string.menu_item_all
                TvProgrammeCategory.KIDS -> R.string.menu_item_kids
                TvProgrammeCategory.EDUCATIONAL -> R.string.menu_item_edu
                TvProgrammeCategory.MOVIES_AND_SERIES -> R.string.menu_item_movies
                TvProgrammeCategory.INFO -> R.string.menu_item_news
                TvProgrammeCategory.MUSIC -> R.string.menu_item_mtv
                TvProgrammeCategory.GENERAL -> R.string.menu_item_general
                TvProgrammeCategory.SPORT -> R.string.menu_item_sport
                TvProgrammeCategory.LIFESTYLE -> R.string.menu_item_lifestyle
                else -> R.string.menu_item_favourites
            }

            MenuItemData(text = stringResource, menuIcon = imageResource)
        }
    }
}