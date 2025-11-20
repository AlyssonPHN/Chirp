package com.marshall.chirp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform