package IO

import com.google.api.core.ApiFuture
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.QuerySnapshot
import com.google.cloud.firestore.WriteResult
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import java.io.FileInputStream


class FirestoreIO {

    fun transferFirestoreData() {

        val firestoreFloatingAi = FirestoreClient.getFirestore(FirebaseApp.initializeApp(FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(FileInputStream("X:\\Administrator\\Tokens\\floating-ai-firebase-adminsdk-cglmq-2b057331be.json")))
            .setDatabaseUrl("https://floating-ai-default-rtdb.firebaseio.com")
            .build(), "Floating AI"))

        val querySnapshot: ApiFuture<QuerySnapshot> = firestoreFloatingAi.collection("Arwen/AI/Training").get()

        val firestoreArwen = FirestoreClient.getFirestore(FirebaseApp.initializeApp(FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(FileInputStream("X:\\Administrator\\Tokens\\arwen-multitasking-administrator.json")))
            .setDatabaseUrl("https://arwen-multitasking.firebaseio.com")
            .build(), "Arwen"))

        querySnapshot.get().documents.forEach {
            println(it.data)

            val writeResult: ApiFuture<WriteResult> = firestoreArwen.document("Arwen/AI/Training/${it.id}").set(it)

            println(writeResult.get())

        }

    }

}