package com.zorbeytorunoglu.kLib.extensions

/**
 * Returns a random integer between specified integers.
 * @param from From
 * @param to To
 * @return A random integer between two parameters.
 */
fun Int.random(from: Int, to:Int): Int = (from..to).random()