import IO.removeDuplication
import Users.ManageUsers
import java.io.File

fun main(args: Array<String>) {

    val manageUsers: ManageUsers = ManageUsers()

//    manageUsers.retrieveFloatIt()
//    manageUsers.retrieveSuperShortcuts()
//    manageUsers.retrievePremiumStorefront()
//    manageUsers.retrieveArwen()

    removeDuplication(File("X:\\Administrator\\Users\\Customers_Contact_Information.csv"))

}