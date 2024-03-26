#   Okostelefon és IoT eszközök programozása (mil-geial51d-ml) - Android Studio beadandó

## Beadandó ismertetése
A beadandó feladatam egy adatbázis kezelő rendszer része. Az adatbázis célja egy fejlesztési csapat fejlesztői mintániak nyomonkövetése. Mintákat egy QR kód alapján lehet azonosítani az adatbázisban.
A mintához több információ elérhető , össze szerelés , fázis , hogy melyik projekthez tartozik stb.
A program műküdése szempontjából a Lokáció a fontos ez a plocon megtalálható QR kód. Ez alapján lehet módosítani a minta fizikai elhelyezkedését. Ez egy hasznos funkció mert Desktop kliensben később könnyebb beazonosítani a minta elhelyezkedését.
A Mobil Kliens egy általam készített Spring Boot Web szerverre csatlakozik. A Wep szerver egy MYSQL adatbázist kezel.

## AndroidManifest.XML
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

## Activity
### BaseActivity
Az BaseActivity egy absztrakt osztály, amely az AppCompatActivity-tól származik. Ez az osztály szolgál alapul más tevékenységek számára az alkalmazásban, biztosítva közös funkciókat, mint például a toast üzenetek megjelenítése és az aktivitások közötti navigáció. Az osztály inicializálja és hozzáférést biztosít az AuthToken és ScanResult példányokhoz, amelyek az alkalmazáson belül közös használatra szolgálnak.
Az openLocationActivity(), openScanActivity() és openinfoScanActivity() metódusok az egyes tevékenységek közötti navigációért felelősek, biztosítva, hogy az aktuális tevékenység ne legyen elérhető a visszacsatolás segítségével.

### LoginActivity
Az LoginActivity felhasználói felületen lehetővé teszi a felhasználónak a felhasználónevének és jelszavának megadását, majd egy hálózati kérés segítségével ellenőrzi ezeket az adatokat. A hálózati kéréshez a Retrofit keretrendszert használja, és az adatok ellenőrzése a checkLogin API végpont segítségével történik. A válasz alapján a felhasználó sikeres vagy sikertelen bejelentkezéséről kap visszajelzést.
Az osztály a felhasználói felületen elhelyezett ProgressBar segítségével jelzi a bejelentkezés folyamatát, és figyeli a felhasználó által megadott felhasználónév és jelszó mezők változásait a TextWatcher interfész segítségével, hogy csak akkor engedélyezze a bejelentkezés gombot, ha mindkét mező kitöltve van.

### ScanActivity
Az ScanActivity felhasználói felületen lehetővé teszi a felhasználónak a QR-kódok szkennelését egy gomb megnyomásával. A szkennelési műveletet a ScanContract és a ScanOptions segítségével konfigurálja és kezeli. 
Miután a QR-kódot sikerült beolvasni, az eredményt ellenőrzi, és ha az egy numerikus érték, azt átadja az alkalmazás egy másik részének, hogy további feldolgozásra kerüljön. Ha a beolvasott QR-kód nem megfelelő formátumú, egy hibaüzenetet jelenít meg, majd visszanavigál a felhasználót az információk megjelenítését kezelő tevékenységhez.

### InfoActivity
Az InfoActivity osztály egy olyan Android alkalmazásban található tevékenység, amely felelős egy minta részletes információinak megjelenítéséért. Az osztály az BaseActivity osztályból származik, ami biztosítja az alapvető funkcionalitásokat, mint például a Toast üzenetek megjelenítése és az aktivitások közötti navigáció.
Az InfoActivity felhasználói felületen különböző TextView-k segítségével jeleníti meg a minta részletes adatait, mint például a minta azonosítója, kódja, fázisa, elhelyezkedése, projekt neve és összeszerelés azonosítója. Ezeket az adatokat egy távoli szerverről kéri le Retrofit segítségével, és a választól függően frissíti az UI-t. Ha a minta nem található, vagy hiba történik a válaszban, a felhasználót visszanavigálja a QR-kódok szkennelését kezelő tevékenységhez.

### LocationActivity
Az LocationActivity felhasználói felületen lehetővé teszi a felhasználónak egy polc sorszámának a beolvasásra egy gomb megnyomásával. A szkennelési műveletet a ScanContract és a ScanOptions segítségével konfigurálja és kezeli. A beolvasott QR-kódok tartalmától függően frissíti a jelenlegi minta példányában tárolt helyszínt, majd megkísérli frissíteni a minta helyszínét a szerveren.
Ha a beolvasott QR-kód nem megfelelő formátumú, egy hibaüzenetet jelenít meg, majd visszanavigál a felhasználót az információk megjelenítését kezelő tevékenységhez. Ha a szerverrel történő kommunikáció során hiba történik, naplózza és értesíti a felhasználót az update 

## Interfaces
### InterfaceAPI
Az InterfaceAPI egy interfész, amely az alkalmazás által használt API végpontokat definiálja a Retrofit keretrendszerrel történő kommunikációhoz. Az interfészben különböző HTTP kérések szerepelnek, amelyek lehetővé teszik az autentikáció ellenőrzését, mint például a bejelentkezési adatok ellenőrzése, a minták lekérdezését és frissítését a szerveren.

## Model
### SampleComposite
A SampleComposite osztály egy kompozit objektumot reprezentál egy mintaelemhez, ami különböző részleteket tartalmaz a mintáról, mint például az azonosítója, kódja, fázisa, elhelyezkedése, projekt neve és összeszerelés azonosítója. Ez az osztály általában adatokat tárol, amelyeket egy adatbázis JOIN lekérdezéséből nyer ki, több táblából összegyűjtve az információkat egyetlen objektumba.

## AuthToken
Az AuthToken osztály egyetlen példányának menedzseléséért felelős singleton osztály. Ez az osztály tartalmazza az alapvető hitelesítési tokenek létrehozásának logikáját egy felhasználónév és jelszó alapján. A singleton minta alkalmazása biztosítja, hogy az alkalmazás minden részén csak egyetlen példányt használunk ebből a token kezelőből.

## RetrofitClientInstance
A RetrofitClientInstance osztály egyetlen példányának létrehozását és kezelését végzi, figyelembe véve a Retrofit példányának konfigurálását. Ez az osztály egy központi Retrofit klienst biztosít HTTP kérések végrehajtásához, ami garantálja, hogy az alkalmazás teljes egészében csak egyetlen Retrofit példányt használjon. A Retrofit-et konfigurálja az API alap URL-jével és egy Gson konverterrel a JSON adatok sorosításához és deszerializálásához.

## ScanResult
A ScanResult osztály egyetlen példányának létrehozását és kezelését végzi, amely a beolvasás eredményét tartalmazza és kezeli. Ez az osztály összefoglalja a beolvasott minta részleteit és az ehhez tartozó kódot, biztosítva, hogy az alkalmazás teljes egészében csak egyetlen beolvasási eredmény példány legyen aktív bármely adott időpontban. Ezáltal központosított hozzáférést biztosít a beolvasási adatokhoz.

## UnsafeOkHttpClient
Az UnsafeOkHttpClient osztály egy olyan segédosztály, amely egy "biztonsági kiskaput" nyit meg az alkalmazás számára az SSL/TLS tanúsítványok ellenőrzése alól. Ennek eredményeként az alkalmazás lehetővé teszi az összes HTTPS kérést, anélkül hogy ellenőrizné a szerver tanúsítványait. Ez kifejezetten csak fejlesztési fázis alatt szabad.



