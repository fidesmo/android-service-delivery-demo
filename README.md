Android-Service-Delivery-Demo
=======================

This repository contains a simple example client showing how to use Fidesmo Service capabilities.
The client demonstrates two different APIs.
In order to test them, first follow this common steps:

1. Install the [Fidesmo App](https://play.google.com/store/apps/details?id=com.fidesmo.sec.android) in an NFC-enabled Android phone.
2. Build this Service Delivery Demo (see instructions below) and install it in the same phone.

Then, to test the *Service Delivery API:*

3. Launch the Service Delivery Demo app and press the "Service Delivery API" button.
4. You can test the Service Delivery API just by using the default values that appear pre-filled in the fields. They point to an example Service, its commented source code [is stored here](https://github.com/fidesmo/spray-example).
5. Depending on the type of [Secure Element](http://www.globalplatform.org/mediaguideSE.asp) you are working with, push one of the two available buttons. The Command APDUs sent by the example service will be downloaded to the Secure Element!

In order to test the *Transceive API,* follow this steps:

6. Use [Fidesmo Transceive API](https://developer.fidesmo.com/api) to prepare a set of Command APDUs for downloading. If you haven't done so yet, you can sign up to participate in our Beta at [https://developer.fidesmo.com/](https://developer.fidesmo.com/).
7. Launch the Transceive Demo and press the "Service Delivery API" button. 
8. Type the UUID returned by [Fidesmo Transceive API](https://developer.fidesmo.com/api)
9. Same as with the Service Delivery API push one of the two available buttons to select the Secure Element. The Command APDUs you entered in step 1 will be downloaded to the Secure Element!

Build instructions: command line
------------------
- Clone this repository
- In the project's root directory, type ``./gradlew build``
- To install the app into a connected phone or an emulator, type ``./gradlew installDebug`` or ``adb install build/apk/android-service-delivery-demo-debug-unaligned.apk``

Build instructions: Android Studio IDE
------------------
- Clone this repository
- In Android Studio, go to menu File -> Import Project
- In the dialog box "Select Gradle Project Import", find this project's root directory in your filesystem and click 'OK'

Screenshots
---------
![Screenshot showing the demo app's startup screen](/docs/Initial_screenshot.png "Screenshot showing the demo app's startup screen")
![Screenshot of the Service Delivery API screen, showing the two buttons and the default service parameters](/docs/ServiceDelivery_screenshot.png "Screenshot of the Service Delivery API screen, showing the two buttons and the default service parameters")
![Screenshot of the Transceive API screen, showing the two buttons and an example UUID](/docs/Transceive_screenshot.png "Screenshot of the Transceive API screen, showing the two buttons and an example UUID")
