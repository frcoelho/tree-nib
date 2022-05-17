package br.org.nib.lustre20.lustre

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_lustre.*


class LustreActivity : AppCompatActivity(){
    private val baseURL = "http://192.168.4.1/"
    private var queue : RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lustre)
        supportActionBar?.hide()

        queue = Volley.newRequestQueue(this)

        kboom.setOnClickListener {
            val url = baseURL+"explosion"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        dancer.setOnClickListener {
            val url = baseURL+"dance"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////

        hold1.setOnTouchListener { _, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    val url = baseURL+"rgb?params=ff0000"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val url = baseURL+"rgb?params=000000"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                else -> {
                    true
                }
            }
        }

        hold2.setOnTouchListener { _, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    val url = baseURL+"rgb?params=00ff00"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val url = baseURL+"rgb?params=000000"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                else -> {
                    true
                }
            }
        }

        hold3.setOnTouchListener { _, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    val url = baseURL+"rgb?params=0000ff"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val url = baseURL+"rgb?params=000000"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                else -> {
                    true
                }
            }
        }

        hold4.setOnTouchListener { _, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    val url = baseURL+"rgb?params=ffff00"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val url = baseURL+"rgb?params=000000"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                else -> {
                    true
                }
            }
        }

        hold5.setOnTouchListener { _, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    val url = baseURL+"rgb?params=ffffff"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val url = baseURL+"rgb?params=000000"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                else -> {
                    true
                }
            }
        }

        hold6.setOnTouchListener { _, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    val url = baseURL+"rgb?params=00ffb6"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val url = baseURL+"rgb?params=000000"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                else -> {
                    true
                }
            }
        }

        hold7.setOnTouchListener { _, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    val url = baseURL+"rgb?params=ff00e0"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val url = baseURL+"rgb?params=000000"
                    val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
                    queue?.add(sr)
                    true
                }
                else -> {
                    true
                }
            }
        }

        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////

        color1.setOnClickListener {
            val url = baseURL+"rgb?params=ff0000"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        color2.setOnClickListener {
            val url = baseURL+"rgb?params=00ff00"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        color3.setOnClickListener {
            val url = baseURL+"rgb?params=0000ff"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        color4.setOnClickListener {
            val url = baseURL+"rgb?params=ffff00"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        color5.setOnClickListener {
            val url = baseURL+"rgb?params=ffffff"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        color6.setOnClickListener {
            val url = baseURL+"rgb?params=00ffb6"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        color7.setOnClickListener {
            val url = baseURL+"rgb?params=ff00e0"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////

        fade1.setOnClickListener {
            val url = baseURL+"fade?params=ff0000"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        fade2.setOnClickListener {
            val url = baseURL+"fade?params=00ff00"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        fade3.setOnClickListener {
            val url = baseURL+"fade?params=0000ff"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        fade4.setOnClickListener {
            val url = baseURL+"fade?params=ffff00"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        fade5.setOnClickListener {
            val url = baseURL+"fade?params=ffffff"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        fade6.setOnClickListener {
            val url = baseURL+"fade?params=00ffb6"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        fade7.setOnClickListener {
            val url = baseURL+"fade?params=ff00e0"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////

        blink1.setOnClickListener {
            val url = baseURL+"blink?params=ff0000"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        blink2.setOnClickListener {
            val url = baseURL+"blink?params=00ff00"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        blink3.setOnClickListener {
            val url = baseURL+"blink?params=0000ff"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        blink4.setOnClickListener {
            val url = baseURL+"blink?params=ffff00"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        blink5.setOnClickListener {
            val url = baseURL+"blink?params=ffffff"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        blink6.setOnClickListener {
            val url = baseURL+"blink?params=00ffb6"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        blink7.setOnClickListener {
            val url = baseURL+"blink?params=ff00e0"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////

        colorOff.setOnClickListener {
            val url = baseURL+"rgb?params=000000"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////

        r1On.setOnClickListener {
            val url = baseURL+"r1?params=1"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }
        r1Off.setOnClickListener {
            val url = baseURL+"r1?params=0"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }

        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////

        r2On.setOnClickListener {
            val url = baseURL+"r2?params=1"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }
        r2Off.setOnClickListener {
            val url = baseURL+"r2?params=0"
            val sr = object : StringRequest(Request.Method.POST, url, Response.Listener { }, Response.ErrorListener { _ -> }) {}
            queue?.add(sr)
        }
    }
}
