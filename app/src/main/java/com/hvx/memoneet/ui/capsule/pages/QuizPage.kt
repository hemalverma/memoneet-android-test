import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hvx.memoneet.ui.capsule.models.QuestionDataModel
import com.hvx.memoneet.ui.capsule.viewmodel.AppViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizPage(onRightAnswer: () -> Unit = {}, onWrongAnswer: () -> Unit = {}) {
    val questions = AppViewModel.Questions;
    val pagerState = rememberPagerState(pageCount = {
        questions.size
    })
    Box(contentAlignment = Alignment.Center) {
        Column {
            QuestionCount(
                totalQuestion = questions.size,
                currentQuestion = pagerState.currentPage + 1
            )
            QuestionPageView(pagerState = pagerState, onRightAnswer = onRightAnswer, onWrongAnswer = onWrongAnswer)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuestionPageView(pagerState: PagerState, onRightAnswer: () -> Unit = {}, onWrongAnswer: () -> Unit = {}) {

    val questions = AppViewModel.Questions

    val coroutineScope = rememberCoroutineScope()

    val moveToNext: () -> Unit = {
        coroutineScope.launch {
            if (pagerState.currentPage == questions.size - 1) {
                //pagerState.animateScrollToPage(0)
                return@launch
            }
            pagerState.animateScrollToPage(pagerState.currentPage + 1)

        }
    }

    HorizontalPager(
        userScrollEnabled = false,
        state = pagerState,
    ) { page ->
        // Our page content
        SingleQuestion(questions[page], onNextClick = moveToNext, onRightAnswer = onRightAnswer, onWrongAnswer = onWrongAnswer)
    }

}


@Composable
fun QuestionCount(totalQuestion: Int, currentQuestion: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color(0xFF03A9F4))
                .clickable {
                }
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .weight(1f)
        ) {
            Row {
                Text(
                    "QUESTION $currentQuestion/$totalQuestion",

                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    ),
                    modifier = Modifier
                        .weight(1f)
                )

            }
        }
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color(0xFF03A9F4))
                .padding(10.dp)

        ) {
            Icon(
                Icons.Outlined.Star, contentDescription = "Correct",
                tint = Color.White,

                )
        }
    }

}


@Composable
fun SingleQuestion(question: QuestionDataModel, onNextClick: () -> Unit = {}, onRightAnswer: () -> Unit = {}, onWrongAnswer: () -> Unit = {}) {

    val selected = remember { mutableStateOf(0) }
    val showAnswer = remember { mutableStateOf(false) }
    val correct = remember { mutableStateOf(question.correct) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xffffffff))
                        .padding(10.dp)
                        .border(2.dp, Color(0xff000000), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row {
                        Text(
                            "Q1:", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )
                        Text(
                            question.question,
                            modifier = Modifier.weight(1f),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                }
                var show = false;

                for (index in question.options.indices) {

                    if (showAnswer.value) {
                        show = selected.value == index + 1 || index + 1 == correct.value;
                    } else {
                        show = selected.value == index + 1;
                    }

                    Option(
                        question.options[index],
                        isSelected = show,
                        isCorrect = index + 1 == correct.value,
                        onClick = {
                            if (selected.value == 0) {
                                selected.value = index + 1
                                if (index + 1 == correct.value) {
                                    onRightAnswer()
                                } else {
                                    onWrongAnswer()
                                }
                            }
                        })
                }

            }
            CheckAnswer(
                onClick = {
                    if (showAnswer.value) {
                        showAnswer.value = false
                        return@CheckAnswer
                    }
                    if (selected.value != 0) {
                        showAnswer.value = true
                    }

                },
                onNextClick = onNextClick,
            )
        }
    }
}

@Composable
fun Option(option: String, isSelected: Boolean, isCorrect: Boolean, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
            .background(
                if (isSelected) if (isCorrect) Color.Green else Color.Red else Color(
                    0xffFEFDC4
                )
            )
            .clickable {
                onClick()
            }
            .clip(shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 10.dp)

            .fillMaxWidth()
    ) {
        Row {
            Text(
                option,

                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .weight(1f)
            )
            if (isSelected) {
                if (isCorrect) {
                    Icon(
                        Icons.Filled.Check, contentDescription = "Correct",
                        tint = Color.White,

                        )
                } else {
                    Icon(
                        Icons.Filled.Close, contentDescription = "Correct",
                        tint = Color.White,

                        )
                }
            }
        }
    }
}

@Composable
fun CheckAnswer(onClick: () -> Unit, onNextClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color(0xff7589A4))
                .clickable {
                    onClick()
                }
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .weight(1f)
        ) {
            Row {
                Text(
                    "CHECK ANSWER",

                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    ),
                    modifier = Modifier
                        .weight(1f)
                )

            }
        }
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color(0xFF03A9F4))
                .clickable {
                    onNextClick()

                }
                .padding(10.dp)

        ) {
            Icon(
                Icons.Filled.KeyboardArrowRight, contentDescription = "Correct",
                tint = Color.White,

                )
        }
    }

}