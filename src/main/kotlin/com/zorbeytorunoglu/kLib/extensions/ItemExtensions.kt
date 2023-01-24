package com.zorbeytorunoglu.kLib.extensions

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

var ItemMeta.loreString: String?
    get() = lore?.joinToString("\n")
    set(value) {
        lore = value?.split("\n") }

var ItemMeta.name: String?
    get() = if (hasDisplayName()) displayName else null
    set(value) { displayName = if (!value.isNullOrEmpty()) value else " " }

fun ItemStack.removeAmount(player: Player, amount: Int) {

    if (this.amount - amount <= 0) {
        if (player.inventory.itemInHand.equals(this)) {
            player.inventory.itemInHand = null
        } else {
            player.inventory.removeItem(this)
        }
        return
    }

    this.amount = this.amount-amount
    player.updateInventory()

}

val Material.isPickaxe: Boolean get() = name.endsWith("_PICKAXE")
val Material.isSword: Boolean get() = name.endsWith("_SWORD")
val Material.isAxe: Boolean get() = name.endsWith("_AXE")
val Material.isSpade: Boolean get() = name.endsWith("_SPADE")
val Material.isHoe: Boolean get() = name.endsWith("_HOE")
val Material.isHelmet: Boolean get() = name.endsWith("_HELMET")
val Material.isChestPlate: Boolean get() = name.endsWith("_CHESTPLATE")
val Material.isLeggings: Boolean get() = name.endsWith("_LEGGINGS")
val Material.isBoots: Boolean get() = name.endsWith("_BOOTS")
val Material.isOre: Boolean get() = name.endsWith("_ORE")
val Material.isIngot: Boolean get() = name.endsWith("_INGOT")
val Material.isDoor: Boolean get() = name.endsWith("_DOOR")
val Material.isMineCart: Boolean get() = name.endsWith("_MINECART")