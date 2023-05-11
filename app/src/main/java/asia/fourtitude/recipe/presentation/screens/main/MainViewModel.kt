package asia.fourtitude.recipe.presentation.screens.main

import asia.fourtitude.recipe.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

}