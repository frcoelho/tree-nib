package org.nib.pwmcontrol

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnSeekBarChangeListener {

    private val baseURL = "http://192.168.4.1/"
    private var queue : RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        queue = Volley.newRequestQueue(this)
        seekMotor.setOnSeekBarChangeListener(this)
        seekLight.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean) {
        var url: String
        if(seekBar == seekMotor) {
            url = "${baseURL}pwm?value=$progress"
        }else{
            url = "${baseURL}light?value=$progress"
        }
        Log.d("CUEN",url)
        val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
        queue?.add(sr)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }
}
