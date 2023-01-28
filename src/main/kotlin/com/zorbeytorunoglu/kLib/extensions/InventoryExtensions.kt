package com.zorbeytorunoglu.kLib.extensions

import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

/**
 * Creates an Inventory.
 * @param type InventoryType
 * @param owner InventoryHolder
 * @param title Title String
 * @return Inventory
 */
fun Inventory(type: InventoryType, owner: InventoryHolder? = null, title: String? = null): Inventory {
    return if (title != null)
        Bukkit.getServer().createInventory(owner, type, title)
    else
        Bukkit.getServer().createInventory(owner, type)
}

/**
 * Creates a limited sized Inventory.
 * @param size Size, Int
 * @param owner InventoryHolder
 * @param title Title, String
 * @return Inventory
 */
fun Inventory(size: Int, owner: InventoryHolder? = null, title: String? = null): Inventory {
    return if (title != null)
        Bukkit.getServer().createInventory(owner, size, title)
    else
        Bukkit.getServer().createInventory(owner, size)
}

/**
 * Checks if there is any available space for the given ItemStack.
 * @param itemStack ItemStack
 * @param amount Amount, Int
 * @return Boolean
 */
fun Inventory.hasSpace(itemStack: ItemStack, amount: Int = itemStack.amount): Boolean {
    return getSpaceOf(itemStack) >= amount
}

/**
 * Gets the number of the available space of the given ItemStack.
 * @param itemStack ItemStack
 * @return Int
 */
fun Inventory.getSpaceOf(itemStack: ItemStack): Int {
    return contents.filterNotNull().map {
        if (it.amount < it.maxStackSize && it.isSimilar(itemStack))
            it.maxStackSize - it.amount
        else 0
    }.count()
}

/**
 * Clones the Inventory.
 * @param cloneItemStacks Boolean
 * @param owner InventoryHolder
 * @param title Title
 * @return Inventory
 */
fun Inventory.clone(cloneItemStacks: Boolean = true, owner: InventoryHolder? = holder, title: String? = null): Inventory {
    val inventory = if (type == InventoryType.CHEST)
        Inventory(size, owner, title)
    else
        Inventory(type, owner, title)

    inventory.contents = if (cloneItemStacks)
        contents.map { it.clone() }.toTypedArray()
    else
        contents

    return inventory
}