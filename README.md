#   Okostelefon és IoT eszközök programozása (mil-geial51d-ml) - Android Studio beadandó

## Beadandó ismertetése

## Főbb project struktúra


### AndroidManifest.XML
Az internet és a Kamera engedélyezéshez módosítanni kellet a fájlt. Ezzel a két sorral engedélyztema funkciókat.
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.INTERNET" />


### build.gradle konfiguráció
Kikellett egészíteni sorral
#### com.squareup.retrofit2:retrofit:2.6.0: 
Retrofit egy könnyű, nyílt forráskódú HTTP kliens Java és Android alkalmazásokhoz. Segít az API-khoz való hálózati kapcsolódás könnyű kezelésében és az adatok sorosításában.
#### com.squareup.retrofit2:converter-gson:2.6.0:
Ez egy Retrofit kiegészítő, amely a Gson könyvtárat használja az adatok konvertálásához JSON formátumból Java objektumokba és fordítva.
#### com.journeyapps:zxing-android-embedded:4.3.0:
Ez a ZXing (Zebra Crossing) könyvtár egy könnyű, nyílt forráskódú vonalkód és QR-kód olvasó Android alkalmazásokhoz. Az "embedded" verzió lehetővé teszi a ZXing kód integrálását a saját alkalmazásba.
#### junit:junit:4.13.2:
Ez a JUnit keretrendszer, amely segíti a Java alkalmazások egységtesztelését.
#### org.mockito:mockito-core:3.0.0:
A Mockito egy Java keretrendszer, amely lehetővé teszi a fiktív (mock) objektumok készítését egységtesztek során.
#### androidx.test:core:1.5.0 és androidx.test.ext:junit:1.1.5:
Az AndroidX Test komponensek lehetővé teszik az Android alkalmazások tesztelését, beleértve az egységteszteket és az instrumentált teszteket.
#### androidx.test.espresso:espresso-core:3.5.1:
Ez az Espresso keretrendszer, amely segíti az Android alkalmazások UI tesztelését.
#### org.robolectric:robolectric:4.7.3:
A Robolectric egy keretrendszer, amely lehetővé teszi az Android alkalmazások tesztelését az Android futtatókörnyezet nélkül, a JVM-en belül. Ez lehetővé teszi az Android specifikus tesztek könnyebb és gyorsabb végrehajtását.

## Főbb project struktúra

