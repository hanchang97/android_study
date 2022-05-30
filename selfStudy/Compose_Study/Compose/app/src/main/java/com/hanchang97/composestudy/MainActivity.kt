package com.hanchang97.composestudy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanchang97.composestudy.ui.theme.ComposeStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ComposeStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting(name = "Compose")
                    MyStudy()
                }
            }
        }
    }
}

// 곧 View
@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        textAlign = TextAlign.Center
    )

    // 현재 영역에서는 align을 사용할 수 없음, Text 가 단독으로 있는 상태여서
}

@Composable
fun MyStudy() {
    val context = LocalContext.current

    Column() {

        Text(
            text = "hihi",
            modifier = Modifier
                .padding(12.dp)
                .align(CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 1f)
        )

        Button(
            onClick = {
                context.startActivity(Intent(context, SecondActivity::class.java))
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Go To Second")
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Button(
            onClick = {
                context.startActivity(Intent(context, LazyColumnActivity::class.java))
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Go To LazyColumn")
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Button(
            onClick = {
                context.startActivity(Intent(context, LazyRowActivity::class.java))
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Go To LazyRow")
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Button(
            onClick = {
                context.startActivity(Intent(context, LazyHorizontalGridActivity::class.java))
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Go To LazyHorizontalGrid")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStudyTheme {
        Greeting("Android")
    }
}