package com.zorbeytorunoglu.kLib.database

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.util.function.Consumer

class SQL(val host: String, val port: String, val database:String, val username: String, val password: String) {

    var connection: Connection? = null

    init {

        try {
            Class.forName("com.mysql.jdbc.Driver")
            connection = DriverManager.getConnection("jdbc:mysql://$host:$port/$database", username, password)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    fun isConnected(): Boolean = connection != null

    fun close() {
        if (isConnected()) {
            try {
                connection!!.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun executeQuery(query: String) {
        try {
            val statement = connection!!.prepareStatement(query)
            statement.executeUpdate()
            statement.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun execute(plugin: Plugin, query: String, async: Boolean) {
        if (async) Bukkit.getScheduler().runTaskAsynchronously(plugin) { executeQuery(query) }
        else executeQuery(query)
    }

    fun getResults(query: String): ResultSet? {
        try {
            val statement = connection!!.prepareStatement(query)
            return statement.executeQuery()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun forEach(plugin: Plugin, query: String, lambda: Consumer<ResultSet>, async: Boolean) {
        if (async) Bukkit.getScheduler()
            .runTaskAsynchronously(plugin) { forEachLambda(query, lambda) } else forEachLambda(query, lambda)
    }

    private fun forEachLambda(query: String, lambda: Consumer<ResultSet>) {
        try {
            val statement = connection!!.prepareStatement(query)
            val resultSet = statement.executeQuery()
            while (resultSet.next()) {
                lambda.accept(resultSet)
            }
            resultSet.close()
            statement.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun exists(selectColumn: String, tableName: String, targetColumn: String, targetValue: String): Boolean =
        getResults("SELECT $selectColumn FROM $tableName WHERE $targetColumn = '$targetValue'")?.next() ?: false

}