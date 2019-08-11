package com.fantasy1022.pixabay.utilities

import android.content.Context


fun Float.convertDpToPixel(context: Context): Float = this * context.resources.displayMetrics.density



