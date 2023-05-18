package IO

import java.io.File

fun removeDuplication(file: File) {

    val customersContactInformation: File = File("X:\\Administrator\\Users\\Customers_Contact_Information.csv")

    val cleanList = customersContactInformation.readLines().distinct()
    println("Clean List Size: ${cleanList.size}")

    val cleanContactInformation: File = File("X:\\Administrator\\Users\\Clean_Contact_Information.csv")

    cleanList.forEach { contact ->

        if (!contact.contains(",,")) {

            cleanContactInformation
                .appendText("${contact}\n")

        }

    }

}