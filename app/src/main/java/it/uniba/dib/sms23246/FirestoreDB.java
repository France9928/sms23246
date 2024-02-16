package it.uniba.dib.sms23246;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreDB {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    public FirestoreDB() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void addData(String data) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DocumentReference userDocRef = db.collection("utenti").document(userId);

            userDocRef.update("data", data)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Dati aggiunti con successo
                            } else {
                                // Gestisci eventuali errori
                            }
                        }
                    });
        }
    }


}

