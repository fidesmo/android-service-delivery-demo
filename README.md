Android-Service-Delivery-Demo
=======================

This repository contains a simple example client showing how to use Fidesmo Service capabilities.
In order to test them, please follow these steps:

1. Install the [Fidesmo App](https://play.google.com/store/apps/details?id=com.fidesmo.sec.android) in an NFC-enabled Android phone.
2. Build this Service Delivery Demo (see instructions below) and install it in the same phone.
3. You can test the Service Delivery API just by using the default values that appear pre-filled in the fields. They point to an example Service Provider with some available services, for example ``transceive``, ``install``, ``mifare`` and ``mifare-pay``. Its commented source code [is stored here](https://github.com/fidesmo/spray-example).
4. Push the button to launch the Fidesmo App and deliver the service to a card.


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
![Screenshot of the Service Delivery API screen, showing the default service parameters](/docs/ServiceDelivery_screenshot.png "Screenshot of the Service Delivery API screen, showing the two buttons and the default service parameters")
