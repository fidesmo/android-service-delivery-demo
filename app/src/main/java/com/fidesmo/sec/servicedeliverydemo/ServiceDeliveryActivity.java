package com.fidesmo.sec.servicedeliverydemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceDeliveryActivity extends AppCompatActivity {

    // String for LogCat documentation
    private final static String TAG = "ServiceDelivery";

    // Fidesmo App package
    private final static String FIDESMO_APP = "com.fidesmo.sec.android";

    // Code to identify the call when starting Intent for Result
    static private final int SERVICE_DELIVERY_REQUEST_CODE = 724;

    // Code to identify the call when starting Intent for Result
    static private final int GOOGLE_PLAY_REQUEST_CODE = 472;

    // String constants defining the intent for using Fidesmo App
    private final static String SERVICE_URI = "https://api.fidesmo.com/";
    private final static String SERVICE_DELIVERY_CARD_ACTION = "com.fidesmo.sec.DELIVER_SERVICE";

    // URLs for Google Play app and to install apps via browser
    private final static String MARKET_URI = "market://details?id=";
    private final static String MARKET_VIA_BROWSER_URI = "http://play.google.com/store/apps/details?id=";

    // UI elements
    EditText spIdInput;
    EditText serviceIdInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Build layout, set activity's content view
        setContentView(R.layout.activity_service_delivery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Reference UI views and elements
        spIdInput = (EditText) findViewById(R.id.sp_id_input);
        serviceIdInput = (EditText) findViewById(R.id.service_id_input);
        // Assign default values to SP ID and Service ID
        spIdInput.setText(R.string.example_sp_id);
        serviceIdInput.setText(R.string.default_service_id);

        Button transceiveToCardButton = (Button) findViewById(R.id.card_button);

        // Link UI elements to listeners
        transceiveToCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Pushed the Deliver Service To Card button");
                // call the Fidesmo App's activity to send APDUs to a contactless card
                // but checking first if the Fidesmo App is installed
                if (appInstalledOrNot(FIDESMO_APP)) {
                    callFidesmoApp(SERVICE_DELIVERY_CARD_ACTION, spIdInput.getText().toString(),
                            serviceIdInput.getText().toString());
                } else {
                    notifyMustInstall();
                }
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
            Intent intent = new Intent(action, Uri.parse(SERVICE_URI + spId + "/services/" + serviceId));
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

    // Show a Toast message to the user informing that Fidesmo App must be installed and launch
    // the Google Play app-store
    private void notifyMustInstall() {
        Toast.makeText(getApplicationContext(), R.string.install_app_message, Toast.LENGTH_LONG).show();

        // if the Google Play app is not installed, call the browser
        try {
            startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URI + FIDESMO_APP)), GOOGLE_PLAY_REQUEST_CODE);
        } catch (android.content.ActivityNotFoundException exception) {
            startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_VIA_BROWSER_URI + FIDESMO_APP)), GOOGLE_PLAY_REQUEST_CODE);
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
        } else if (requestCode == GOOGLE_PLAY_REQUEST_CODE) {
            // at this point we do not know whether the user installed the Fidesmo App or not
            // because the Activity is always returning RESULT_OK
            // so we need to check again
            if (appInstalledOrNot(FIDESMO_APP)) {
                callFidesmoApp(SERVICE_DELIVERY_CARD_ACTION, spIdInput.getText().toString(),
                        serviceIdInput.getText().toString());
            } else {
                // if at this point the user has not installed the Fidesmo App, we just give up
                finish();
            }
        }

    }
}
