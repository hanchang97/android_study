package com.hanchang97.composestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanchang97.composestudy.model.SampleData
import com.hanchang97.composestudy.ui.theme.ComposeStudyTheme

class LazyRowActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sampleDataList = mutableListOf<SampleData>()
        sampleDataList.add(SampleData(1, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(2, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(3, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(4, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(5, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(6, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(7, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(8, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(9, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(10, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(11, R.drawable.ic_launcher_background))
        sampleDataList.add(SampleData(12, R.drawable.ic_launcher_background))

        setContent {
            ComposeStudyTheme {
                Surface(modifier = Modifier.background(androidx.compose.ui.graphics.Color.White)) {
                    LazyRow(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
                        items(sampleDataList) { sampleData ->
                            ListItem(sampleData = sampleData)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ListItem(sampleData: SampleData) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                //.fillMaxWidth(0.1f)
                .width(200.dp)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(242 / 294f),
                shape = RoundedCornerShape(15)
            ) {
                Image(
                    painter = painterResource(id = sampleData.image),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = sampleData.id.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }


}