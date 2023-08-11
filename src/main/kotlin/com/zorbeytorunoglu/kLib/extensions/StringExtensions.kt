package com.zorbeytorunoglu.kLib.extensions

import org.apache.maven.artifact.versioning.ComparableVersion
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import java.security.MessageDigest
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Translates alternate color codes from '&', which is used very common, to color the string.
 * @return String
 */
val String.color get(): String {
    return ChatColor.translateAlternateColorCodes('&', this)
}

/**
 * Translates alternate color codes from '&', which is used very common, to color the string.
 * Supports RGB color codes as well.
 * @return String
 */
val String.colorHex get(): String {
    var message = this
    val pattern = Pattern.compile("#[a-fA-F0-9]{6}")
    var matcher = pattern.matcher(message)
    while (matcher.find()) {
        val hexCode = message.substring(matcher.start(), matcher.end())
        val replaceSharp = hexCode.replace('#', 'x')
        val ch = replaceSharp.toCharArray()
        val builder = StringBuilder("")
        for (c in ch) {
            builder.append("&$c")
        }
        message = message.replace(hexCode, builder.toString())
        matcher = pattern.matcher(message)
    }
    return ChatColor.translateAlternateColorCodes('&', message)
}

/**
 * Translates alternate color codes by the given char, to color the string.
 * Supports RGB color codes as well.
 * @return String
 */
fun String.color(colorCode: Char): String {
    return ChatColor.translateAlternateColorCodes(colorCode, this)
}

/**
 * Filters only the letters from the string and returns it.
 * @return Filtered letters, String
 */
val String.letters get(): String {
    return this.filter { it.isLetter() }
}

/**
 * Filters all the numbers from the given string and returns the result as Int.
 * @return Numbers in the String, Int
 */
val String.numbers get(): Int {
    return this.filter { it.isDigit() }.toInt()
}

/**
 * Filters all the numbers from the given string and returns as Double.
 * @return Numbers in the String, Double
 */
val String.numbersDouble get(): Double {
    return this.filter { it.isDigit() }.toDouble()
}

/**
 * MD5 Hash Calculator
 * @return String
 */
val String.md5: String
    get() {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        return bytes.joinToString("") {
            "%02x".format(it)
        }
    }

/**
 * SHA1 Hash Calculator
 * @return String
 */
val String.sha1: String
    get() {
        val bytes = MessageDigest.getInstance("SHA-1").digest(this.toByteArray())
        return bytes.joinToString("") {
            "%02x".format(it)
        }
    }

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

/**
 * Checks if the given String contains latin letters.
 * @return Boolean
 */
val String.containsLatinLetter: Boolean
    get() = matches(Regex(".*[A-Za-z].*"))

/**
 * Checks if the given String contains numbers.
 * @return Boolean
 */
val String.containsNumbers: Boolean
    get() = matches(Regex(".*[0-9].*"))

/**
 * Checks if the given String is alphanumeric.
 * @return Boolean
 */
val String.isAlphanumeric: Boolean
    get() = matches(Regex("[A-Za-z0-9]*"))

/**
 * Checks if the given String has letters and digits.
 * @return Boolean
 */
val String.hasLettersAndDigits: Boolean
    get() = containsLatinLetter && containsNumbers

/**
 * Checks if the given String is an integer number.
 * @return Boolean, returns null if it can't be cast.
 */
val String.isIntegerNumber: Boolean
    get() = toIntOrNull() != null

/**
 * Checks if the string can be cast to decimal number. Returns null if it can't be cast.
 * @return Boolean
 */
val String.isDecimalNumber: Boolean
    get() = toDoubleOrNull() != null

/**
 * Gives the formed date with the given format.
 * @return Date formed with the given format
 */
fun String.dateInFormat(format: String): Date? {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    var parsedDate: Date? = null
    try {
        parsedDate = dateFormat.parse(this)
    } catch (ignored: ParseException) {
        ignored.printStackTrace()
    }
    return parsedDate
}

/**
 * Compares the version with the given String in version format.
 * The result of the comparison will be greater than or less than
 * 0 when one version is greater than or less than the other.
 * @return Int
 */
fun String.compareVersion(version: String): Int = ComparableVersion(this).compareTo(ComparableVersion(version))

/**
 * Checks if the String is greater than the given version String.
 * @return Boolean
 */
fun String.isVersionGreaterThan(version: String): Boolean = (ComparableVersion(this) > ComparableVersion(version))

/**
 * Checks if the String is lesser than the given version String.
 * @return Boolean
 */
fun String.isVersionLessThan(version: String): Boolean = (ComparableVersion(this) < ComparableVersion(version))

fun String.fromLegibleString(string: String): Location {

    val args: List<String> = string.split(";")

    return Location(
        Bukkit.getWorld(args[0]),args[1].toDouble(),args[2].toDouble(),args[3].toDouble(),
        args[4].toFloat(), args[5].toFloat())

}