package com.example.space.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.space.domain.SpacePictureDto
import com.example.space.service.PictureService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class SpaceUiState(
    val pictures: List<SpacePictureDto> = emptyList(),
    val currentRequestDate: LocalDate = LocalDate.now(),    // todo move to SpaceViewModel ? ui needs only List<SpacePicture>
    var loading: Boolean = false                            // todo move to SpaceViewModel ? ui needs only List<SpacePicture>
)

@HiltViewModel
class SpaceViewModel @Inject constructor(
    private val service: PictureService,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SpaceUiState())
    val uiState: StateFlow<SpaceUiState> = _uiState.asStateFlow()

    init {
        requestPageOfPictures()
    }

    fun requestPageOfPictures() {

        if (_uiState.value.loading) {
            return;
        }

        _uiState.update { it.copy(loading = true) }

        viewModelScope.launch {
            var spacePictures =
                service.getSpacePictures(
                    _uiState.value.currentRequestDate.minusDays(9),
                    _uiState.value.currentRequestDate)

            spacePictures = spacePictures
                .filter { i -> i.mediaType == "image" }
                .sortedWith { o: SpacePictureDto, n: SpacePictureDto ->
                    n.date.compareTo(o.date) // date descending
                }

            _uiState.update {
                it.copy(
                    currentRequestDate = _uiState.value.currentRequestDate.minusDays(10),
                    pictures = _uiState.value.pictures + spacePictures,
                    loading = false // todo at some error above loading stay true. add try-finally ?
                )
            }
        }
    }

}