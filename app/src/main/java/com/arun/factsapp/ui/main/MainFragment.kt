package com.arun.factsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arun.factsapp.R
import com.arun.factsapp.di.FactRepoInjection
import com.arun.factsapp.model.Fact
import com.arun.factsapp.ui.main.viewmodel.FactResponseUI
import com.arun.factsapp.ui.main.viewmodel.FactViewModel
import com.arun.factsapp.ui.main.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var adapter: FactsAdapter
    open lateinit var viewModel: FactViewModel //declared open for test

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModels()
        setupPullToRefresh()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFacts()
    }

    private fun setupViewModels() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(FactRepoInjection.provideFactRepository())).get(FactViewModel::class.java)
        viewModel.response.observe(this, responseObserver)
    }

    private fun setupPullToRefresh() {
        context?.let {
            swipe_refresh?.apply {
                setColorSchemeColors(ContextCompat.getColor(it, R.color.colorPrimary))
                setOnRefreshListener {
                    viewModel.getFacts()
                }
            }
        }
    }

    private val responseObserver = Observer<FactResponseUI> {
        swipe_refresh?.isRefreshing = false
        setupRecyclerView(it.facts)
        empty_text.showView(it.isEmpty)
        error_text.showView(it.isError)
        progressBar.showView(it.isLoading)
        if(it.title.isNotBlank()) {
            (activity as TitleUpdater).updateTitle(it.title)
        }
    }

    private fun setupRecyclerView(facts: List<Fact>) {
        adapter = FactsAdapter(facts)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        val itemDecor = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        recyclerView.addItemDecoration(itemDecor)
    }

}
