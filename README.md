Android-Transceive-Demo
=======================

This repository contains a simple example client showing how to use Fidesmo Service capabilities.
In order to test it:

1. Install the [Fidesmo App](https://play.google.com/store/apps/details?id=com.fidesmo.sec.android) in an NFC-enabled Android phone.
2. Build this Transceive Demo (see instructions below) and install it in the same phone.
3. Use [Fidesmo Transceive API](https://fidesmo.3scale.net/api) to prepare a set of Command APDUs for downloading. If you haven't done so yet, you can sign up to participate in our Beta at https://fidesmo.3scale.net/.
4. Launch the Transceive Demo and type the UUID returned by [Fidesmo Transceive API](https://fidesmo.3scale.net/docs)
5. Depending on the type of [Secure Element](http://www.globalplatform.org/mediaguideSE.asp) you are working with, push one of the two available buttons. The Command APDUs you entered in step 3 will be downloaded to the Secure Element!

Build instructions
------------------
- Clone this repository
- In Eclipse (with the [ADT plugin](http://developer.android.com/tools/sdk/eclipse-adt.html)), select menu *File -> Import*
- In the dialogue box, select *General -> Existing Projects into Workspace*
- Click *Next*
- As root directory, select the one where you have cloned this repository

Screenshot
---------
![Screenshot showing the two buttons and an example UUID](/docs/Example_screenshot.png "Screenshot showing the two buttons and an example UUID")


