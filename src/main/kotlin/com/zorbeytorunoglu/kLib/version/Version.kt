package com.zorbeytorunoglu.kLib.version

import org.bukkit.Bukkit

object Version {

    private var nmsVersion: VersionEnum = VersionEnum.UNKNOWN

    fun getVersion(): VersionEnum {

        if (nmsVersion == VersionEnum.UNKNOWN) {

            val versionName = Bukkit.getServer().javaClass.getPackage().name
                .replace(".", ",").split(",")[3].uppercase()

            nmsVersion = try {
                VersionEnum.valueOf(versionName)
            } catch (ex: IllegalArgumentException) {
                VersionEnum.UNKNOWN
            }

        }

        return nmsVersion

    }

}