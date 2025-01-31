package com.ctw.ctwpokedex.data.models.converter

import androidx.room.TypeConverter
import com.ctw.ctwpokedex.data.models.DreamWorld
import com.ctw.ctwpokedex.data.models.Other
import com.ctw.ctwpokedex.data.models.Sprites

class SpritesConverter {
    @TypeConverter
    fun fromString(value: String?): Sprites? {
        return value?.let {
            Sprites(
                other = Other(
                    DreamWorld(
                        front_default = it
                    )
                )
            )
        }
    }

    @TypeConverter
    fun toString(sprites: Sprites?): String {
        return sprites.toString()
    }
}