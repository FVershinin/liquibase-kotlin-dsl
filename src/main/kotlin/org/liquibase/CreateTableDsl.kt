package org.liquibase

import liquibase.change.core.CreateTableChange
import org.liquibase.column.ColumnDsl
import java.time.LocalDate

@LiquibaseDslMarker
class CreateTableDsl(name: String) {

    var remarks: String? = null

    var tablespace: String? = null

    internal val createTableChange = CreateTableChange().also {
        it.tableName = name
        it.remarks = remarks
        it.tablespace = tablespace
    }

    infix fun String.boolean(block: ColumnDsl<Boolean>.() -> Unit) {
        val column = ColumnDsl<Boolean>(this).also {
            it.definition.type = "boolean"
            block(it)
        }
        createTableChange.addColumn(column.definition)
    }

    infix fun String.bigint(block: ColumnDsl<Long>.() -> Unit) {
        val column = ColumnDsl<Long>(this).also {
            it.definition.type = "bigint"
            block(it)
        }
        createTableChange.addColumn(column.definition)
    }

    infix fun String.date(block: ColumnDsl<LocalDate>.() -> Unit) {
        val column = ColumnDsl<LocalDate>(this).also {
            it.definition.type = "date"
            block(it)
        }
        createTableChange.addColumn(column.definition)
    }

    infix fun String.int(block: ColumnDsl<Int>.() -> Unit) {
        val column = ColumnDsl<Int>(this).also {
            it.definition.type = "int"
            block(it)
        }
        createTableChange.addColumn(column.definition)
    }

    infix fun String.smallint(block: ColumnDsl<Int>.() -> Unit) {
        val column = ColumnDsl<Int>(this).also {
            it.definition.type = "smallint"
            block(it)
        }
        createTableChange.addColumn(column.definition)
    }

    infix fun String.tinyint(block: ColumnDsl<Int>.() -> Unit) {
        val column = ColumnDsl<Int>(this).also {
            it.definition.type = "tinyint"
            block(it)
        }
        createTableChange.addColumn(column.definition)
    }

    infix fun String.varchar(block: ColumnDsl<String>.() -> Unit) {
        val column = ColumnDsl<String>(this).also {
            it.definition.type = "varchar"
            block(it)
        }
        createTableChange.addColumn(column.definition)
    }
}