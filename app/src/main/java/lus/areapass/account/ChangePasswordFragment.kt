package lus.areapass.account

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.account.viewmodels.ChangePasswordViewModel
import lus.areapass.di.activityViewModel
import lus.areapass.di.injector
import lus.areapass.di.viewModel
import lus.areapass.notification.InfoDialog


class ChangePasswordFragment : BaseFragment<ChangePasswordViewModel, ViewDataBinding>() {

    private val navigation by activityViewModel { injector.accountViewModel }

    override val viewModel by viewModel {
        injector.changePasswordViewModel.apply {
            onChanged.observe(this@ChangePasswordFragment, Observer { onPasswordChanged() })
            errors.observe(this@ChangePasswordFragment, Observer { onFailure(it) })
        }
    }

    override fun getLayoutId() = R.layout.fragment_change_password

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation.showToolbar(title = getString(R.string.title_change_password))
    }

    private fun onPasswordChanged() {
        InfoDialog.show(childFragmentManager, "Password is successfully changed")
    }

}