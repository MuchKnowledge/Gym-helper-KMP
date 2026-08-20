package com.trulala.gymhelper.shared.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.trulala.gymhelper.core.designsystem.theme.GymHelperTheme

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    GymHelperTheme {
        Surface(modifier = modifier.fillMaxSize()) {
            Children(
                stack = component.stack,
                animation = stackAnimation(fade()),
            ) { child ->
                when (child.instance) {
                    RootComponent.Child.Placeholder -> PlaceholderScreen()
                }
            }
        }
    }
}

@Composable
private fun PlaceholderScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Gym Helper KMP",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
