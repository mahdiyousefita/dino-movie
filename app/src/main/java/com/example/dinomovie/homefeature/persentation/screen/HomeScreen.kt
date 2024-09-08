package com.example.dinomovie.homefeature.persentation.screen

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.dinomovie.R
import com.example.dinomovie.homefeature.persentation.component.MoviesItem
import com.example.dinomovie.ui.theme.DinoMovieTheme
import com.example.dinomovie.ui.theme.Red

@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current
    HomeScreenView()

}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenView() {

    val context = LocalContext.current
    DinoMovieTheme {
        Scaffold { innerPadding ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                val (
                    recommendedMoviesList,
                    moviesList,
                    seriesList
                ) = createRefs()

                Box(modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth()
                    .height(250.dp)
                    .constrainAs(recommendedMoviesList) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    })

                val domeData = listOf("Inception", "Interstellar", "The Batman")
                CategoryList(modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(moviesList) {
                        top.linkTo(recommendedMoviesList.bottom, margin = 12.dp)
                        end.linkTo(recommendedMoviesList.end)
                        start.linkTo(recommendedMoviesList.start)
                    }, categoryTitle = "Movies",
                    context = context,
                    domeData)

                val domeSeriesData = listOf("The last of us", "Dark", "Arcane", "13 reason why")
                CategoryList(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(seriesList) {
                            top.linkTo(moviesList.bottom, margin = 12.dp)
                            end.linkTo(moviesList.end)
                            start.linkTo(moviesList.start)
                        },
                    categoryTitle = "Series",
                    context = context,
                    domeSeriesData
                )

            }
        }
    }
}

@Composable
fun CategoryList(
    modifier: Modifier,
    categoryTitle: String,
    context: Context,
    listOfItems: List<String>
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = categoryTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            TextButton(onClick = {
                Toast.makeText(context, "see all movies", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "See all", color = Red)
            }
        }

        LazyRow(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(listOfItems){ title ->
                MoviesItem(painterResource = R.drawable.ic_launcher_background,
                    text = title)
            }
        }
    }

}