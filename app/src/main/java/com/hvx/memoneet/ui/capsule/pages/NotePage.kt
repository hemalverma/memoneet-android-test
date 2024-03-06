import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NotePage() {
    val map = remember {
        mutableStateOf(
            mutableMapOf<Int, String>()
        )
    }


    val value = remember { mutableStateOf("") };
    Box(modifier = Modifier.fillMaxHeight()) {
        Column {
            Text(
                text = "Add Notes",
                style = TextStyle(
                    fontSize = 20.sp,
                    letterSpacing = 0.15.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(10.dp)
            )
            TextField(
                value = value.value,
                onValueChange = { value.value = it },
                label = { Text("Make Note") },
                maxLines = 4,
                minLines = 4,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )


            Text(
                text = "My Notes",
                style = TextStyle(
                    fontSize = 20.sp,
                    letterSpacing = 0.15.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(10.dp)
            )
            if (map.value.isEmpty()) {
                Text(
                    text = "No Notes",
                    style = TextStyle(
                        fontSize = 20.sp,
                        letterSpacing = 0.15.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.padding(10.dp)
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .verticalScroll(rememberScrollState ())
            ) {
                map.value.forEach { (key, value) ->
                    NoteListItem(id = key, note = value)
                }
            }

            Button(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(), onClick = {
                if (value.value.isNotEmpty()) {

                    map.value[map.value.size] = value.value
                    value.value = ""
                }
            }) {
                Text(text = "Add Note")

            }

        }
    }
}

@Composable
fun NoteListItem(id: Int, note: String) {
    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Top) {
        Text(
            text = (id + 1).toString(),
            style = TextStyle(
                fontSize = 20.sp,
                letterSpacing = 0.15.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = note,
            style = TextStyle(
                fontSize = 20.sp,
                letterSpacing = 0.15.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

}