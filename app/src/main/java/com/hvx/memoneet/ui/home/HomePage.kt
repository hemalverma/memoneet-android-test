package com.hvx.memoneet.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hvx.memoneet.routing.AppRouting
import com.hvx.memoneet.ui.theme.MemoNeetTheme

@Composable
fun Home(navController: NavController) {

    MemoNeetTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome to MemoNeet ", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "Start the challenge to get some extra income",
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(40.dp))
                Box(contentAlignment = Alignment.Center) {
                    Button(onClick = {
                        navController.navigate(AppRouting.Capsule.route)
                    }) {
                        Text("Start Challenge")

                    }
                }
            }
        }
    }


}