package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoursesApp()
                }
            }
        }
    }
}

@Composable
fun CoursesApp(modifier: Modifier = Modifier) {
    TopicGrid(modifier = modifier, topics = DataSource.topics)
}

@Composable
fun TopicGrid(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(topics.pairs()) {
            Row {
                val mod = Modifier
                    .weight(1f)
                    .padding(4.dp)

                TopicItem(topic = it.first, modifier = mod)
                it.second?.let { TopicItem(topic = it, modifier = mod) }
            }
        }
    }
}

@VisibleForTesting
internal fun <T> Iterable<T>.pairs(): List<Pair<T, T?>> {
    val it = iterator()
    val res = mutableListOf<Pair<T, T?>>()
    var currentPair: Pair<T, T?>? = null

    while (it.hasNext()) {
        currentPair = when {
            currentPair == null -> {
                it.next() to null
            }

            currentPair.second == null -> {
                currentPair.first to it.next()
            }

            else -> {
                res.add(currentPair)
                it.next() to null
            }
        }
    }
    if (currentPair != null) {
        res.add(currentPair)
    }
    return res
}

@Composable
fun TopicItem(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        // https://stackoverflow.com/a/78281491
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Image(
                painter = painterResource(id = topic.thumbnailId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(68.dp),
                contentScale = ContentScale.FillHeight,
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(id = topic.nameId),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = "Number of associated courses",
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = topic.associatedCoursesCount.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoursesAppPreview() {
    CoursesTheme {
        CoursesApp()
    }
}

@Preview
@Composable
fun TopicItemPreview() {
    CoursesTheme {
        TopicItem(Topic(R.string.business, 78, R.drawable.business))
    }
}
