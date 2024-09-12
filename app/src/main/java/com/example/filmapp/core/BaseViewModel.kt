package com.example.filmapp.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BaseViewModel<Event, State>(
    private val useCases: List<UseCase<Event, State>>,
    private val reducer: Reducer<Event, State>,
    initialState: State,
) : ViewModel() {

    val _state = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    private val eventHandlers = mutableListOf<EventHandler<Event>>()

    fun doOnEvent(filter : (Event) -> Boolean, onEvent : (Event) -> Unit){
        eventHandlers.add(EventHandler(filter, onEvent))
    }

    protected fun handleEvent(event: Event) {
        val newState = reducer.reduce(event, state.value)
        _state.value = newState
        viewModelScope.launch(Dispatchers.IO) {
            eventHandlers.filter { it.filter(event) }.forEach { it.onEvent(event) }
        }
        useCases.filter { it.canHandle(event) }.forEach {
            viewModelScope.launch(Dispatchers.IO) {
                val result = it.invoke(event, newState)
                handleEvent(result)
            }
        }
    }
}