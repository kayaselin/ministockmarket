package com.selinkaya.selinproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.selinkaya.selinproject.R
import com.selinkaya.selinproject.presentation.ui.HomeComposable
import com.selinkaya.selinproject.presentation.viewmodel.InstrumentViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: InstrumentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[InstrumentViewModel::class.java]
        viewModel.getInstrumentList()

        val imageList = listOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f
        )

        lifecycleScope.launch {

            viewModel.instState.collect{
                setContent {
                    HomeComposable(imageList, it)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
