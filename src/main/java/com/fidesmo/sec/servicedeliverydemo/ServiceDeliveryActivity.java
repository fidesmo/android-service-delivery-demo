
package com.fidesmo.sec.servicedeliverydemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceDeliveryActivity extends Activity {

    // String for LogCat documentation
    private final static String TAG = "Fidesmo-ServiceDelivery-Demo";

    // Code to identify the call when starting Intent for Result
    static private final int SERVICE_DELIVERY_REQUEST_CODE = 724;

    // String constants defining the intent for using Fidesmo App
    private final static String SERVICE_URI = "https://api.fidesmo.com/service/";
    private final static String SERVICE_DELIVERY_CARD_ACTION = "com.fidesmo.sec.DELIVER_SERVICE";
    private final static String SERVICE_DELIVERY_MICROSD_ACTION = "com.fidesmo.sec.DELIVER_SERVICE_DEVICE_FIDELITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build layout, set activity's content view
        setContentView(R.layout.activity_service_delivery);

        // Reference UI views and elements
        final EditText spIdInput = (EditText) findViewById(R.id.sp_id_input);
        final EditText serviceIdInput = (EditText) findViewById(R.id.service_id_input);
        // Assign default values to SP ID and Service ID
        spIdInput.setText(R.string.example_sp_id);
        serviceIdInput.setText(R.string.default_service_id);

        Button transceiveToCardButton = (Button) findViewById(R.id.card_button);
        Button transceiveToDeviceFidelityButton = (Button) findViewById(R.id.devicefidelity_button);

        // Link UI elements to listeners
        transceiveToCardButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Pushed the Deliver Service To Card button");
                // call the Fidesmo App's activity to send APDUs to a
                // contactless card
                callFidesmoApp(SERVICE_DELIVERY_CARD_ACTION, spIdInput.getText().toString(),
                        serviceIdInput.getText().toString());
            }
        });

        transceiveToDeviceFidelityButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Pushed the Deliver Service To Device Fidelity button");
                // call the Fidesmo App's activity to send APDUs to a
                // DeviceFidelity micro SD card
                callFidesmoApp(SERVICE_DELIVERY_MICROSD_ACTION, spIdInput.getText().toString(),
                        serviceIdInput.getText().toString());
            }
        });

    }

    void wrongParameters() {
        // Error pop-up telling the user to review the input text
        Context context = getApplicationContext();
        String text = getString(R.string.input_error);
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    void callFidesmoApp(String action, String spId, String serviceId) {
        try {
            // create Intent to one of the two Actions exposed by the Fidesmo
            // App
            Intent intent = new Intent(action, Uri.parse(SERVICE_URI + spId + "/" + serviceId));
            // we use startActivityForResult because we want to know if the
            // operation was successful. startActivity() would also work, but we
            // would not receive a resultCode
            startActivityForResult(intent, SERVICE_DELIVERY_REQUEST_CODE);
        } catch (IllegalArgumentException e) {
            Log.i(TAG, "Parameters entered by user could not be parsed");
            // ask user to review the input parameters
            wrongParameters();
        }
    }

    // method called when the Fidesmo App activity has finished
    // Will just display a brief message depending on the result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "Entered onActivityResult()");

        if (requestCode == SERVICE_DELIVERY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "Fidesmo SEC Client returned SUCCESS");
                Toast.makeText(getApplicationContext(), getString(R.string.success),
                        Toast.LENGTH_LONG).show();
            } else {
                Log.i(TAG, "Fidesmo SEC Client returned FAILURE");
                Toast.makeText(getApplicationContext(), getString(R.string.failure),
                        Toast.LENGTH_LONG).show();
            }
        }

    }
}
