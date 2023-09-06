package com.selinkaya.selinproject.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.selinkaya.selinproject.data.model.Instrument
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun HomeComposable(
    image: List<Int>,
    data: List<Instrument>
) {

    fun Double.format(decimalPlaces: Int): String = "%.${decimalPlaces}f".format(this)
    val priceStates = data.map { mutableStateOf(it.lastPrice ?: 0.0) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        items(6) {index ->
            LaunchedEffect(Unit) {
                while (true) {
                    // Generate a random change in price
                    val priceChange = (Random.nextFloat() * 4 - 1)
                    val newIndex = index // Capture the current index in a val

                    // Update the price using MutableState
                    priceStates[newIndex].value += priceChange

                    delay(3000) // Wait for 3 seconds before the next update
                }
            }
            Row(
                modifier = Modifier
                    .padding(0.dp,16.dp,0.dp,0.dp)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    contentDescription = "",
                    painter = painterResource(image[index]),
                )
                Column {
                    Text(
                        text = data[index].symbol,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Text(
                        text = data[index].description.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = priceStates[index].value.format(2),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterEnd)

                    )
                }
            }
        }
    }
}
