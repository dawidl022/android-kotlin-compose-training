package com.example.lemonadestand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadestand.ui.theme.LemonadeStandTheme
import com.example.lemonadestand.ui.theme.Main
import com.example.lemonadestand.ui.theme.Button as ButtonColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeStandTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeStandApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeStandApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TopBar()
        Lemonade(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .background(Main)
            .padding(16.dp)
            .fillMaxWidth(),
        text = "Lemonade",
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var state by remember {
        mutableStateOf(LemonadeState.Tree)
    }
    var squeezeTimes by remember {
        mutableIntStateOf(randomSqueezeTimes())
    }

    fun stateTransition() {
        if (state == LemonadeState.Squeeze && squeezeTimes > 1) {
            squeezeTimes--
        } else {
            state = LemonadeState.entries[(state.ordinal + 1) % LemonadeState.entries.size]
            squeezeTimes = randomSqueezeTimes()
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LemonadeButton(state = state, handleClick = ::stateTransition)
        Spacer(modifier = Modifier.padding(16.dp))
        LemonadeDescription(state = state)
    }
}

private fun randomSqueezeTimes() = (2..4).random()

@Composable
fun LemonadeButton(modifier: Modifier = Modifier, state: LemonadeState, handleClick: () -> Unit) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
        onClick = handleClick,
        shape = RoundedCornerShape(48.dp),
    ) {
        Image(
            painter = painterResource(id = state.imageId),
            contentDescription = stringResource(id = state.titleId)
        )
    }
}

@Composable
fun LemonadeDescription(modifier: Modifier = Modifier, state: LemonadeState) {
    Text(modifier = modifier, text = stringResource(id = state.descriptionId))
}

enum class LemonadeState(val imageId: Int, val descriptionId: Int, val titleId: Int) {
    Tree(R.drawable.lemon_tree, R.string.lemon_tree, R.string.lemon_tree_title),
    Squeeze(R.drawable.lemon_squeeze, R.string.lemon_squeeze, R.string.lemon_squeeze_title),
    Drink(R.drawable.lemon_drink, R.string.lemon_drink, R.string.lemon_drink_title),
    Restart(R.drawable.lemon_restart, R.string.lemon_restart, R.string.lemon_restart_title)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LemonadeStandTheme {
        LemonadeStandApp()
    }
}
