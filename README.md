# kLib for Kotlin Spigot Devs

[![](https://jitpack.io/v/zorbeytorunoglu/kLib.svg)](https://jitpack.io/#zorbeytorunoglu/kLib)

kLib is an API for Kotlin developers to make their coding experiences in Spigot much more easier.

As you are aware of benefits of Kotlin over Java, especially considering performance utilities, there is no reason to not to choose Kotlin.

NOTE: If you want to use the suspending functions, you need __"KotlinX Coroutines"__ as well.

__[Short YouTube video for "kLib" task chain system example](https://www.youtube.com/watch?v=_nng2xyOW-g)__

Documentation will be prepared after further adjustments.

__If you need anything or have any questions, please don't hesitate to open an issue or contact me via Discord: >>TheLegend<<#6052__

### Important Note:
Kotlin "stdlib" and Coroutines are already implemented. So, if you have "kLib" in your "plugins" folder and if you add it as dependency in your "plugin.yml", you won't need to implement kotlin-stdlib and coroutines, you only need to compile them. Your plugin's size can be much smaller since you don't need to compile the runtime.

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.zorbeytorunoglu</groupId>
        <artifactId>kLib</artifactId>
        <version>{LATEST_VERSION_HERE}</version>
    </dependency>
</dependencies>
```

### Gradle

```groovy
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
dependencies {
    implementation 'com.github.zorbeytorunoglu:kLib:[LATEST_VERSION_HERE]'
}
```

### Gradle .kts

```groovy
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.zorbeytorunoglu:kLib:[LATEST_VERSION_HERE]]")
}
```