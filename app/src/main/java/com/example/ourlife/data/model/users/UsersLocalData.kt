package com.example.ourlife.data.model.users

data class User(
    val id: Int,
    val name: String,
    val email: String
)

val u1 = User(1, "Leanne Graham", "sincere@april.biz")
val u2 = User(2, "Ervin Howell", "shanna@melissa.tv")
val u3 = User(3, "Clementine Bauch", "nathan@yesenia.net")
val u4 = User(4, "Patricia Lebsack", "julianne.OConner@kory.org")
val u5 = User(5, "Chelsey Dietrich", "lucio_hettinger@annie.ca")
val u6 = User(6, "Mrs. Dennis Schulist", "karley_dach@jasper.info")
val u7 = User(7, "Kurtis Weissnat", "telly.hoeger@billy.biz")
val u8 = User(8, "Nicholas Runolfsdottir V", "sherwood@rosamond.me")
val u9 = User(9, "Glenna Reichert", "chaim_mcdermott@dana.io")
val u10 = User(10, "Clementina DuBuque", "rey.padberg@karina.biz")

val usersList = listOf(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10)