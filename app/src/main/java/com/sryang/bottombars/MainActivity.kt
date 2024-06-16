package com.sryang.bottombars

import YoutubeBottomBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.bottombars.ui.theme.BottomBarsTheme
import com.sryang.library.BottomBarsProvider
import com.sryang.library.bottomAppBarTypeList

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bottomPageState = rememberPagerState { bottomAppBarTypeList.size }
            val height = 40.dp

            var selectedBottomBar by remember { mutableStateOf(bottomAppBarTypeList[0]) }

            LaunchedEffect(key1 = bottomPageState) {
                snapshotFlow {
                    bottomPageState.currentPage
                }.collect {
                    selectedBottomBar = bottomAppBarTypeList[it]
                }
            }

            BottomBarsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = selectedBottomBar.bottomBar
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.align(Alignment.Center)) {
                        Row(
                            Modifier.height(height),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "BottomBar:")
                            VerticalPager(state = bottomPageState) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(
                                        text = bottomAppBarTypeList[it].name,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomBarsTheme {
        BottomBarsProvider.YoutubeBottomBar()
    }
}