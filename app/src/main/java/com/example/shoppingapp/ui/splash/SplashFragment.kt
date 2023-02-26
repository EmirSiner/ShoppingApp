package com.example.shoppingapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val viewModel by viewModels<SplashViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            delay(2000)
            launch {
                viewModel.uiEvent.collect {
                    when (it) {
                        is SplashViewEvent.NavigateToOnBoarding -> {
                            navigateToOnBoarding()
                        }
                        is SplashViewEvent.NavigateToMain -> {
                            navigateToMain(it.isNavigateHome)
                        }

                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    private fun navigateToMain(isNavigateHome: Boolean) {
        findNavController().navigate(R.id.action_splashFragment_to_productsFragment)
    }

    private fun navigateToOnBoarding() {
        findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
    }

}