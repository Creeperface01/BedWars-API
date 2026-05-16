package com.creeperface.nukkit.bedwars.api.economy

import com.hypixel.hytale.server.core.universe.PlayerRef
import java.util.*
import java.util.concurrent.CompletableFuture

interface EconomyProvider {

    val defaultCurrency: Currency

    fun subtractMoney(
        playerRef: PlayerRef,
        amount: Double,
        currency: Currency = defaultCurrency
    ): CompletableFuture<Void> = addMoney(playerRef.uuid, -amount, currency)

    fun subtractMoney(
        player: UUID,
        amount: Double,
        currency: Currency = defaultCurrency
    ): CompletableFuture<Void> = addMoney(player, -amount, currency)

    fun addMoney(
        playerRef: PlayerRef,
        amount: Double,
        currency: Currency = defaultCurrency
    ): CompletableFuture<Void> = addMoney(playerRef.uuid, amount, currency)

    fun addMoney(player: UUID, amount: Double, currency: Currency = defaultCurrency): CompletableFuture<Void>

    fun getMoney(playerRef: PlayerRef, currency: Currency = defaultCurrency): CompletableFuture<Double> =
        getMoney(playerRef.username, currency)

    fun getMoney(player: String, currency: Currency = defaultCurrency): CompletableFuture<Double>

    fun transferMoney(
        from: String,
        to: String,
        amount: Double,
        currency: Currency = defaultCurrency
    ): CompletableFuture<Boolean>

    fun getCurrency(name: String): Currency?

    interface Currency

    object NullCurrency : Currency
}
