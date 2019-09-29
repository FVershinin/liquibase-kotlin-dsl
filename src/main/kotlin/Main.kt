import liquibase.Liquibase
import liquibase.database.core.H2Database
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.liquibase.*
import java.sql.DriverManager
import java.time.LocalDate

fun main() {
    val changelog = changeLog {
        changeSet("0", "firstname.lastname") {
            createTable(name = "user") {
                "id" bigint {
                    primaryKey()
                    autoIncrement()
                }
                "username" varchar { length(50); unique() }
                "password" varchar { length(50) }
                "firstname" varchar { length(100) }
                "lastname" varchar { length(100) }
            }
            createTable(name = "group") {
                "id" bigint {
                    primaryKey()
                    autoIncrement()
                    description("Test")
                }
                "name" varchar {
                    unique()
                }
            }
            createTable(name = "event") {
                "id" bigint { autoIncrement(); primaryKey() }
                "name" varchar { nonNull() }
                "date" date { nonNull(); defaultValue(LocalDate.now()) }
                "user_id" bigint {
                    reference("user(id)", "fk_event_user", false)
                }
            }
        }
    }
    val database = H2Database().apply {
        connection = JdbcConnection(DriverManager.getConnection("jdbc:h2:mem:"))
    }
    val liquibase = Liquibase(changelog, ClassLoaderResourceAccessor(), database)
    liquibase.update("");
}