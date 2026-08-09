package com.marshall.chirp.convention

import org.gradle.api.Project

fun Project.pathToPackageName(): String {
    val relativePackageName = path
        .replace(":", ".")
        .lowercase()
    return "com.marshall$relativePackageName"
}

fun Project.pathToResourcePrefix(): String {
    val relativePackageName = path
        .replace(":", "_")
        .lowercase()
        .drop(1) + "_"
    return "com.marshall$relativePackageName"
}