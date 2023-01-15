package com.zorbeytorunoglu.kLib.extensions

import com.zorbeytorunoglu.kLib.MCPlugin
import com.zorbeytorunoglu.kLib.task.MCDispatcher
import com.zorbeytorunoglu.kLib.task.Scopes
import kotlinx.coroutines.launch
import org.bukkit.Effect
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

fun Location.spawnEntity(entityType: EntityType) {
    this.world.spawnEntity(this, entityType)
}

fun Location.dropItem(item: ItemStack) {
    world.dropItem(this, item)
}

fun Location.dropItemNaturally(item: ItemStack) {
    world.dropItemNaturally(this, item)
}

fun Location.strikeLightning() {
    world.strikeLightning(this)
}

fun Location.strikeLightningEffect() {
    world.strikeLightningEffect(this)
}

fun Location.createExplosion(power: Float) {
    world.createExplosion(this, power)
}

fun Location.createExplosion(power: Float, fire: Boolean) {
    world.createExplosion(this, power, fire)
}

fun Location.createExplosion(power: Float, fire: Boolean, breakBlocks: Boolean) {
    world.createExplosion(this.x, this.y, this.z, power, fire, breakBlocks)
}

suspend fun Location.createExplosionAsync(power: Float, fire: Boolean, breakBlocks: Boolean) {
    Scopes.supervisorScope.launch { world.createExplosion(this@createExplosionAsync.x, this@createExplosionAsync.y, this@createExplosionAsync.z, power, fire, breakBlocks) }.join()
}

fun Location.playEffect(effect: Effect, data: Int) {
    world.playEffect(this, effect, data)
}

fun Location.playEffect(effect: Effect, data: Int, radius: Int) {
    world.playEffect(this, effect, data, radius)
}

fun <T> Location.playEffect(effect: Effect, data: T) {
    world.playEffect(this, effect, data)
}

fun <T> Location.playEffect(effect: Effect, data: T, radius: Int) {
    world.playEffect(this, effect, data, radius)
}

suspend fun Location.spawnEntityAsync(mcPlugin: MCPlugin, entityType: EntityType) {

    Scopes.supervisorScope.launch(MCDispatcher(mcPlugin, async = false)) {
        world.spawnEntity(this@spawnEntityAsync, entityType)
    }.join()

}