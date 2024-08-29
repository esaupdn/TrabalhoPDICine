package br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.CinemaBranch;

public class CinemaBranchDAO {

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final DAOObserver observer;

    private static final String COLLECTION = "CinemaBranch";
    private static final String NAME = "name";
    private static final String LOCATION = "location";
    private static final String GROUP = "group";

    public CinemaBranchDAO(DAOObserver observer) {
        this.observer = observer;
    }

    private Map<String, Object> cinemabranchToMap(CinemaBranch cinemabranch) {
        Map<String, Object> map = new HashMap<>();
        map.put(NAME, cinemabranch.getName());
        map.put(LOCATION, cinemabranch.getLocation());
        map.put(GROUP, cinemabranch.getGroup());
        return map;
    }

    public boolean save(CinemaBranch cinemabranch) {

        Map<String, Object> cinemabranchAsMap = cinemabranchToMap(cinemabranch);
        firestore.collection(COLLECTION).add(cinemabranchAsMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                observer.saveOk();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                observer.saveError();
            }
        });

        return true;
    }

    public void loadCinemaBranches() {
        firestore.collection(COLLECTION).orderBy(NAME, Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> taskSnap) {

                if (taskSnap.isSuccessful()) {
                    List<CinemaBranch> cinemabranches = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : taskSnap.getResult()) {
                        String id = doc.getId();
                        String name = doc.get(NAME, String.class);
                        String location = doc.get(LOCATION, String.class);
                        String group = doc.get(GROUP, String.class);

                        CinemaBranch cinemabranch = new CinemaBranch(id, name, location, group);

                        cinemabranches.add(cinemabranch);
                    }

                    observer.loadOk(cinemabranches);
                } else {
                    // Não há tarefas no banco...
                    observer.loadError();
                }
            }
        });

    }

    public void delete(CinemaBranch cinemabranch) {

        firestore.collection(COLLECTION).document(cinemabranch.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                observer.deleteOk();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                observer.deleteError();
            }
        });
    }

    public void update(CinemaBranch cinemabranch) {
        Map<String, Object> cinemabranchAsMap = cinemabranchToMap(cinemabranch);

        firestore.collection(COLLECTION).document(cinemabranch.getId()).update(cinemabranchAsMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                observer.updateOk();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                observer.updateError();
            }
        });

    }
}