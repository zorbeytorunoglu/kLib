package com.zorbeytorunoglu.kLib.extensions

import com.zorbeytorunoglu.kLib.MCPlugin
import com.zorbeytorunoglu.kLib.task.MCDispatcher
import com.zorbeytorunoglu.kLib.task.Scopes
import kotlinx.coroutines.launch
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*


fun Player.isInside(playerLocation: Location, location1: Location, location2: Location): Boolean {

    val x: Int = playerLocation.blockX
    val y: Int = playerLocation.blockY
    val z: Int = playerLocation.blockZ
    val x1: Int = location1.blockX.coerceAtMost(location2.blockX)
    val y1: Int = location1.blockY.coerceAtMost(location2.blockY)
    val z1: Int = location1.blockZ.coerceAtMost(location2.blockZ)
    val x2: Int = location1.blockX.coerceAtLeast(location2.blockX)
    val y2: Int = location1.blockY.coerceAtLeast(location2.blockY)
    val z2: Int = location1.blockZ.coerceAtLeast(location2.blockZ)

    return x in x1..x2 && y >= y1 && y <= y2 && z >= z1 && z <= z2

}

fun Player.isInventoryFull(): Boolean {
    return this.inventory.firstEmpty() == -1;
}

fun Player.clearArmor() {
    player.inventory.armorContents = arrayOf<ItemStack?>(null, null, null, null)
}

fun Player.clearAllInventory() {
    inventory.clear()
    clearArmor()
}

val Player.hasItemInHand get() = itemInHand != null && itemInHand.type != Material.AIR

fun Player.playSound(sound: Sound, volume: Float, pitch: Float) = playSound(location, sound, volume, pitch)

fun <T> Player.playEffect(effect: Effect, data: T? = null) = playEffect(player.location, effect, data)

fun Player.resetWalkSpeed() {
    player.walkSpeed = 0.2f
}

fun Player.resetFlySpeed() {
    player.flySpeed = 0.1f
}

suspend fun Player.teleportAsync(mcPlugin: MCPlugin, location: Location) {
    Scopes.supervisorScope.launch(MCDispatcher(mcPlugin, async = false)) {

        this@teleportAsync.teleport(location)

    }.join()
}

suspend fun Player.teleportAsync(mcPlugin: MCPlugin, targetPlayer: Player) {
    Scopes.supervisorScope.launch(MCDispatcher(mcPlugin, async = false)) {

        this@teleportAsync.teleport(targetPlayer.location)

    }.join()
}

suspend fun Entity.teleportAsync(mcPlugin: MCPlugin, location: Location) {
    Scopes.supervisorScope.launch(MCDispatcher(mcPlugin, async = false)) {

        this@teleportAsync.teleport(location)

    }.join()
}

suspend fun Entity.teleportAsync(mcPlugin: MCPlugin, entity: Entity) {

    Scopes.supervisorScope.launch(MCDispatcher(mcPlugin, async = false)) {

        this@teleportAsync.teleport(entity.location)

    }.join()

}

fun Player.ban(reason: String) {
    this.kickPlayer(reason)
    Bukkit.getBanList(BanList.Type.NAME).addBan(this.name, reason, null, null)
}

fun Player.ban(reason: String, source: String) {
    this.kickPlayer(reason)
    Bukkit.getBanList(BanList.Type.NAME).addBan(this.name, reason, null, source)
}

fun Player.ban(reason: String, source: String, expiration: Date) {
    this.kickPlayer(reason)
    Bukkit.getBanList(BanList.Type.NAME).addBan(this.name, reason, expiration, source)
}

fun Player.ban(reason: String?, expiration: Date) {
    this.kickPlayer(reason)
    Bukkit.getBanList(BanList.Type.NAME).addBan(this.name, reason, expiration, null)
}

fun Player.banIP(reason: String?) {
    this.kickPlayer(reason)
    if (this.address != null) {
        Bukkit.getBanList(BanList.Type.IP).addBan(this.address.address.hostAddress, reason, null, null)
    }
}

fun Player.banIP(reason: String?, source: String?) {
    this.kickPlayer(reason)
    if (this.address != null) {
        Bukkit.getBanList(BanList.Type.IP).addBan(this.address.address.hostAddress, reason, null, source)
    }
}

fun Player.banIP(reason: String?, source: String?, expiration: Date?) {
    this.kickPlayer(reason)
    if (this.address != null) {
        Bukkit.getBanList(BanList.Type.IP).addBan(this.address.address.hostAddress, reason, expiration, source)
    }
}

fun Player.heal() {
    this.health = 20.0
    this.foodLevel = 20
}

fun Player.feed() {
    this.foodLevel = 20
}



fun Player.sendMessage(messageList: Collection<String>) = messageList.forEach { this.sendMessage(it) }

fun CommandSender.sendMessage(messageList: Collection<String>) = messageList.forEach { this.sendMessage(it) }

fun Player.getBlockBelow(): Block = this.location.subtract(0.0, 1.0, 0.0).block

fun Player.kill() {
    player.health = 0.0
}