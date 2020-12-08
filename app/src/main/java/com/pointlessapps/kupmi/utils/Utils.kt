package com.pointlessapps.kupmi.utils

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object Utils {

    fun getViewModelFactoryProducer(root: ViewGroup): () -> ViewModelProvider.Factory = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>) =
                modelClass.getConstructor(ViewGroup::class.java).newInstance(root)
        }
    }

    open class SingletonHolder<T : Any, in A>(creator: (A?) -> T) {
        private var creator: ((A?) -> T)? = creator

        @Volatile
        protected var instance: T? = null

        fun init(arg: A? = null): T {
            if (instance != null) return instance!!

            return synchronized(this) {
                if (instance != null) instance!!
                else {
                    val created = creator!!(arg)
                    instance = created
                    creator = null
                    created
                }
            }
        }
    }
}
