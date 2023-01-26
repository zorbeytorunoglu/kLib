package com.zorbeytorunoglu.kLib.extensions

import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerMoveEvent

/**
 * Checks if the player displaced from its previous location.
 * @return True if yes, false if no.
 */
val PlayerMoveEvent.displaced: Boolean
    get() = this.from.x != this.to?.x || this.from.y != this.to?.y || this.from.z != this.to?.z

/**
 * Checks if the player's action is a left click air or block.
 * @return True if it is a left to a block or air, false if otherwise.
 */
val Action.isLeftClick get() = this == Action.LEFT_CLICK_BLOCK || this == Action.LEFT_CLICK_AIR

/**
 * Checks if the player's action is a right click air or block.
 * @return True if it is a right to a block or air, false if otherwise.
 */
val Action.isRightClick get() = this == Action.RIGHT_CLICK_BLOCK || this == Action.RIGHT_CLICK_AIR