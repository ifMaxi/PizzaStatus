package com.maxidev.pizzastatus.ui.presentation.components

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.pizzastatus.ui.theme.soraFont

@Composable
fun StatusComponent(
    modifier: Modifier = Modifier,
    @StringRes message: Int,
    @RawRes rawRes: Int
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LottieComponent(
            rawRes = rawRes,
            modifier = Modifier
                .size(400.dp)
        )
        Text(
            text = stringResource(id = message),
            fontFamily = soraFont,
            fontSize = 25.sp
        )
    }
}