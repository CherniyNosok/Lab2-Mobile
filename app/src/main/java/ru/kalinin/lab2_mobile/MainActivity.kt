package ru.kalinin.lab2_mobile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kalinin.lab2_mobile.ui.theme.Lab2MobileTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2MobileTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    ArtworkApp(windowSize)
                }
            }
        }
    }
}

val artworkList = listOf(
    Artwork(
        image = R.drawable.over_the_town,
        title = R.string.title_pic1,
        author = R.string.author_pic1,
        year = R.string.year_pic1
    ),
    Artwork(
        image = R.drawable.scene_with_the_officer,
        title = R.string.title_pic2,
        author = R.string.author_pic2,
        year = R.string.year_pic2
    ),
    Artwork(
        image = R.drawable.suprematist_composition,
        title = R.string.title_pic3,
        author = R.string.author_pic3,
        year = R.string.year_pic3
    ),Artwork(
        image = R.drawable.woman_with_pails,
        title = R.string.title_pic4,
        author = R.string.author_pic4,
        year = R.string.year_pic4
    ),
)

@Composable
fun Painting(artwork: Artwork, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(dimensionResource(R.dimen.standard_padding))
            .border(
                width = dimensionResource(R.dimen.outline),
                color = Color.White
            )
            .shadow(dimensionResource(R.dimen.shadow))
    ) {
        Image(
            painter = painterResource(artwork.image),
            contentDescription = stringResource(artwork.title),
            modifier = Modifier
        )
    }
}

@Composable
fun PaintingDescription(artwork: Artwork, modifier: Modifier = Modifier){
    val smallPadding = dimensionResource(R.dimen.small_padding)

    Surface(
        modifier = modifier
            .padding(dimensionResource(R.dimen.standard_padding), 0.dp)
            .clip(RoundedCornerShape(20))
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceDim),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = stringResource(artwork.title),
                fontSize = dimensionResource(R.dimen.title_font_size).value.sp,
                lineHeight = dimensionResource(R.dimen.title_font_size).value.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(smallPadding, smallPadding, smallPadding, 0.dp)
            )
            Text(
                text = stringResource(artwork.author) + " (" + stringResource(artwork.year) + ")",
                fontSize = dimensionResource(R.dimen.main_font_size).value.sp,
                lineHeight = dimensionResource(R.dimen.main_font_size).value.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(smallPadding)
            )
        }
    }
}

@Composable
fun ControlButtons(currentIndex: MutableState<Int>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(dimensionResource(R.dimen.standard_padding)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { currentIndex.value-- },
            enabled = currentIndex.value > 0,
            modifier = Modifier
                .weight(1f)
                .padding(dimensionResource(R.dimen.small_padding), 0.dp)
        ) {
            Text(
                text = stringResource(R.string.prev_button_title),
                lineHeight = dimensionResource(R.dimen.main_font_size).value.sp
            )
        }
        Button(
            onClick = { currentIndex.value++ },
            enabled = currentIndex.value < artworkList.size - 1,
            modifier = Modifier
                .weight(1f)
                .padding(dimensionResource(R.dimen.small_padding), 0.dp)
        ) {
            Text(
                text = stringResource(R.string.next_button_title),
                lineHeight = dimensionResource(R.dimen.main_font_size).value.sp
            )
        }
    }
}

@Composable
fun PortraitLayout(currentIndex: MutableState<Int>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Painting(artworkList[currentIndex.value])
        PaintingDescription(artworkList[currentIndex.value])
        ControlButtons(currentIndex)
    }
}

@Composable
fun LandscapeLayout(currentIndex: MutableState<Int>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Painting(artworkList[currentIndex.value])
            PaintingDescription(artworkList[currentIndex.value])
        }
        ControlButtons(currentIndex, Modifier.weight(0.35f))
    }
}

@Composable
fun ArtworkApp(windowSizeClass: WindowSizeClass) {
    val currentIndex = rememberSaveable { mutableStateOf(0) }

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            PortraitLayout(currentIndex)
        }

        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
            LandscapeLayout(currentIndex)
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, locale = "ru")

@Composable
fun GreetingPreview() {
    Lab2MobileTheme {
        val currentIndex = mutableStateOf(2)
        PortraitLayout(currentIndex)
    }
}