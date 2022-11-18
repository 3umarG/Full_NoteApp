package com.example.noteapp.ui.fragments

import android.animation.Animator
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        requireActivity().setTheme(R.style.Theme_AppCompat_Splash)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = activity?.findViewById<View>(android.R.id.content)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            content?.viewTreeObserver?.addOnDrawListener { false }
        }
        splash()

    }

    private fun splash() {
        val directions = SplashFragmentDirections.actionSplashFragmentToNotesFragment()
        val navHostFragment =
            activity?.let {
                it.supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            }
        val navController = navHostFragment?.navController

        binding.splashLottie.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
                println("LOTTIE : Start")
            }

            override fun onAnimationEnd(p0: Animator) {
                navController?.navigate(directions)
            }

            override fun onAnimationCancel(p0: Animator) {
                println("LOTTIE : Cancelled")
            }

            override fun onAnimationRepeat(p0: Animator) {
                println("LOTTIE : Repeat")
            }
        })
    }

}