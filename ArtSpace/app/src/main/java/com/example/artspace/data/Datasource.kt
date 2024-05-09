package com.example.artspace.data

import com.example.artspace.R
import com.example.artspace.model.Artwork

fun loadArtworks(): List<Artwork> =
    listOf(
        Artwork(
            imageId = R.drawable.art_1,
            titleId = R.string.art_1_name,
            artistId = R.string.art_1_artist,
            yearId = R.string.art_1_year,
        ),
        Artwork(
            imageId = R.drawable.art_2,
            titleId = R.string.art_2_name,
            artistId = R.string.art_2_artist,
            yearId = R.string.art_2_year,
        ),
        Artwork(
            imageId = R.drawable.art_3,
            titleId = R.string.art_3_name,
            artistId = R.string.art_3_artist,
            yearId = R.string.art_3_year,
        ),
    )
