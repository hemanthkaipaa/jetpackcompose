package com.kaipa.jetpackcompose.ilearn.components

import android.R.attr.contentDescription
import android.R.attr.onClick
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.kaipa.jetpackcompose.ilearn.model.MovieData
import com.kaipa.jetpackcompose.ilearn.ui.theme.Purple40

@Composable
fun MovieRow(movie: MovieData, onItemClick: () -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    CardWrapper(
        Modifier.animateContentSize(animationSpec = tween(
        durationMillis = 500,
        easing = LinearOutSlowInEasing
    )),
        cardShapeDp = 8.dp,
        cardElevationDp = 6.dp,
        onClick = onItemClick
    ) {
        RowWrapper(
            arrangement = Arrangement.Start,
            alignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .weight(1f).size(150.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.poster)
                    .crossfade(300)
                    .crossfade(true).build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Movie Poster"
            )
            ColumnWrapper(Modifier.weight(3f), orientation = Arrangement.Center, alignment = Alignment.Start, padding = 4.dp) {
                Text(
                    text = "Movie : ${movie.title}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Director : ${movie.director}",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Released : ${movie.year}",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )

                AnimatedVisibility(expanded) {
                    ColumnWrapper(alignment = Alignment.Start) {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    )
                                ) {
                                    append("Plot: ")
                                }
                                withStyle(
                                    SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 12.sp,
                                        fontStyle = FontStyle.Italic
                                    )
                                ) {
                                    append(movie.plot)
                                }
                            },
                            modifier = Modifier.padding(4.dp),
                            lineHeight = 14.sp
                        )
                        Text(
                            text = "Genre : ${movie.genre}",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Actors : ${movie.actors}",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Rating : ${movie.imdbRating}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Purple40,
                            textAlign = TextAlign.Start
                        )
                    }
                }
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    tint = Color.DarkGray,
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    },
                    contentDescription = "icon"
                )
            }
        }

    }
}

@Composable
@Preview
fun Preview() {
    MovieRow(MovieData()) {

    }
}