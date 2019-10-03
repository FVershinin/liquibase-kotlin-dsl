package org.liquibase.column

import liquibase.change.ColumnConfig
import liquibase.change.ConstraintsConfig
import liquibase.statement.DatabaseFunction
import liquibase.statement.SequenceNextValueFunction
import org.liquibase.LiquibaseDslMarker
import org.liquibase.Ref
import org.liquibase.RefBlock
import java.sql.Date
import java.time.LocalDate

@LiquibaseDslMarker
class ColumnDsl<T>(name: String) {

    internal val definition = ColumnConfig.fromName(name).apply {
        constraints = ConstraintsConfig()
    }
}

var ColumnDsl<*>.description: String
    get() = TODO()
    set(value) {
        definition.remarks = value
    }

var ColumnDsl<String>.length: Int
    get() = TODO()
    set(value) {
        definition.name = "${definition.name}($value)"
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

fun <T : Number> ColumnDsl<T>.defaultNextValueSeq(seqName: String) {
    definition.defaultValueSequenceNext = SequenceNextValueFunction(seqName)
}

fun <T> ColumnDsl<T>.primaryKey(constraintName: String? = null, validated: Boolean? = null) {
    definition.constraints.isPrimaryKey = true
    definition.constraints.primaryKeyName = constraintName
    definition.constraints.setShouldValidatePrimaryKey(validated)
}

fun <T> ColumnDsl<T>.reference(fkName: String, ref: Ref, isValidated: Boolean = false, block: RefBlock? = null) {
    definition.constraints.referencedTableName = ref.first
    definition.constraints.referencedColumnNames = ref.second
    definition.constraints.foreignKeyName = fkName
    definition.constraints.setShouldValidateForeignKey(isValidated)
    block?.invoke(ReferenceDsl(definition.constraints))
}

fun <T> ColumnDsl<T>.nonNull(constraintName: String? = null, validated: Boolean? = null) {
    definition.constraints.isNullable = false
    definition.constraints.notNullConstraintName = constraintName
    definition.constraints.setShouldValidateNullable(validated)
}

fun <T> ColumnDsl<T>.unique(constraintName: String? = null, validated: Boolean? = null) {
    definition.constraints.isUnique = true
    definition.constraints.uniqueConstraintName = constraintName
    definition.constraints.setShouldValidateUnique(validated)
}

@JvmName("autoIncrementInteger")
fun ColumnDsl<Int>.autoIncrement(startWith: Int = 1, incrementBy: Int = 1) {
    definition.isAutoIncrement = true
    definition.startWith = startWith.toBigInteger()
    definition.incrementBy = incrementBy.toBigInteger()
}

@JvmName("autoIncrementLong")
fun ColumnDsl<Long>.autoIncrement(startWith: Long = 1, incrementBy: Long = 1) {
    definition.isAutoIncrement = true
    definition.startWith = startWith.toBigInteger()
    definition.incrementBy = incrementBy.toBigInteger()
}