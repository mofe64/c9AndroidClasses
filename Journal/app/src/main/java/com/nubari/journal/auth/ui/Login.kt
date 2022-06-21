package com.nubari.journal.auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nubari.journal.R
import com.nubari.journal.databinding.FragmentLoginBinding

class Login : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return loginBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerLink = loginBinding.registerLink
        val btn = loginBinding.loginBtn
        btn.setOnClickListener {
            val action = LoginDirections.actionLoginToApplicationNavigationGraph()
            val navController = findNavController()
            navController.navigate(action)
        }

        registerLink.setOnClickListener {
            val action = LoginDirections.actionLoginToRegister()
            val navController = findNavController()
            navController.navigate(action)
        }


    }


}