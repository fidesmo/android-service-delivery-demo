
package com.fidesmo.sec.transceivedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    // String for LogCat documentation
    private final static String TAG = "Fidesmo-Client-Example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build layout, set activity's content view
        setContentView(R.layout.activity_main);

        // Reference UI views and elements
        Button serviceDeliveryButton = (Button) findViewById(R.id.service_delivery_button);
        Button transceiveToDeviceFidelityButton = (Button) findViewById(R.id.transceive_button);

        // Link UI elements to listeners
        serviceDeliveryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Pushed the Service Delivery API button");
                // call the example Activity that uses the Service Delivery API
                startActivity(new Intent(v.getContext(), ServiceDeliveryActivity.class));
            }
        });

        transceiveToDeviceFidelityButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Pushed the Transceive API button");
                // call the example Activity that uses the Transceive API
                startActivity(new Intent(v.getContext(), TransceiveActivity.class));
            }
        });

    }
}
