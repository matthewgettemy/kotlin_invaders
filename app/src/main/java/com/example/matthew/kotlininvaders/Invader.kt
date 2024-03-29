package com.example.matthew.kotlininvaders

import android.content.Context
import android.graphics.Bitmap
import android.graphics.RectF
import java.util.*
import android.graphics.BitmapFactory

class InvaderA(context: Context, row: Int, column: Int, screenX: Int, screenY: Int) :
    Invader(context, row, column, screenX, screenY)
{
    // Initialize the bitmaps
    override var bitmap1 = BitmapFactory.decodeResource(
    context.resources,
    R.drawable.invader_1a)

    override var bitmap2 = BitmapFactory.decodeResource(
    context.resources,
    R.drawable.invader_1b)
    init {
        // stretch the first bitmap to a size
        // appropriate for the screen resolution
        bitmap1 = Bitmap.createScaledBitmap(
            bitmap1!!,
            (width.toInt()),
            (height.toInt()),
            false)

        // stretch the second bitmap as well
        bitmap2 = Bitmap.createScaledBitmap(
            bitmap2!!,
            (width.toInt()),
            (height.toInt()),
            false)

        numberOfInvaders ++
    }
}

class InvaderB(context: Context, row: Int, column: Int, screenX: Int, screenY: Int) :
    Invader(context, row, column, screenX, screenY)
{
    // Initialize the bitmaps
    override var bitmap1 = BitmapFactory.decodeResource(
    context.resources,
    R.drawable.invader_2a)

    override var bitmap2 = BitmapFactory.decodeResource(
    context.resources,
    R.drawable.invader_2b)
    init {
        // stretch the first bitmap to a size
        // appropriate for the screen resolution
        bitmap1 = Bitmap.createScaledBitmap(
            bitmap1!!,
            (width.toInt()),
            (height.toInt()),
            false)

        // stretch the second bitmap as well
        bitmap2 = Bitmap.createScaledBitmap(
            bitmap2!!,
            (width.toInt()),
            (height.toInt()),
            false)

        numberOfInvaders ++
    }
}

class InvaderC(context: Context, row: Int, column: Int, screenX: Int, screenY: Int) :
    Invader(context, row, column, screenX, screenY)
{
    // Initialize the bitmaps
    override var bitmap1 = BitmapFactory.decodeResource(
    context.resources,
    R.drawable.invader_3a)

    override var bitmap2 = BitmapFactory.decodeResource(
    context.resources,
    R.drawable.invader_3b)
    init {
        // stretch the first bitmap to a size
        // appropriate for the screen resolution
        bitmap1 = Bitmap.createScaledBitmap(
            bitmap1!!,
            (width.toInt()),
            (height.toInt()),
            false)

        // stretch the second bitmap as well
        bitmap2 = Bitmap.createScaledBitmap(
            bitmap2!!,
            (width.toInt()),
            (height.toInt()),
            false)

        numberOfInvaders ++
    }
}

open class Invader(context: Context, row: Int, column: Int, screenX: Int, screenY: Int) {
    // How wide, high and spaced out are the invader will be
    var width = screenX / 35f
    var height = screenY / 35f
    private val padding = screenX / 45

    var position = RectF(
        column * (width + padding),
        100 + row * (width + padding / 4),
        column * (width + padding) + width,
        100 + row * (width + padding / 4) + height
    )

    // This will hold the pixels per second speed that the invader will move
    private var speed = 40f

    private val left = 1
    private val right = 2

    // Is the ship moving and in which direction
    private var shipMoving = right

    var isVisible = true

    // The alien ship will be represented by a Bitmap
    open var bitmap1: Bitmap? = null
    open var bitmap2: Bitmap? = null

    companion object {




        // keep track of the number of instances
        // that are active
        var numberOfInvaders = 0
    }

    fun update(fps: Long) {
        if (shipMoving == left) {
            position.left -= speed / fps
        }

        if (shipMoving == right) {
            position.left += speed / fps
        }

        position.right = position.left + width
    }

    fun dropDownAndReverse(waveNumber: Int) {
        shipMoving = if (shipMoving == left) {
            right
        } else {
            left
        }

        position.top += height
        position.bottom += height

        // The later the wave, the more the invader speeds up
        speed *=  (1.1f + (waveNumber.toFloat() / 20))
    }

    fun takeAim(playerShipX: Float,
                playerShipLength: Float,
                waves: Int)
            : Boolean {

        val generator = Random()
        var randomNumber: Int

        // If near the player consider taking a shot
        if (playerShipX + playerShipLength > position.left &&
            playerShipX + playerShipLength < position.left + width ||
            playerShipX > position.left && playerShipX < position.left + width) {

            // The fewer invaders the more each invader shoots
            // The higher the wave the more the invader shoots
            randomNumber = generator.nextInt(100 * numberOfInvaders) / waves
            if (randomNumber == 0) {
                return true
            }

        }

        // If firing randomly (not near the player)
        randomNumber = generator.nextInt(150 * numberOfInvaders)
        return randomNumber == 0

    }
}