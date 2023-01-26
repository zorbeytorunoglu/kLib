package com.zorbeytorunoglu.kLib.extensions

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
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

fun ItemStack.setLore(vararg lore: String) {

    val meta = (if (this.hasItemMeta()) this.itemMeta else Bukkit.getItemFactory().getItemMeta(this.type))

    val colored = ArrayList<String>()

    lore.forEach { colored.add(it.color) }

    meta.lore = colored

    this.itemMeta = meta

}

fun ItemStack.setLore(loreList: List<String>) {

    val meta = (if (this.hasItemMeta()) this.itemMeta else Bukkit.getItemFactory().getItemMeta(this.type))

    meta.lore = loreList

    this.itemMeta = meta

}

fun ItemStack.addLore(vararg lore: String?) {

    val meta = (if (this.hasItemMeta()) this.itemMeta else Bukkit.getItemFactory().getItemMeta(this.type))!!
    var loreList = meta.lore

    if (loreList == null) {
        loreList = lore.asList()
    } else {
        loreList.addAll(lore.asList())
    }
    meta.lore = loreList
    this.itemMeta = meta

}

fun ItemStack.addLore(lore: MutableList<String>) {

    val meta = (if (this.hasItemMeta()) this.itemMeta else Bukkit.getItemFactory().getItemMeta(this.type))!!
    var loreList = meta.lore

    if (loreList == null) loreList = lore else loreList.addAll(lore)

    meta.lore = loreList
    this.itemMeta = meta

}

fun ItemStack.setEnchantment(enchantment: Enchantment, level: Int) {
    val meta = (if (this.hasItemMeta()) this.itemMeta else Bukkit.getItemFactory().getItemMeta(this.type))!!
    meta.addEnchant(enchantment, level, true)
    this.itemMeta = meta
}

fun ItemStack.clearEnchantments() {
    val meta = (if (this.hasItemMeta()) this.itemMeta else Bukkit.getItemFactory().getItemMeta(this.type))!!
    for (enchantment in meta.enchants.keys) {
        meta.removeEnchant(enchantment)
    }
    this.itemMeta = meta
}

fun ItemStack.removeEnchantment(enchantment: Enchantment) {
    val meta = (if (this.hasItemMeta()) this.itemMeta else Bukkit.getItemFactory().getItemMeta(this.type))!!
    meta.removeEnchant(enchantment)
    this.itemMeta = meta
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