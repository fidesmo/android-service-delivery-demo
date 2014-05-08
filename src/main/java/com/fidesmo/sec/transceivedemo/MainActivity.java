
package com.fidesmo.sec.transceivedemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    // String for LogCat documentation
    private final static String TAG = "Fidesmo-Client-Example";

    // Fidesmo App package
    private final static String FIDESMO_APP = "com.fidesmo.sec.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build layout, set activity's content view
        setContentView(R.layout.activity_main);

        // Reference UI views and elements
        Button serviceDeliveryButton = (Button) findViewById(R.id.service_delivery_button);
        Button transceiveToDeviceFidelityButton = (Button) findViewById(R.id.transceive_button);

        // Detect if the Fidesmo App is installed
        final boolean fidesmoAppInstalled = appInstalledOrNot(FIDESMO_APP);

        // Link UI elements to listeners
        serviceDeliveryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Pushed the Service Delivery API button");
                if (fidesmoAppInstalled) {
                    // call the example Activity that uses the Service Delivery API
                    startActivity(new Intent(v.getContext(), ServiceDeliveryActivity.class));
                } else {
                    notifyMustInstall();
                }
            }
        });

        transceiveToDeviceFidelityButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Pushed the Transceive API button");
                if (fidesmoAppInstalled) {
                    // call the example Activity that uses the Transceive API
                    startActivity(new Intent(v.getContext(), TransceiveActivity.class));
                } else {
                    notifyMustInstall();
                }
            }
        });
    }

    // Use the package manager to detect if the Fidesmo App is installed on the phone
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            Log.i(TAG, "Fidesmo App not installed in phone");
            app_installed = false;
        }
        return app_installed;
    }

    // Show a Toast message to the user informing that Fidesmo App must be installed
    private void notifyMustInstall() {
        Toast.makeText(getApplicationContext(), R.string.install_app_message, Toast.LENGTH_LONG).show();
    }

}
