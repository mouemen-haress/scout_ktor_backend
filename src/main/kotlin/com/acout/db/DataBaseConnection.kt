package com.acout.db

import org.ktorm.database.Database

object DataBaseConnection {
    var database = Database.connect(
        url = "jdbc:mysql://localhost:3306/scout",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "1234aA@@@@"
    )
}