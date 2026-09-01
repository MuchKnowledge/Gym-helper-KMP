package com.trulala.gymhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.retainedComponent
import com.trulala.gymhelper.shared.root.DefaultRootComponent
import com.trulala.gymhelper.shared.root.RootContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Строго после super.onCreate(): retainedComponent читает SavedStateRegistry,
        // а тот отдаёт данные только когда Activity уже в состоянии CREATED.
        // Компонент переживает пересоздание Activity — граф не пересобирается на повороте.
        val root = retainedComponent(factory = ::DefaultRootComponent)

        setContent {
            RootContent(component = root)
        }
    }
}
