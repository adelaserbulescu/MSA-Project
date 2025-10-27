import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        Database.connect(
            url = "jdbc:mysql://localhost:3306/msa_project_db",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "ParolaFaina123!"
        )
    }
    fun check_status()
    {
        transaction {
            exec("SELECT 1") { rs ->
                val color_ascii_red="\u001b[31m";
                val color_ascii_green="\u001b[32m";
                val color_ascii_reset="\u001b[0m";
                if (rs.next()) {
                    println(
                        color_ascii_green +
                        "==================================================================================================================================================================" +
                        "\nSUCCESS -Database connection works!- SUCCESS\n" +
                        "=================================================================================================================================================================="
                        + color_ascii_reset
                    )
                } else {
                    println(
                        color_ascii_red +
                        "==================================================================================================================================================================" +
                        "\nFAILURE -Database connection doesn't work!- FAILURE\n" +
                        "=================================================================================================================================================================="
                        + color_ascii_reset
                    )
                }
            }
        }
    }
}
