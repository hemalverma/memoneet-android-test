package com.hvx.memoneet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hvx.memoneet.routing.AppRouting
import com.hvx.memoneet.ui.capsule.CapsulePage
import com.hvx.memoneet.ui.home.Home
import com.hvx.memoneet.ui.theme.MemoNeetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun App() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRouting.Home.route) {
        //route 1
        composable(
            route = AppRouting.Home.route
        ) {
            Home(navController = navController)
        }
        //route 2
        composable(
            route = AppRouting.Capsule.route
        ) {
            CapsulePage(navController = navController)
        }

    }
}