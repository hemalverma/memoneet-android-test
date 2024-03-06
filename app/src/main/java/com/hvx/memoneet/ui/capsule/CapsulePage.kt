package com.hvx.memoneet.ui.capsule

import NotePage
import QuizPage
import ResultPage
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hvx.memoneet.ui.capsule.pages.VideoPage
import com.hvx.memoneet.ui.capsule.viewmodel.AppViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@Composable
fun CapsulePage(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        PageSlider(navController)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageSlider(navController: NavController) {

    var correctAnsuwer = remember { mutableStateOf(0) }
    var wrongAnsuwer = remember { mutableStateOf(0) }


    val pagerState = rememberPagerState(pageCount = {
        4
    })

    val coroutineScope = rememberCoroutineScope()

    val moveToNext: () -> Unit = {
        coroutineScope.launch {
            if (pagerState.currentPage == 3) {
                //pagerState.animateScrollToPage(0)
                return@launch
            }
            pagerState.animateScrollToPage(pagerState.currentPage + 1)

        }
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header(pagerState = pagerState, onBackPressed = { navController.popBackStack() })
        HorizontalPager(
            userScrollEnabled = false,
            state = pagerState,
            modifier = Modifier.weight(1f, fill = true)
        ) { page ->
            // Our page content
            when (page) {
                0 -> VideoPage()
                1 -> NotePage()
                2 -> QuizPage(onRightAnswer = {
                    correctAnsuwer.value++
                }, onWrongAnswer = {
                    wrongAnsuwer.value++
                })
                3 -> ResultPage(
                    total = AppViewModel.Questions.size,
                    correct = correctAnsuwer.value,
                    wrong = wrongAnsuwer.value)

            }


        }

        if(pagerState.currentPage < 3){
        NextButton(
            title = AppViewModel.totalSteps[pagerState.currentPage + 1]?.get("title") ?: "Title",
            desc = AppViewModel.totalSteps[pagerState.currentPage + 1]?.get("desc")
                ?: "Description",
            moveNext = moveToNext
        )}else{
            NextButton(
                title = "Move back to Home",
                desc = "Start another Capsule",
                moveNext = {
                    navController.popBackStack()
                }
            )
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Header(onBackPressed: () -> Unit = {}, pagerState: PagerState) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            onBackPressed()
                        }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = AppViewModel.totalSteps[pagerState.currentPage]?.get("heading")
                            ?: "Heading",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                    )
                }
                Timer()
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "STEP 1", modifier = Modifier.padding(bottom = 5.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                if (pagerState.currentPage > 0) Color.Green else if (pagerState.currentPage == 0) Color(
                                    0xFFA56C4D
                                ) else Color.Gray
                            )
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "STEP 2", modifier = Modifier.padding(bottom = 5.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                if (pagerState.currentPage > 1) Color.Green else if (pagerState.currentPage == 1) Color(
                                    0xFFA56C4D
                                ) else Color.Gray
                            )
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "STEP 3", modifier = Modifier.padding(bottom = 5.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                if (pagerState.currentPage > 2) Color.Green else if (pagerState.currentPage == 2) Color(
                                    0xFFA56C4D
                                ) else Color.Gray
                            )
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "STEP 4", modifier = Modifier.padding(bottom = 5.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                if (pagerState.currentPage > 3) Color.Green else if (pagerState.currentPage == 4) Color(
                                    0xFFA56C4D
                                ) else Color.Gray
                            )
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                }


            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Timer() {
    var ticks by remember { mutableIntStateOf(AppViewModel.totalSeconds) }
    LaunchedEffect(Unit) {
        while (true) {
            if (ticks <= 0) {
                break
            }
            delay(1.seconds)
            ticks--
        }
    }


    val minutes = (ticks / 60)
    val seconds = (ticks % 60)

    Box(
        modifier = Modifier
            .padding(10.dp)
            .border(
                border = BorderStroke(width = 2.dp, color = Color(0xFF03A9F4)),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(5.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Icon(Icons.Outlined.DateRange, contentDescription = "Timer", tint = Color(0xFF03A9F4))
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "${minutes.toString().padStart(2, '0')}:${
                    seconds.toString().padStart(2, '0')
                } min"
            )
        }
    }
}

@Composable
fun NextButton(title: String, desc: String, moveNext: () -> Unit = {}) {
    Column {
        Text(
            text = "Up Next:",
            color = Color.Black,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 15.dp, bottom = 5.dp)
        )
        Box(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color(0xff44BAF8))
                .clickable {
                    moveNext()
                    Log.d("HEMAL", "Next button clicked")
                }
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .fillMaxWidth()

        ) {
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = Color.White,
                        style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.SemiBold),
                    )
                    Text(
                        text = desc,
                        color = Color(0xFFE4E4E4),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal),
                    )
                }
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(40.dp)
                )
            }
        }
    }

}




