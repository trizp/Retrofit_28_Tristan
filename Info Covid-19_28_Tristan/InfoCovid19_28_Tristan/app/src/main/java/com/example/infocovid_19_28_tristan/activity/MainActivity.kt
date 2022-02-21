package com.example.infocovid_19_28_tristan.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.infocovid_19_28_tristan.R
import com.example.infocovid_19_28_tristan.api.RetrofitClient
import com.example.infocovid_19_28_tristan.model.IndonesiaResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showIndonesia()

        btnProvince.setOnClickListener {
            Intent(this, ProvinceActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun showIndonesia() {
        RetrofitClient.instance.getIndonesia().enqueue(object :
            Callback<ArrayList<IndonesiaResponse>> {
            override fun onFailure(call: Call<ArrayList<IndonesiaResponse>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ArrayList<IndonesiaResponse>>,
                response: Response<ArrayList<IndonesiaResponse>>
            ) {
                val indonesia = response.body()?.get(0)
                val positive = indonesia?.positif
                val hospitilized = indonesia?.dirawat
                val recover = indonesia?.sembuh
                val death = indonesia?.meninggal

                tvPositive.text = positive
                tvHospitalized.text = hospitilized
                tvRecover.text = recover
                tvDeath.text = death
            }

        })
    }
}