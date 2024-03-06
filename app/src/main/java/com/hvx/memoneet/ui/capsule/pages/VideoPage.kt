package com.hvx.memoneet.ui.capsule.pages

import android.media.browse.MediaBrowser
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_OFF
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Preview(showSystemUi = true)
@Composable
fun VideoPage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        VideoPlayerView()
    }
}


@Composable
fun VideoPlayerView() {
    val context = LocalContext.current
    val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    //build source
    val mediaItem = remember(url) {
        MediaItem.fromUri(url)

    }
    //build player
    val exoPlayer =
        ExoPlayer.Builder(context)
            .build()


    LaunchedEffect(exoPlayer) {
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true;
    }
    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }

    }
    AndroidView(factory = { ctx ->
        PlayerView(ctx).apply {
            player = exoPlayer
        }
    }, modifier = Modifier
        .height(300.dp)
        .background(Color.Black))
}