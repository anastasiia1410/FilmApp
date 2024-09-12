package com.example.filmapp.core

class EventHandler <Event>(val filter : (Event) -> Boolean, val onEvent : (Event) -> Unit)