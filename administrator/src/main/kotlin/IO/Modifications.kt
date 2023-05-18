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

fun removeInvalid() {

    val invalidContacts: File = File("X:\\Administrator\\Users\\Invalid_Contacts.csv")
    val allInvalidContacts = invalidContacts.readLines()

    val cleanContactInformation: File = File("X:\\Administrator\\Users\\Clean_Contact_Information.csv")
    val allCleanContacts = ArrayList<String>(cleanContactInformation.readLines())
    println(allCleanContacts.size)

    val validContactInformation: File = File("X:\\Administrator\\Users\\Valid_Contact_Information.csv")

    var counter = 0

    allCleanContacts.forEachIndexed { index, value  ->

        val email = value.split(",").first()

        if (isContains(allInvalidContacts, email)) {

            counter++

            println("${index}. Invalid Entry; ${value} - ${counter}")

        } else {

            validContactInformation
                .appendText("${value}\n")

        }

    }

}

fun isContains(inputList: List<String>, inputValue: String) : Boolean {

    var isContain = false

    for (it in inputList) {

        if (it.contains(inputValue)) {

            isContain = true

            break

        }

    }

    return isContain
}