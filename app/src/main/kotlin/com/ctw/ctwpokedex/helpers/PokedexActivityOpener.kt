/**
 * Copyright (c), BMW Critical TechWorks. All rights reserved.
 */

package com.ctw.ctwpokedex.helpers

import android.content.Context
import android.content.Intent
import com.ctw.ctwpokedex.presentation.activities.PokedexActivity
import timber.log.Timber

fun Context.openPokedexActivity() {
    runCatching {
        startActivity(
            Intent(this, PokedexActivity::class.java)
        )
    }.onFailure {
        Timber.e(
            "Something went wrong sending the intent to Pokedex Activity, exception: $it"
        )
    }
}
