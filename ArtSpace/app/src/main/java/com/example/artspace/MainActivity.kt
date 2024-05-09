package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.data.loadArtworks
import com.example.artspace.model.Artwork
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val artworks = loadArtworks()
    var artIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    fun next() {
        artIndex = (artIndex + 1) % artworks.size
    }

    fun prev() {
        artIndex = (artIndex + artworks.size - 1) % artworks.size
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArtCanvas(artworks[artIndex], modifier = Modifier.weight(1f))
        ArtLabel(artworks[artIndex], modifier = Modifier.fillMaxWidth())
        GalleryButtons(next = ::next, prev = ::prev, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ArtCanvas(art: Artwork, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Surface(
            modifier = Modifier.align(Alignment.Center), shadowElevation = 12.dp
        ) {
            Image(
                modifier = Modifier.padding(32.dp),
                painter = painterResource(id = art.imageId),
                contentDescription = stringResource(id = art.titleId),
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ArtLabel(art: Artwork, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.padding(32.dp), tonalElevation = 32.dp) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .size(128.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = art.titleId),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Light
            )
            FlowRow {
                Text(
                    text = stringResource(id = art.artistId),
                    fontWeight = FontWeight.Bold,
                )
                Text(text = " (${stringResource(id = art.yearId)})")
            }
        }
    }
}

@Composable
fun GalleryButtons(next: () -> Unit, prev: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        val btnModifier = Modifier.weight(1f)

        Button(modifier = btnModifier, onClick = prev) {
            Text(
                text = stringResource(id = R.string.prev_btn)
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Button(modifier = btnModifier, onClick = next) {
            Text(
                text = stringResource(id = R.string.next_btn)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
