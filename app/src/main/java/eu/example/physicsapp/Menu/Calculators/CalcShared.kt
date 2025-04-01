package eu.example.physicsapp.Menu.Calculators

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.reflect.KMutableProperty0

class SharedViewModel : ViewModel() {

    /*********************************
                Zmienne
     *********************************/
    var isSheetOpen by  mutableStateOf(false)
    var isSheetOpen2 by  mutableStateOf(false)
    var selectedUnitName by mutableStateOf("Wybierz jednostke")
    var selectedUnitExts by mutableStateOf("")
    var inputNumber by  mutableStateOf("")
    var inputMultiplayer by  mutableDoubleStateOf(0.0)
    var inputNumberDouble:Double = 0.0
//converterLenghts
    var outPutNumberM by mutableStateOf("0.0")
    var outPutNumberMm by mutableStateOf("0.0")
    var outPutNumberCm by mutableStateOf("0.0")
    var outPutNumberKm by mutableStateOf("0.0")
    var outPutNumberIn by mutableStateOf("0.0")
    var outPutNumberFt by mutableStateOf("0.0")
    var outPutNumberYd by mutableStateOf("0.00")
    var outPutNumberMi by mutableStateOf("0.0")
    var outPutNumberNmi by mutableStateOf("0.0")
//converterArea
    var outPutNumberM2 by mutableStateOf("0.0")
    var outPutNumberCm2 by mutableStateOf("0.0")
    var outPutNumberMm2 by mutableStateOf("0.0")
    var outPutNumberKm2 by mutableStateOf("0.0")
    var outPutNumberAr by mutableStateOf("0.0")
    var outPutNumberHa by mutableStateOf("0.0")
    var outPutNumberIn2 by mutableStateOf("0.00")
    var outPutNumberFt2 by mutableStateOf("0.0")
    var outPutNumberYd2 by mutableStateOf("0.0")
    var outPutNumberMi2 by mutableStateOf("0.0")
    var outPutNumberAc by mutableStateOf("0.0")
    //converterVolume
    var outPutNumberM3 by mutableStateOf("0.0")
    var outPutNumberL by mutableStateOf("0.0")
    var outPutNumberBbl by mutableStateOf("0.0")
    var outPutNumberCm3 by mutableStateOf("0.0")
    var outPutNumberFt3 by mutableStateOf("0.0")
    var outPutNumberGalB by mutableStateOf("0.0")
    var outPutNumberGalU by mutableStateOf("0.00")
    var outPutNumberIn3 by mutableStateOf("0.0")
    //converterPower
    var outPutNumberW by mutableStateOf("0.0")
    var outPutNumberWm by mutableStateOf("0.0")
    var outPutNumberWk by mutableStateOf("0.0")
    var outPutNumberWM by mutableStateOf("0.0")
    var outPutNumberKM by mutableStateOf("0.0")
    var outPutNumberHP by mutableStateOf("0.0")
    var outPutNumberKcalH by mutableStateOf("0.0")
    //converterEnergy
    var outPutNumberJ by mutableStateOf("0.0")
    var outPutNumberKJ by mutableStateOf("0.0")
    var outPutNumberWh by mutableStateOf("0.0")
    var outPutNumberKWh by mutableStateOf("0.0")
    var outPutNumberCal by mutableStateOf("0.0")
    var outPutNumberKcal by mutableStateOf("0.0")
    var outPutNumberEV by mutableStateOf("0.0")
    var outPutNumberBTU by mutableStateOf("0.0")
    //converterMass
    var outPutNumberKg by mutableStateOf("0.0")
    var outPutNumberG by mutableStateOf("0.0")
    var outPutNumberLb by mutableStateOf("0.0")
    var outPutNumberOz by mutableStateOf("0.0")
    var outPutNumberT by mutableStateOf("0.0")
    var outPutNumberTGB by mutableStateOf("0.0")
    var outPutNumberTUS by mutableStateOf("0.0")
    //converterDensity
    var outPutNumberKgm3 by mutableStateOf("0.0")
    var outPutNumberGml by mutableStateOf("0.0")
    var outPutNumberLbft by mutableStateOf("0.0")
    var outPutNumberLbin by mutableStateOf("0.0")
    //WireConCalculator


        /*********************************
        Funkcje
         *********************************/

        fun updateIsSheetOpen(booleanStatus: Boolean) {
            isSheetOpen = booleanStatus

        }

        fun updateIsSheetOpen2(booleanStatus: Boolean) {
            isSheetOpen2 = booleanStatus

        }

        fun updateSelectedUnitName(text: String) {
            selectedUnitName = text

        }

        fun upadateSelectedUnitExts(text: String) {
            selectedUnitExts = text

        }

        //Obliczanie wyników do outputów w kalkulatorach
        @SuppressLint("DefaultLocale")
        fun calcNumber(property: KMutableProperty0<String>, dzielnik: Double, scala: Int) {
            fun roundNumber(value: Double, scale: Int): Double {
                return BigDecimal(value).setScale(scale, RoundingMode.HALF_UP).toDouble()
            }

            val number = roundNumber(
                (((inputNumberDouble * inputMultiplayer * 100.0) / dzielnik) / 100.0),
                scala
            )

//            when {
//                number == 0.0 -> property.set("0") // Obsługa wartości zerowej
//                number >= 1 -> property.set(DecimalFormat("#.##").format(number)) // Formatowanie dla >= 1
//                else -> property.set(number.toString()) // Pozostawienie oryginalnej liczby dla < 1
//            }
            property.set(formatDistance(number))
        }


}
fun formatDistance(value: Double): String {
    return when {
        kotlin.math.abs(value) >= 1e6 -> "%.2e".format(value) // Notacja naukowa dla bardzo dużych liczb
        kotlin.math.abs(value) < 1e-3 -> "%.2e".format(value) // Notacja naukowa dla liczb mniejszych niż 1e-3, z dokładnością do 2 miejsc
        kotlin.math.abs(value) < 1 -> {
            // Jeśli liczba mniejsza niż 1 i ma więcej niż 4 miejsca po przecinku, użyj notacji naukowej
            val decimalPlaces = value.toString().substringAfterLast('.').length
            if (decimalPlaces > 4) {
                "%.2e".format(value) // Notacja naukowa dla wartości z więcej niż 4 miejscami po przecinku
            } else {
                "%.15f".format(value).trimEnd('0').trimEnd('.') // Trzy miejsca po przecinku dla liczb < 1
            }
        }
        else -> "%.2f".format(value) // Standardowe dwie liczby po przecinku
    }
}





