package com.example.shoppingapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.shoppingapp.data.model.product.Product
import com.example.shoppingapp.data.model.product.ProductDTO
import com.example.shoppingapp.databinding.FragmentBottomSheetDialogBinding
import com.example.shoppingapp.ui.ProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun addCart(products: ProductDTO){
        viewModel.addCart(products)
    }


    @SuppressLint("SetTextI18n")
    fun showDialog() {
        val args = arguments
        val product: ProductDTO? = args?.getParcelable("productForDialog")
        with(binding) {
            if (product != null) {
                Glide.with(ivDialog)
                    .load(product.image)
                    .into(ivDialog)
                tvTitle.text = product.title
                tvCategory.text = product.category
                tvDescription.text = product.description
                tvPrice.text = "$" + product.price.toString()
                addButton.setOnClickListener {
                    addCart(product)
                    Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}