package com.example.filmapp.core

interface Reducer<Event, State> {
    fun reduce(event: Event, state: State) : State
}