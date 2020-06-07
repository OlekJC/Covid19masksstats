package com.ajdigital.covid19_mask_stats_nav.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ajdigital.covid19_mask_stats_nav.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*

class Login : Fragment() {

    private val MIN_PASSWORD_LENGTH = 8
    private val TAG = "LoginFragment"
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            login(currentUser)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        login_btn.setOnClickListener {
            checkLogin()
        }
    }

    private fun checkLogin() {
        //navController.navigate(R.id.action_login_to_mainFragment)
        val email = email_tv.editText?.text.toString()
        val password = password_tv.editText?.text.toString()

        if (email.isEmpty() || email.isBlank() || !email.contains("@")) {
            Toast.makeText(requireContext(), "Email is incorrect", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isEmpty() || password.isBlank()) {
            Toast.makeText(requireContext(), "Password is empty", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.length < MIN_PASSWORD_LENGTH) {
            Toast.makeText(
                requireContext(),
                "Password must have at least $MIN_PASSWORD_LENGTH characters",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    login(auth.currentUser!!)
                } else {
                    Toast.makeText(
                        requireContext(), "Authentication failed. ${task.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun login(user: FirebaseUser) {
        Log.d(TAG, "Logging in with ${user.email}")
        navController.navigate(R.id.action_login_to_mainFragment)
    }
}