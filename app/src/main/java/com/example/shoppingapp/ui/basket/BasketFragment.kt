package com.example.shoppingapp.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.shoppingapp.data.mapper.ProductMapper
import com.example.shoppingapp.data.model.carts.CartFirebaseModel
import com.example.shoppingapp.databinding.FragmentBasketBinding


class BasketFragment : Fragment() {

    lateinit var basketAdapter: BasketAdapter
    private var _binding: FragmentBasketBinding? = null
    private val viewModel: BasketViewModel by viewModels()
    private val binding get() = _binding!!
    private val mapper = ProductMapper()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }


    private fun observe() {
        lifecycleScope.launchWhenResumed {
            viewModel.basketList.observe(viewLifecycleOwner) {
                basketAdapter.product = it

            }
        }
    }
    fun addToUserCart(cartFirebaseModel: CartFirebaseModel) {
viewModel.addToUserCart(cartFirebaseModel)
    }
}