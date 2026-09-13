package com.marshall.core.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

expect fun platform(): String

@Composable
fun HelloWorld() {
    Scaffold {
        println("Hello, ${platform()}")
    }
}
