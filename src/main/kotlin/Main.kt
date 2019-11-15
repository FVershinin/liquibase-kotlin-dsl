import liquibase.Liquibase
import liquibase.database.core.H2Database
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.liquibase.*
import org.liquibase.column.*
import java.sql.DriverManager
import java.time.LocalDate

fun main() {
    val changelog = changeLog {
        changeSet("0", "firstname.lastname") {
            createTable(name = "user") {
                "id" bigint {
                    description = "some description";
                    primaryKey(validated = true);
                    autoIncrement(startWith = 32, incrementBy = 10)
                }
                "username" varchar {
                    description = ""
                    length = 50
                    unique()
                }
                "password" varchar {
                    description = "some description 2"
                    length = 50
                }
                "firstname" varchar { length = 50; description = "qwe"; unique() }
                "lastname" varchar {
                    length = 10
                }
                "version" smallint {
                    autoIncrement()
                }
            }
            createTable(name = "group") {
                "id" bigint {
                    primaryKey()
                    autoIncrement()
                }
                "name" varchar {
                    unique()
                }
            }
            createTable(name = "event") {
                "id" bigint { autoIncrement(); primaryKey() }
                "name" varchar {
                    nonNull()
                }
                "date" date {
                    nonNull();
                    defaultValue(LocalDate.now())
                }
                "user_id" bigint {
                    nonNull();
                    reference("fk_event_user", "user" to "id") {
                    }
                }
            }
        }
        changeSet("a", "b") {
            createView("ev") { replaceIfExists(); sql = """select * from event""" }
        }
    }
    val database = H2Database().apply {
        connection = JdbcConnection(DriverManager.getConnection("jdbc:h2:mem:"))
    }
    val liquibase = Liquibase(changelog, ClassLoaderResourceAccessor(), database)
    liquibase.update("");
}