package com.nubari.journal.auth.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nubari.journal.auth.events.AuthEvent
import com.nubari.journal.auth.viewmodels.RegisterViewModel
import com.nubari.journal.data.model.RegistrationRequest
import com.nubari.journal.data.model.Resource
import com.nubari.journal.data.model.Status
import com.nubari.journal.databinding.FragmentRegisterBinding


class Register : Fragment() {

    private lateinit var registerBinding: FragmentRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerBinding = FragmentRegisterBinding
            .inflate(inflater, container, false)
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = registerBinding.registerBtn
        registerViewModel.isFormValid.observe(viewLifecycleOwner, {
            it?.let { isValid ->
                if (!isValid) {
                    btn.setBackgroundColor(Color.RED)
                }
            }
        })

        btn.setOnClickListener {
            val firstname = registerBinding.firstnameInput.text.toString()
            val lastname = registerBinding.lastnameInput.text.toString()
            val email = registerBinding.emailInput.text.toString()
            val password = registerBinding.passwordInput.text.toString()

            val request = RegistrationRequest(firstname, lastname, email, password)
            val event = AuthEvent.RegistrationEvent(request)
            Log.i("status", event.toString())
            registerViewModel.register(request).observe(viewLifecycleOwner, {

                if (it.status == Status.LOADING) {
                    Log.i("status", "loading")
                }
                if (it.status == Status.ERROR) {
                    Log.i("status", "error")
                    Log.i("status", it.message!!)
                }
                if (it.status == Status.SUCCESS) {
                    Log.i("status", "success")
                    Log.i("status", it.data!!.toString())
                }
            })
        }

    }


}