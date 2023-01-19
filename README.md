# kLib for Kotlin Spigot Devs

[![](https://jitpack.io/v/zorbeytorunoglu/kLib.svg)](https://jitpack.io/#zorbeytorunoglu/kLib)

kLib is an API for Kotlin developers to make their coding experiences in Spigot much more easier.

As you are aware of benefits of Kotlin over Java, especially considering performance utilities, there is no reason to not to choose Kotlin. 

__[Short YouTube video for "kLib" task chain system example](https://www.youtube.com/watch?v=_nng2xyOW-g)__

Documentation will be prepared after further adjustments.

__If you need anything or have any questions, please don't hesitate to open an issue or contact me via Discord: >>TheLegend<<#6052__

### Important Note:
Kotlin "stdlib" and Coroutines are already implemented. So, if you have "kLib" in your "plugins" folder and if you specified "kLib" as dependency in your "plugin.yml", you won't need to implement kotlin-stdlib and coroutines, you only need to compile them. Your plugin's size can be much smaller since you don't need to compile the runtime and coroutines. You can use "kLib" as your Kotlin runtime provider for your other Kotlin plugins too.

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
    <repository>
        <id>spigot-repo</id>
        <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.zorbeytorunoglu</groupId>
        <artifactId>kLib</artifactId>
        <version>{LATEST_VERSION_HERE}</version>
        <scope>provided</scope>
    </dependency>

    <!-- Coroutine is needed for suspending functions and already implemented in "kLib", scope can be "provided" -->
    <dependency>
        <groupId>org.jetbrains.kotlinx</groupId>
        <artifactId>kotlinx-coroutines-core</artifactId>
        <version>1.6.4</version>
        <type>pom</type>
        <scope>provided</scope>
    </dependency>

    <!-- "kotlin-stdlib" also already implemented in "kLib", can be scoped as "provided" -->
    <dependency>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-stdlib</artifactId>
        <version>1.8.0</version>
        <scope>provided</scope>
    </dependency>

    <!-- SpigotAPI -->
    <dependency>
        <groupId>org.spigotmc</groupId>
        <artifactId>spigot-api</artifactId>
        <version>1.8.8-R0.1-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Gradle

```groovy
repositories {
    
    maven {
        url = 'https://hub.spigotmc.org/nexus/content/repositories/snapshots/'

        // As of Gradle 5.1, you can limit this to only those
        // dependencies you expect from it
        content {
            includeGroup 'org.bukkit'
            includeGroup 'org.spigotmc'
        }
    }

    maven { url 'https://jitpack.io'}
    maven { url = 'https://oss.sonatype.org/content/repositories/snapshots' }
    maven { url = 'https://oss.sonatype.org/content/repositories/central' }

}
dependencies {
    
    compileOnly 'com.github.zorbeytorunoglu:kLib:[LATEST_VERSION_HERE]'

    // "kotlin-stdlib" also already implemented in "kLib", can be compiled only
    compileOnly 'org.jetbrains.kotlin:kotlin-stdlib:1.8.0'

    // Coroutine is needed for suspending functions and already implemented in "kLib", can be compiled only
    compileOnly 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'

    // Spigot API
    compileOnly 'org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT'
    
}
```

### Gradle .kts

```groovy
repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    /*
     As Spigot-API depends on the BungeeCord ChatComponent-API,
    we need to add the Sonatype OSS repository, as Gradle,
    in comparison to maven, doesn't want to understand the ~/.m2
    directory unless added using mavenLocal(). Maven usually just gets
    it from there, as most people have run the BuildTools at least once.
    This is therefore not needed if you're using the full Spigot/CraftBukkit,
    or if you're using the Bukkit API.
    */
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
}

dependencies {
    compileOnly("com.github.zorbeytorunoglu:kLib:[LATEST_VERSION_HERE]]")
    
    // Coroutine is needed for suspending functions and already implemented in "kLib", can be compiled only
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    
    // "kotlin-stdlib" also already implemented in "kLib", can be compiled only
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
    
    // SpigotAPI
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
}
```

__Don't forget to add "kLib" as a dependency in your "plugin.yml"__
```yaml
depend: [kLib]
```

Discord Server: https://discord.com/invite/dp3R6A9