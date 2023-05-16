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

            val serviceAccount = FileInputStream("X:\\Xemonstration\\Kotlin\\FirebaseAdministrator\\ProjectsKeys\\floating-shortcuts-pro-firebase-adminsdk-qmni9-92c8e32b57.json")

            val firebaseOptions: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://floating-shortcuts-pro.firebaseio.com")
                .build()

            FirebaseApp.initializeApp(firebaseOptions)

            var listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

            var loopCounter: Int = 0

            val customersContactInformation: File = File("X:\\Xemonstration\\Kotlin\\FirebaseAdministrator\\Customers_Contact_Information.csv")

            if (!customersContactInformation.exists()) {

                customersContactInformation.appendText("Email\n")

            }

            while (listUsersPage != null) {

                for (exportedUserRecord in listUsersPage.values) {

                    println("${loopCounter}. " + "Email Address: " + exportedUserRecord.email)

                    customersContactInformation
                        .appendText("${exportedUserRecord.email}\n")

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

            val serviceAccount = FileInputStream("X:\\Xemonstration\\Kotlin\\FirebaseAdministrator\\ProjectsKeys\\super-shortcuts-pro-firebase-adminsdk-6euye-8efb5e4e3e.json")

            val firebaseOptions: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://super-shortcuts-pro.firebaseio.com")
                .build()

            FirebaseApp.initializeApp(firebaseOptions);

            var listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

            var loopCounter: Int = 0

            val customersContactInformation: File = File("X:\\Xemonstration\\Kotlin\\FirebaseAdministrator\\Customers_Contact_Information.csv")

            if (!customersContactInformation.exists()) {

                customersContactInformation.appendText("Email\n")

            }

            while (listUsersPage != null) {

                for (exportedUserRecord in listUsersPage.values) {

                    println("${loopCounter}. " + "Email Address: " + exportedUserRecord.email)

                    customersContactInformation
                        .appendText("${exportedUserRecord.email}\n")

                    loopCounter++

                }

                listUsersPage = listUsersPage.nextPage

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun clearGeeksEmpireUsers() {

        val serviceAccount = FileInputStream("path/to/serviceAccountKey.json")

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://geeks-empire-website.firebaseio.com")
            .build()

        FirebaseApp.initializeApp(options)

    }

}