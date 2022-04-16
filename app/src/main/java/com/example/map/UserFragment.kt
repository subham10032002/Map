package com.example.map

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment() {

    val auth by lazy {
        FirebaseAuth.getInstance()
    }

    val database by lazy {
        FirebaseFirestore.getInstance()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user, container, false)


        var docRef = database.collection("users").document(auth.uid!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    val value = document.getString("email")

                    usrEmail.text= value

                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        docRef = database.collection("users").document(auth.uid!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val valuee = document.getString("name")

                    usrName.text= valuee

                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }


        return rootView
    }


}