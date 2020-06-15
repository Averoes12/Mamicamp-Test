package com.averoes.booksapp.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.averoes.booksapp.R
import com.averoes.booksapp.model.genre.Genre
import com.averoes.booksapp.ui.home.GenreAdapter
import com.averoes.booksapp.utils.ConfigRetrofit
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }
    private fun getGender() {
        ConfigRetrofit().getService().getGenre().enqueue(object : Callback<Genre?> {
            override fun onFailure(call: Call<Genre?>, t: Throwable) {
                Log.d("RESPONSE", "Error ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Genre?>, response: Response<Genre?>) {
                if (response.isSuccessful) {
                    val genre = response.body().toString().toList()
                    ArrayAdapter(context!!, android.R.layout.simple_spinner_item, genre)
                }
            }

        })
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}