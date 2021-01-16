package forApi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class Common {
    public static String getVolleyErrorMessage(VolleyError volleyError) {

        String message;
        if (volleyError instanceof NetworkError) {
            message = Constant.NETWORK_ERROR;
        } else if (volleyError instanceof ServerError) {
            message = Constant.SERVER_ERROR;
        } else if (volleyError instanceof AuthFailureError) {
            message = Constant.AUTHFAILURE_ERROR;
        } else if (volleyError instanceof ParseError) {
            message = Constant.PARSE_ERROR;
        } else if (volleyError instanceof NoConnectionError) {
            message = Constant.NOCONNECTION_ERROR;
        } else if (volleyError instanceof TimeoutError) {
            message = Constant.TIMEOUT_ERROR;
        } else {
            message = Constant.INVALID_ERROR;
        }

        return message;
    }

    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
