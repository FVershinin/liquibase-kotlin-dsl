package org.liquibase

typealias ColumnBlock<T> = ColumnDsl<T>.() -> Unit

typealias TableBlock = CreateTableDsl.() -> Unit