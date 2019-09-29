package org.liquibase

import liquibase.change.ColumnConfig
import liquibase.change.ConstraintsConfig
import liquibase.statement.DatabaseFunction
import java.sql.Date
import java.time.LocalDate

@LiquibaseDslMarker
class ColumnDsl<T>(name: String) {

    internal val definition = ColumnConfig.fromName(name).apply {
        constraints = ConstraintsConfig()
    }
}

fun <T> ColumnDsl<T>.description(description: String) {
    definition.remarks = description
}

fun <T> ColumnDsl<T>.defaultExpression(expression: String) {
    definition.defaultValueComputed = DatabaseFunction(expression)
}

fun ColumnDsl<Boolean>.defaultValue(value: Boolean, constraintName: String? = null) {
    definition.defaultValueConstraintName = constraintName
    definition.defaultValueBoolean = value
}

fun ColumnDsl<LocalDate>.defaultValue(value: LocalDate, constraintName: String? = null) {
    definition.defaultValueConstraintName = constraintName
    definition.defaultValueDate = Date.valueOf(value)
}

fun ColumnDsl<String>.defaultValue(value: String, constraintName: String? = null) {
    definition.defaultValueConstraintName = constraintName
    definition.defaultValue = value
}

fun <T : Number> ColumnDsl<T>.defaultValue(value: T, constraintName: String? = null) {
    definition.defaultValueConstraintName = constraintName
    definition.defaultValueNumeric = value
}

fun <T> ColumnDsl<T>.primaryKey(constraintName: String? = null) {
    definition.constraints.isPrimaryKey = true
    definition.constraints.primaryKeyName = constraintName
}

fun <T> ColumnDsl<T>.reference(reference: String, fkName: String, deleteCascade: Boolean = false) {
    definition.constraints.references = reference
    definition.constraints.foreignKeyName = fkName
    definition.constraints.isDeleteCascade = deleteCascade
}

fun <T> ColumnDsl<T>.nonNull(constraintName: String? = null) {
    definition.constraints.isNullable = false
    definition.constraints.notNullConstraintName = constraintName
}

fun <T> ColumnDsl<T>.unique(constraintName: String? = null) {
    definition.constraints.isUnique = true
    definition.constraints.uniqueConstraintName = constraintName
}

@JvmName("strLength")
fun ColumnDsl<String>.length(value: Int) {
    definition.name = "${definition.name}($value)"
}

@JvmName("autoIncrementInteger")
fun ColumnDsl<Int>.autoIncrement() {
    definition.isAutoIncrement = true
}

@JvmName("autoIncrementLong")
fun ColumnDsl<Long>.autoIncrement() {
    definition.isAutoIncrement = true
}