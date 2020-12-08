package com.pointlessapps.kupmi.ui.lists

import androidx.fragment.app.viewModels
import com.pointlessapps.kupmi.R
import com.pointlessapps.kupmi.ui.FragmentBase
import com.pointlessapps.kupmi.utils.Utils

class FragmentLists : FragmentBase() {

    private val viewModelLists by viewModels<ViewModelLists>(
        factoryProducer = Utils.getViewModelFactoryProducer(
            root()
        )
    )

    override fun getLayoutId() = R.layout.fragment_lists

    override fun created() {
        viewModelLists.prepareListItems()
        viewModelLists.prepareInput()
    }
}
