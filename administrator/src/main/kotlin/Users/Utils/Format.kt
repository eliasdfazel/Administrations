package Users.Utils

fun formatCSV(uniqueId: String , displayUsername: String, emailAddress: String, photoUrl: String) : String {

    return "${uniqueId},${displayUsername},${emailAddress},${photoUrl}"
}

fun formatCSV(firstName: String, lastName: String, emailAddress: String) : String {

    return "${emailAddress},${firstName},${lastName}"
}