package org.liquibase.column

import liquibase.change.ConstraintsConfig
import org.liquibase.LiquibaseDslMarker

@LiquibaseDslMarker
class ReferenceDsl(private val constraints: ConstraintsConfig) {

    var schema: String
        get() = TODO()
        set(value) {
            constraints.referencedTableSchemaName = value
        }

    var catalog: String
        get() = TODO()
        set(value) {
            constraints.referencedTableCatalogName = value
        }

    var isDeleteCascade: Boolean
        get() = TODO()
        set(value) {
            constraints.isDeleteCascade = value
        }

    var isDeferrable: Boolean
        get() = TODO()
        set(value) {
            constraints.isDeferrable = value
        }

    var isInitiallyDeferred: Boolean
        get() = TODO()
        set(value) {
            constraints.isInitiallyDeferred = value
        }
}