package forApi

import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.royalsoftsolutions.drowingusingcardview.UnderMaintainanceActivity
import forApi.Common.getVolleyErrorMessage
import forApi.Common.isConnected
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class StringRequestVolley(
    private val context: Context,
    private val paramsMap: Map<String, String>,
    private val volleyResponseListener: VolleyResponseListener
) {

    private val requestQueue: RequestQueue


    private fun sendStringRequest() {
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, Constant.BASE_URL,
            Response.Listener { response ->

                try {
                    val jsonObject = JSONObject(response)
                    volleyResponseListener.onResponse(response)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->

                val errorMessage = getVolleyErrorMessage(error)
                if (errorMessage == Constant.SERVER_ERROR) {
                    //ClearData.clearAllData(context);
                    val intent = Intent(context, UnderMaintainanceActivity::class.java)
                    intent.putExtra("errorMessage", errorMessage)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    context.startActivity(intent)
                } else {
                    volleyResponseListener.onError(errorMessage)
                }
            }) {
            override fun getHeaders(): Map<String, String> {

                val headers: MutableMap<String, String> = HashMap()
                //headers.put("Content-Type", "application/x-www-form-urlencoded");
              headers["Apikey"] = "GSFS"
                headers["Userdevicetype"] ="android"
                headers["Userdevicetoken"] = ""
                headers["Userdeviceid"] = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                headers["Userid"] = "1"

                return headers
            }

            override fun getParams(): Map<String, String> {

                return paramsMap
            }
        }

        stringRequest.setShouldCache(false)
        stringRequest.retryPolicy =
            DefaultRetryPolicy(120000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        requestQueue.add(stringRequest)
    }

    init {
        requestQueue = Volley.newRequestQueue(context)


        if (isConnected(context))
            sendStringRequest()
        else
            volleyResponseListener.onError("No Internet Connection")
    }
}