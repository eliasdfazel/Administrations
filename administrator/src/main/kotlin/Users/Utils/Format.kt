package Users.Utils

fun formatCSV(firstName: String, lastName: String, emailAddress: String) : String {

    return "${emailAddress},${firstName},${lastName}"
}