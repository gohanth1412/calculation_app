package com.example.calculatorapp

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val dataButton = listOf(
        "C",
        "%",
        "X",
        "+",
        "7",
        "8",
        "9",
        "-",
        "4",
        "5",
        "6",
        "x",
        "1",
        "2",
        "3",
        "/",
        "a",
        "0",
        ".",
        "="
    )
    val dataInput =
        listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-", "x", "/")
}