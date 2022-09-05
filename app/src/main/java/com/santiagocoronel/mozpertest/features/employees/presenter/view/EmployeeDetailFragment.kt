package com.santiagocoronel.mozpertest.features.employees.presenter.view

import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.santiagocoronel.androidbase.presenter.BaseActivity
import com.santiagocoronel.androidbase.presenter.BaseFragment
import com.santiagocoronel.androidbase.presenter.BaseViewModel
import com.santiagocoronel.mozpertest.databinding.FragmentEmployeeDetailBinding
import com.santiagocoronel.mozpertest.features.employees.presenter.viewmodel.EmployeeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class EmployeeDetailFragment : BaseFragment<FragmentEmployeeDetailBinding, BaseActivity<*>>() {

    private val args: EmployeeDetailFragmentArgs by navArgs()
    private val viewModel: EmployeeViewModel by sharedViewModel()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun init() {
        viewModel.getEmployees()

        with(binding) {
            textViewFullName.text = "${args.employee.firstName} ${args.employee.lastName}"

            textViewDescription.text = args.employee.description

            textViewRating.text = "Rating: ${args.employee.rating}"

            Glide.with(imageView.context)
                .load(args.employee.image)
                .into(binding.imageView)

        }

    }

    override fun setupObserversViewModel() {

    }
}