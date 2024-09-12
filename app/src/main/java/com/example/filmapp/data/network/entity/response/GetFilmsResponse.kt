package com.example.filmapp.data.network.entity.response

import com.example.filmapp.data.network.entity.MovieNetwork

data class GetFilmsResponse(val page : Int, val results : List<MovieNetwork> )