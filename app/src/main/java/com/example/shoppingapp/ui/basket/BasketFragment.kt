package com.example.shoppingapp.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.carts.CartFirebaseModel
import com.example.shoppingapp.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment(),BasketItemClickListener {
    private lateinit var binding : FragmentBasketBinding
    private val viewModel by viewModels<BasketViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

    }


    private fun observe() {
        lifecycleScope.launchWhenResumed {

            viewModel.basketList.observe(viewLifecycleOwner) {
                (binding.basketRecycler.adapter as BasketAdapter).product = it

            }
        }
    }
    fun addToUserCart(cartFirebaseModel: CartFirebaseModel) {
viewModel.addToUserCart(cartFirebaseModel)
    }
    fun updateToUserCartProduct(cartFirebaseModel: CartFirebaseModel) {
       viewModel.updateToUserCartProduct(cartFirebaseModel)
    }
    fun deleteCart(cartFirebaseModel: CartFirebaseModel){
        viewModel.deleteProduct(cartFirebaseModel)
    }
    fun clearCart (){
        viewModel.clearUserCart()
    }

    override fun onItemClick(product: ProductEntity) {
        TODO("Not yet implemented")
    }
}