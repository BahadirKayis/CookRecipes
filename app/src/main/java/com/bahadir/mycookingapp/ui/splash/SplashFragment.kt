package com.bahadir.mycookingapp.ui.splash

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.animationLotti.playAnimation()

        CoroutineScope(Dispatchers.Main).launch {
            delay(2200)
            val animation = AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.zoom_in
            )
            binding.animationLotti.startAnimation(
                animation
            )

            delay(300)
            findNavController().navigate(R.id.action_splashFragment_to_randomFoodFragment)


        }


    }


}