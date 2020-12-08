package com.pointlessapps.kupmi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pointlessapps.kupmi.managers.FragmentManager
import com.pointlessapps.kupmi.ui.FragmentBaseInterface
import com.pointlessapps.kupmi.ui.lists.FragmentLists

class ActivityMain : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager

    companion object {
        val fragments: Map<Int, FragmentBaseInterface> = mapOf(R.id.itemLists to FragmentLists())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = FragmentManager.of(this).apply {
            showIn(R.id.containerFragment)
            changeFragment(fragments.getValue(R.id.itemLists))
            onItemClickListener = { fragments[it]?.also(this::changeFragment) }
        }
    }

    override fun onBackPressed() {
        if (!fragmentManager.popHistory()) {
            super.onBackPressed()
        }
    }
}
