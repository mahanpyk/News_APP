package com.example.kotlintoturial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlintoturial.Adapter.News_Adapter
import com.example.kotlintoturial.Adapter.Sources_Adapter
import com.example.kotlintoturial.Model.ModelsResponse
import com.example.kotlintoturial.Model.Models
import com.example.kotlintoturial.WebService.API_Client
import com.example.kotlintoturial.WebService.API_Interface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    internal lateinit var recyclerview: RecyclerView
    internal lateinit var rv_layoutmanager: LinearLayoutManager
    internal lateinit var rv_adapter: News_Adapter
    internal val apiclient = API_Client() //Calling class for using method to other class
    internal val apiinterface = apiclient.getclient()?.create(API_Interface::class.java)
    private val APIKEY = "bc8dd652e95c494f8497851ec40eb857"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val swipe: SwipeRefreshLayout = findViewById(R.id.swipe)

        requset_news(null)
        requset_source()

        //swipe refresh layout
        swipe.setOnRefreshListener {

            requset_news(null)
            requset_source()
            swipe.isRefreshing = false
        }

    }

    fun requset_news(sourceID: String?) {
        val call: Call<ModelsResponse>
        if (sourceID == null) {
            call = apiinterface!!.getnews()// !! = not null
        } else {
            call = apiinterface!!.getnewssource(sourceID, APIKEY)
        }
        call.enqueue(object : Callback<ModelsResponse> {
            override fun onResponse(call: Call<ModelsResponse>, response: Response<ModelsResponse>) {
                if (response.isSuccessful) {
                    var news: List<Models> = response.body()!!.newsList!!
                    setupRV_news(news)
                }
            }

            override fun onFailure(call: Call<ModelsResponse>, t: Throwable) {
                Log.e("Error", "$t")
            }
        })
    }

    fun setupRV_news(news: List<Models>) {
        recyclerview = findViewById(R.id.rv)
        rv_layoutmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_adapter = News_Adapter(this, news)
        recyclerview.layoutManager = rv_layoutmanager
        recyclerview.adapter = rv_adapter
        recyclerview.itemAnimator = DefaultItemAnimator()
    }

    fun requset_source() {
        val call: Call<ModelsResponse.sourceResponse> = apiinterface!!.getsources()// !! = not null
        call.enqueue(object : Callback<ModelsResponse.sourceResponse> {
            override fun onResponse(
                call: Call<ModelsResponse.sourceResponse>,
                response: Response<ModelsResponse.sourceResponse>
            ) {
                if (response.isSuccessful) {
                    val sources: List<Models.Source> = response.body()!!.sourcelist!!
                    setupRV_sources(sources)
                }
            }

            override fun onFailure(call: Call<ModelsResponse.sourceResponse>, t: Throwable) {
                Log.e("Error", "$t")
            }
        })
    }

    fun setupRV_sources(sources: List<Models.Source>) {
        recyclerview = findViewById(R.id.rv_category)
        rv_layoutmanager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = Sources_Adapter(this, sources, object : Sources_Adapter.SourceViewCallback {
            override fun onSourceItemClick(sources: String) {
                requset_news(sources)
            }
        })
        recyclerview.layoutManager = rv_layoutmanager
        recyclerview.adapter = adapter
        recyclerview.itemAnimator = DefaultItemAnimator()
    }

}
