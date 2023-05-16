package Users

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ListUsersPage
import java.io.File
import java.io.FileInputStream

class ManageUsers {

    /**
     * Retrieve Float It Users
     **/
    fun retrieveFloatIt() {
        println("|:. Float It .:|")

        try {

            val serviceAccount = FileInputStream("X:\\Administrator\\Tokens\\floating-shortcuts-pro-firebase-adminsdk-qmni9-4ab2b1fd7a.json")

            val firebaseOptions: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://floating-shortcuts-pro.firebaseio.com")
                .build()

            FirebaseApp.initializeApp(firebaseOptions)

            var listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

            var loopCounter: Int = 0

            val customersContactInformation: File = File("X:\\Administrator\\Users\\Customers_Contact_Information.csv")

            while (listUsersPage != null) {

                for (exportedUserRecord in listUsersPage.values) {

                    //Email, First Name, Last Name
                    val firstName = exportedUserRecord.displayName.split(" ").first()
                    val lastName = exportedUserRecord.displayName.split(" ").last()

                    customersContactInformation
                        .appendText("${exportedUserRecord.email},${firstName},${lastName}\n")

                    println("${loopCounter}. " + "User: ${exportedUserRecord.email},${firstName},${lastName}")

                    loopCounter++

                }

                listUsersPage = listUsersPage.nextPage

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * Retrieve Super Shortcuts Users
     **/
    fun retrieveSuperShortcuts() {
        println("|:. Super Shortcuts .:|")

        try {

            val serviceAccount = FileInputStream("X:\\Administrator\\Tokens\\super-shortcuts-pro-firebase-adminsdk-6euye-c2cd14877c.json")

            val firebaseOptions: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://super-shortcuts-pro.firebaseio.com")
                .build()

            FirebaseApp.initializeApp(firebaseOptions);

            var listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

            var loopCounter: Int = 0

            val customersContactInformation: File = File("X:\\Administrator\\Users\\Customers_Contact_Information.csv")

            while (listUsersPage != null) {

                for (exportedUserRecord in listUsersPage.values) {

                    //Email, First Name, Last Name
                    val firstName = exportedUserRecord.displayName.split(" ").first()
                    val lastName = exportedUserRecord.displayName.split(" ").last()

                    customersContactInformation
                        .appendText("${exportedUserRecord.email},${firstName},${lastName}\n")

                    println("${loopCounter}. " + "User: ${exportedUserRecord.email},${firstName},${lastName}")

                    loopCounter++

                }

                listUsersPage = listUsersPage.nextPage

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun clearGeeksEmpireUsers() {

        val serviceAccount = FileInputStream("X:\\Administrator\\Tokens\\geeks-empire-website-firebase-adminsdk-ax89e-1159331875.json")

        val firebaseOptions = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://geeks-empire-website.firebaseio.com")
            .build()

        FirebaseApp.initializeApp(firebaseOptions)

        val listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

        listUsersPage?.values?.forEach { exportedUserRecord ->
            println("User To Delete: ${exportedUserRecord.email}")

            FirebaseAuth.getInstance().deleteUser(exportedUserRecord.uid)

        }

    }

}