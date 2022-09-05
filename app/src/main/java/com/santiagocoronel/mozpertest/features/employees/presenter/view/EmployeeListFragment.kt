package com.santiagocoronel.mozpertest.features.employees.presenter.view

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.santiagocoronel.androidbase.presenter.BaseActivity
import com.santiagocoronel.androidbase.presenter.BaseFragment
import com.santiagocoronel.androidbase.presenter.BaseViewModel
import com.santiagocoronel.mozpertest.R
import com.santiagocoronel.mozpertest.databinding.FragmentEmployeeListBinding
import com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.tables.EmployeeEntity
import com.santiagocoronel.mozpertest.features.employees.presenter.view.adapter.EmployeeAdapter
import com.santiagocoronel.mozpertest.features.employees.presenter.viewmodel.EmployeeViewModel
import com.santiagocoronel.mozpertest.features.splash.presenter.view.SplashActivity
import com.santiagocoronel.mozpertest.preference.PreferencesManager
import com.santiagocoronel.mozpertest.preference.PreferencesManager.KEY_LOGGED
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class EmployeeListFragment : BaseFragment<FragmentEmployeeListBinding, BaseActivity<*>>() {

    private val viewModel: EmployeeViewModel by sharedViewModel()
    private lateinit var adapter: EmployeeAdapter

    override fun getViewModel(): BaseViewModel = viewModel

    override fun init() {
        viewModel.getEmployees()

        adapter = EmployeeAdapter(context = requireContext(), onClickItem = ::onClickItem)
        binding.recyclerView.adapter = adapter

        binding.buttonCloseSession.setOnClickListener {
            closeSession()
        }
    }

    private fun closeSession() {
        PreferencesManager.getInstance().saveKey(KEY_LOGGED,"false")
        val intent = Intent(requireActivity(), SplashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun setupObserversViewModel() {
        //region common observers
        viewModel.mutableLoading.observe(this) {
            if (it) {
                binding.layoutLoading.visibility = View.VISIBLE
                binding.container.visibility = View.GONE
            } else {
                binding.layoutLoading.visibility = View.GONE
                binding.container.visibility = View.VISIBLE
            }
        }
        viewModel.mutableConnection.observe(this) {
            if (!it) {
                (binding.root.findViewById(R.id.layout_no_connection) as View).visibility =
                    View.VISIBLE
                (binding.root.findViewById(R.id.layout_no_connection) as View).findViewById<TextView>(
                    com.santiagocoronel.androidbase.R.id.textview_try
                ).setOnClickListener {
                    viewModel.getEmployees()
                }
                binding.container.visibility = View.GONE
            } else {
                (binding.root.findViewById(R.id.layout_no_connection) as View).visibility =
                    View.GONE
            }
        }
        viewModel.mutableThrowables.observe(this) {
            throwError(it)
        }
        //endregion
        viewModel.employeesLD.observe(this) { employeesList ->
            adapter.clear()
            adapter.addAll(employeesList)
            adapter.notifyDataSetChanged()
        }
        viewModel.offlineModeLD.observe(this){
           it?.let {
               if (!it.hasBeenHandled){
                   Snackbar.make(requireView(), getString(R.string.you_are_offline), Snackbar.LENGTH_LONG).show()
               }
           }
        }
    }

    private fun onClickItem(item: EmployeeEntity) {
        val action =
            EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFragment(item)
        navigate(action)
    }

    private fun navigate(navDirection: NavDirections) {
        try {
            requireActivity().findNavController(R.id.navHostHome).navigate(navDirection)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}