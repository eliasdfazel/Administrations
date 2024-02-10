package Users

import Users.Utils.formatCSV
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.*
import java.io.File
import java.io.FileInputStream

class ManageUsers {

    init {

        val customersContactInformation: File = File("X:\\Administrator\\Users\\ArwenMultitasking.csv")

    }

    fun deleteAllUsers() {

        FirebaseApp.initializeApp(FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(FileInputStream("X:\\Administrator\\Tokens\\arwen-multitasking-administrator.json")))
            .setDatabaseUrl("https://arwen-multitasking.firebaseio.com")
            .build())

        var listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

        val allUsers = ArrayList<String>()

        while (listUsersPage != null) {

            for (exportedUserRecord in listUsersPage.values) {

                allUsers.add(exportedUserRecord.uid)

            }

            FirebaseAuth.getInstance().deleteUsers(allUsers)

            allUsers.clear()

            listUsersPage = listUsersPage.nextPage

        }

    }

    fun importAllUsers() {

        try {

//            FirebaseApp.initializeApp(FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.fromStream(FileInputStream("X:\\Administrator\\Tokens\\floating-shortcuts-pro-firebase-adminsdk-qmni9-4ab2b1fd7a.json")))
//                .setDatabaseUrl("https://floating-shortcuts-pro.firebaseio.com")
//                .build())
//
//            FirebaseApp.initializeApp(FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.fromStream(FileInputStream("X:\\Administrator\\Tokens\\floating-ai-firebase-adminsdk-cglmq-2b057331be.json")))
//                .setDatabaseUrl("https://floating-ai-default-rtdb.firebaseio.com")
//                .build())
//
            FirebaseApp.initializeApp(FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(FileInputStream("X:\\Administrator\\Tokens\\super-shortcuts-pro-firebase-adminsdk-6euye-c2cd14877c.json")))
                .setDatabaseUrl("https://super-shortcuts-pro.firebaseio.com")
                .build())

            var listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

            val allUsers = ArrayList<ImportUserRecord>()

            while (listUsersPage != null) {

                for (exportedUserRecord in listUsersPage.values) {

                    //uniqueId, displayName, emailAddress, photoUrl
                    var displayName = exportedUserRecord.displayName?:"ABC XYZ"
                    displayName = displayName.replace(")", "")
                    displayName = displayName.replace("(", "")

                    if (!exportedUserRecord.email.contains("cloudtestlabaccounts")) {

                        val existedUser: UserRecord? = try {

                            FirebaseAuth.getInstance(FirebaseApp.initializeApp(FirebaseOptions.builder()
                                .setCredentials(GoogleCredentials.fromStream(FileInputStream("X:\\Administrator\\Tokens\\arwen-multitasking-administrator.json")))
                                .setDatabaseUrl("https://arwen-multitasking.firebaseio.com")
                                .build(), System.currentTimeMillis().toString())).getUserByEmail(exportedUserRecord.email)

                        } catch (e: FirebaseAuthException) {

                            null

                        }

                        if (exportedUserRecord.email != (existedUser?.email ?: "")) {

                            println("User -> ${formatCSV(exportedUserRecord.uid, displayName, exportedUserRecord.email, exportedUserRecord.photoUrl?:"https://play-lh.googleusercontent.com/-b4QAiBSRT2QMay7uaga7Ia4SbO-1kdyXCGzc_fHAUux3ntC2oxPEc44O3mPxqb04dg=s94")}")

                            allUsers.add(ImportUserRecord.builder()
                                .setUid(exportedUserRecord.uid)
                                .setEmail(exportedUserRecord.email)
                                .addUserProvider(UserProvider.builder()
                                    .setUid(exportedUserRecord.uid)
                                    .setEmail(exportedUserRecord.email)
                                    .setDisplayName(displayName)
                                    .setPhotoUrl(exportedUserRecord.photoUrl?:"https://play-lh.googleusercontent.com/-b4QAiBSRT2QMay7uaga7Ia4SbO-1kdyXCGzc_fHAUux3ntC2oxPEc44O3mPxqb04dg=s94")
                                    .setProviderId("google.com")
                                    .build())
                                .build())

                        }

                    }

                }

                println("All Users: ${allUsers.size}")

                if (allUsers.isNotEmpty()) {

                    val userImportResult  = FirebaseAuth.getInstance(FirebaseApp.initializeApp(FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(FileInputStream("X:\\Administrator\\Tokens\\arwen-multitasking-administrator.json")))
                        .setDatabaseUrl("https://arwen-multitasking.firebaseio.com")
                        .build(), System.currentTimeMillis().toString())).importUsers(allUsers)

                    for (indexedError in userImportResult.errors) {
                        println("Failed to import user: " + indexedError.reason)
                    }

                }

                allUsers.clear()

                listUsersPage = listUsersPage.nextPage

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

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

                    //First Name, Last Name, Email
                    var displayName = exportedUserRecord.displayName?:"ABC XYZ"
                    displayName = displayName.replace(")", "")
                    displayName = displayName.replace("(", "")

                    val firstName = displayName.split(" ").first()
                    val lastName = displayName.split(" ").last()

                    if (!exportedUserRecord.email.contains("cloudtestlabaccounts")) {

                        customersContactInformation
                            .appendText("${formatCSV(firstName, lastName, exportedUserRecord.email)}\n")

                    }

                    println("${loopCounter}. " + "User -> ${firstName} ${lastName}; ${exportedUserRecord.email}")

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

            FirebaseApp.initializeApp(FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(FileInputStream("X:\\Administrator\\Tokens\\super-shortcuts-pro-firebase-adminsdk-6euye-c2cd14877c.json")))
                .setDatabaseUrl("https://super-shortcuts-pro.firebaseio.com")
                .build())

            var listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

            var loopCounter: Int = 0

            val customersContactInformation: File = File("X:\\Administrator\\Users\\Customers_Contact_Information.csv")

            while (listUsersPage != null) {

                for (exportedUserRecord in listUsersPage.values) {

                    //First Name, Last Name, Email
                    var displayName = exportedUserRecord.displayName?:"ABC XYZ"
                    displayName = displayName.replace(")", "")
                    displayName = displayName.replace("(", "")

                    val firstName = displayName.split(" ").first()
                    val lastName = displayName.split(" ").last()

                    if (!exportedUserRecord.email.contains("cloudtestlabaccounts")) {

                        customersContactInformation
                            .appendText("${formatCSV(firstName, lastName, exportedUserRecord.email)}\n")

                    }

                    println("${loopCounter}. " + "User -> ${firstName} ${lastName}; ${exportedUserRecord.email}")

                    loopCounter++

                }

                listUsersPage = listUsersPage.nextPage

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * Retrieve Premium Storefront Users
     **/
    fun retrievePremiumStorefront() {
        println("|:. Premium Storefront .:|")

        try {

            val serviceAccount = FileInputStream("X:\\Administrator\\Tokens\\premium-storefront-firebase-adminsdk-x8f6b-00a0d845e2.json")

            val firebaseOptions: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://premium-storefront-default-rtdb.firebaseio.com")
                .build()

            FirebaseApp.initializeApp(firebaseOptions)

            var listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

            var loopCounter: Int = 0

            val customersContactInformation: File = File("X:\\Administrator\\Users\\Customers_Contact_Information.csv")

            while (listUsersPage != null) {

                for (exportedUserRecord in listUsersPage.values) {

                    //First Name, Last Name, Email
                    var displayName = exportedUserRecord.displayName?:"ABC XYZ"
                    displayName = displayName.replace(")", "")
                    displayName = displayName.replace("(", "")

                    val firstName = displayName.split(" ").first()
                    val lastName = displayName.split(" ").last()

                    if (!exportedUserRecord.email.contains("cloudtestlabaccounts")) {

                        customersContactInformation
                            .appendText("${formatCSV(firstName, lastName, exportedUserRecord.email)}\n")

                    }

                    println("${loopCounter}. " + "User -> ${firstName} ${lastName}; ${exportedUserRecord.email}")

                    loopCounter++

                }

                listUsersPage = listUsersPage.nextPage

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * Retrieve Arwen Users
     **/
    fun retrieveArwen() {
        println("|:. Arwen .:|")

        try {

            val serviceAccount = FileInputStream("X:\\Administrator\\Tokens\\floating-ai-firebase-adminsdk-cglmq-2b057331be.json")

            val firebaseOptions: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://floating-ai-default-rtdb.firebaseio.com")
                .build()

            FirebaseApp.initializeApp(firebaseOptions)

            var listUsersPage: ListUsersPage? = FirebaseAuth.getInstance().listUsers(null)

            var loopCounter: Int = 0

            val customersContactInformation: File = File("X:\\Administrator\\Users\\Customers_Contact_Information.csv")

            while (listUsersPage != null) {

                for (exportedUserRecord in listUsersPage.values) {

                    //First Name, Last Name, Email
                    var displayName = exportedUserRecord.displayName?:"ABC XYZ"
                    displayName = displayName.replace(")", "")
                    displayName = displayName.replace("(", "")

                    val firstName = displayName.split(" ").first()
                    val lastName = displayName.split(" ").last()

                    if (!exportedUserRecord.email.contains("cloudtestlabaccounts")) {

                        customersContactInformation
                            .appendText("${formatCSV(firstName, lastName, exportedUserRecord.email)}\n")

                    }

                    println("${loopCounter}. " + "User -> ${firstName} ${lastName}; ${exportedUserRecord.email}")

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