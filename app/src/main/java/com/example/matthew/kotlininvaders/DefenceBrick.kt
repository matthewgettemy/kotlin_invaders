package com.example.matthew.kotlininvaders

import android.graphics.RectF


class DefenceBrick(row: Int, column: Int, shelterNumber: Int, screenX: Int, screenY: Int) {

    var isVisible = true

    // private val width = screenX / 120
    private val width = 16
    // private val height = screenY / 50
    private val height = 16


    // Sometimes a bullet slips through this padding
    // Set padding to zero if this annoys you
    private val brickPadding = 0

    // The number of shelters
    private val shelterPadding = screenX / 12f
    private val startHeight = screenY - screenY / 10f * 2f

    val position = RectF(column * width + brickPadding +
            shelterPadding * shelterNumber +
            shelterPadding + shelterPadding * shelterNumber,
        row * height + brickPadding + startHeight,
        column * width + width - brickPadding +
                shelterPadding * shelterNumber +
                shelterPadding + shelterPadding * shelterNumber,
        row * height + height - brickPadding + startHeight)
}