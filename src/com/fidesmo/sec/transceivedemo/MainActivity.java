
package com.fidesmo.sec.transceivedemo;

import java.util.UUID;

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

public class MainActivity extends Activity {

    // String for LogCat documentation
    private final static String TAG = "Fidesmo-Transceive-Demo";

    // String constants defining the intent for using Fidesmo SEC Client
    private final static String INTENT_URI = "https://api.fidesmo.com/transaction/";
    private final static String TRANSCEIVE_CARD_ACTION = "com.fidesmo.sec.TRANSCEIVE";
    private final static String TRANSCEIVE_MICROSD_ACTION = "com.fidesmo.sec.TRANSCEIVE_DEVICE_FIDELITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build layout, set activity's content view
        setContentView(R.layout.activity_main);

        // Reference UI views and elements
        final EditText uuidInput = (EditText) findViewById(R.id.uuid_input);
        Button transceiveToCardButton = (Button) findViewById(R.id.card_button);
        Button transceiveToDeviceFidelityButton = (Button) findViewById(R.id.devicefidelity_button);

        // Link UI elements to listeners
        transceiveToCardButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Pushed the Transceive To Card button");
                // call the SEC Client's activity to send APDUs to a contactless
                // card
                callFidesmoSECClient(TRANSCEIVE_CARD_ACTION, uuidInput.getText().toString());
            }
        });

        transceiveToDeviceFidelityButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Pushed the Transceive To Device Fidelity button");
                // call the SEC Client's activity to send APDUs to a
                // DeviceFidelity micro SD card
                callFidesmoSECClient(TRANSCEIVE_MICROSD_ACTION, uuidInput.getText().toString());
            }
        });

    }

    void wrongUuid() {
        // Error pop-up telling the user to review the input text
        Context context = getApplicationContext();
        String text = getString(R.string.uuid_error);
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    void callFidesmoSECClient(String action, String uuid) {
        try {
            // Verify that the UUID has a correct format by attempting to parse
            // it
            UUID.fromString(uuid);
            // create Intent to one of the two Actions exposed by Fidesmo SEC
            // Client
            Intent intent = new Intent(action, Uri.parse(INTENT_URI + uuid));
            startActivity(intent);

        } catch (IllegalArgumentException e) {
            Log.i(TAG, "UUID entered by user could not be parsed");
            // ask user to enter a valid UUID
            wrongUuid();
        }
    }
}
