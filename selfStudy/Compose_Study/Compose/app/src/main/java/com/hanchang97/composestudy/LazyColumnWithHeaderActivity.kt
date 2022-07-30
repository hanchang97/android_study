package com.hanchang97.composestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanchang97.composestudy.model.SampleData
import com.hanchang97.composestudy.ui.theme.ComposeStudyTheme

class LazyColumnWithHeaderActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sampleDataList1 = mutableListOf<SampleData>()
        sampleDataList1.add(SampleData(1, R.drawable.ic_launcher_foreground))
        sampleDataList1.add(SampleData(2, R.drawable.ic_launcher_foreground))
        sampleDataList1.add(SampleData(3, R.drawable.ic_launcher_foreground))

        val sampleDataList2 = mutableListOf<SampleData>()
        sampleDataList2.add(SampleData(1, R.drawable.ic_launcher_foreground))
        sampleDataList2.add(SampleData(2, R.drawable.ic_launcher_foreground))
        sampleDataList2.add(SampleData(3, R.drawable.ic_launcher_foreground))

        val sampleDataList3 = mutableListOf<SampleData>()
        sampleDataList3.add(SampleData(1, R.drawable.ic_launcher_foreground))
        sampleDataList3.add(SampleData(2, R.drawable.ic_launcher_foreground))
        sampleDataList3.add(SampleData(3, R.drawable.ic_launcher_foreground))

        setContent {
            val scrollState = rememberScrollState()
            ComposeStudyTheme {
                Surface(color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()) {
                    //GreetingHeaderActivity(name = "lazy column with header study")
                   LazyColumn(){
//                       item {
//                           LazyCoumnWithHeader1(list = sampleDataList1)
//                       }
//                       item {
//                           LazyCoumnWithHeader2(list = sampleDataList2)
//                       }
//                       item {
//                           LazyCoumnWithHeader3(list = sampleDataList2)
//                       }
                       // 위과 같이 하면 오류 발생
                   // Vertically scrollable component was measured with an infinity maximum height constraints, which is disallowed.
                   // One of the common reasons is nesting layouts like LazyColumn and Column(Modifier.verticalScroll()).
                   // If you want to add a header before the list of items please add a header as a separate item() before the main items() inside the LazyColumn scope.
                   // There are could be other reasons for this to happen: your ComposeView was added into a LinearLayout with some weight, you applied Modifier.wrapContentSize(unbounded = true) or wrote a custom layout.
                   // Please try to remove the source of infinite constraints in the hierarchy above the scrolling container.

                       item{ Header(title = "1")}

                       items(sampleDataList1){
                               sampleData ->
                           ListItem(sampleData = sampleData)
                       }

                       item { LastHeader(title = "1")}
                        //
                       item{ Header(title = "2")}

                       items(sampleDataList2){
                               sampleData ->
                           ListItem(sampleData = sampleData)
                       }

                       item { LastHeader(title = "2")}
                        //
                       item{ Header(title = "3")}

                       items(sampleDataList3){
                               sampleData ->
                           ListItem(sampleData = sampleData)
                       }

                       item { LastHeader(title = "3")}
                   }
                }
            }
        }
    }
}

@Composable
private fun GreetingHeaderActivity(name: String) {
    Text(text = "Hello $name!")
}


@Composable
private fun LazyCoumnWithHeader1(list: List<SampleData>){
    LazyColumn(){
        item {
            // header
            Header(title = "1")
        }
        items(list){ sampleData ->
            ListItem(sampleData = sampleData)
        }
        item {
            // last header
            LastHeader(title = "1")
        }
    }
}

@Composable
private fun LazyCoumnWithHeader2(list: List<SampleData>){
    LazyColumn(){
        item {
            // header
            Header(title = "2")
        }
        items(list){ sampleData ->
            ListItem(sampleData = sampleData)
        }
        item {
            // last header
            LastHeader(title = "2")
        }
    }
}

@Composable
private fun LazyCoumnWithHeader3(list: List<SampleData>){
    LazyColumn(){
        item {
            // header
            Header(title = "3")
        }
        items(list){ sampleData ->
            ListItem(sampleData = sampleData)
        }
        item {
            // last header
            LastHeader(title = "3")
        }
    }
}

@Composable
private fun Header(title: String){
    Text(text = "Header $title!")
}

@Composable
private fun LastHeader(title: String){
    Text(text = "Last Header $title!")
}

@Composable
fun ListItem(sampleData: SampleData) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()

    ) {
        Image(
            painter = painterResource(id = sampleData.image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(5.dp)
        )
        Text(
            text = sampleData.id.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ListItem2(sampleData: SampleData) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()

    ) {
        Image(
            painter = painterResource(id = sampleData.image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(5.dp)
        )
        Text(
            text = sampleData.id.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}