import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun ui() {
    ResultPage(total = 10, correct = 4, wrong = 4)
}

@Composable
fun ResultPage(total: Int, correct: Int, wrong: Int) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            "Result",

            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
            ),
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .weight(1f)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFF009688))
                    .padding(15.dp)
                    .padding(15.dp)
            ) {
                Text(
                    text = "Total Questions: $total",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Not Attempted: ${total - (correct + wrong)}",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color.Gray)
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                )
                Text(
                    text = "Coorect Answers: $wrong",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color.Green)
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                )
                Text(
                    text = "Wrong Answers: $wrong",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color.Red)
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                )


            }


        }


    }
}