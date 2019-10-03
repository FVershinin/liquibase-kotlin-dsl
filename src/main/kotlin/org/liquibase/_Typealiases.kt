package org.liquibase

import org.liquibase.column.ColumnDsl
import org.liquibase.column.ReferenceDsl

typealias ColumnBlock<T> = ColumnDsl<T>.() -> Unit

typealias RefBlock = ReferenceDsl.() -> Unit

typealias TableBlock = CreateTableDsl.() -> Unit

typealias Ref = Pair<String, String>