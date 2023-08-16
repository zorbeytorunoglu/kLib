package com.zorbeytorunoglu.kLib.version

enum class VersionEnum {

    UNKNOWN,
    V1_8_R1,
    V1_8_R2,
    V1_8_R3,
    V1_9_R1,
    V1_9_R2,
    V1_10_R1,
    V1_11_R1,
    V1_12_R1,
    V1_13_R1,
    V1_13_R2,
    V1_14_R1,
    V1_15_R1,
    V1_16_R1,
    V1_16_R2,
    V1_16_R3,
    V1_17_R1,
    V1_18_R1,
    V1_18_R2,
    V1_19_R1,
    V1_19_R2,
    V1_20_R1;

    fun isPost(version: VersionEnum): Boolean = version.ordinal < ordinal

    fun isPre(version: VersionEnum): Boolean = version.ordinal > ordinal

    fun isPostOrEquals(version: VersionEnum): Boolean = isPost(version) || equals(version)

    fun isPreOrEquals(version: VersionEnum): Boolean = isPre(version) || equals(version)

}