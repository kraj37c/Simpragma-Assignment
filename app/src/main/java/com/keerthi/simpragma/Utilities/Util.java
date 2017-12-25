package com.keerthi.simpragma.Utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class Util {

     static ProgressDialog progressDialog;
    public static void showProgressDialog(Context context, String header, String message, Boolean isCancelable){
        if(progressDialog == null) {
            Log.d("PD", "null");
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setTitle(header);
            progressDialog.setCancelable(isCancelable);
            progressDialog.show();
//            progressDialog.show(context, header, message, false, isCancelable);
        }else {
            if (!progressDialog.isShowing()) {
                Log.d("PD", "not showing");
                progressDialog.show(context, header, message, false, isCancelable);
            } else {
                Log.d("PD", " showing");
                progressDialog.dismiss();
                progressDialog.show(context, header, message, false, isCancelable);
            }
        }
    }

    public static void dismissProgressDialog(){
        Log.d("PD", "Dismiss");
        progressDialog.dismiss();
        progressDialog = null;
    }


}
