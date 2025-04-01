package eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.LenghtCalculator

import android.annotation.SuppressLint
import eu.example.physicsapp.Menu.Calculators.SharedViewModel

@SuppressLint("DefaultLocale")
fun lengthConvert(viewModel: SharedViewModel) {
    @SuppressLint("DefaultLocale")
    viewModel.inputNumber = viewModel.inputNumber.replace(',','.')
    val test2 = viewModel.inputNumber.toDoubleOrNull() ?: ""
    if (viewModel.inputNumber!=""&&test2!="") {

        viewModel.inputNumberDouble = viewModel.inputNumber.toDoubleOrNull() ?: 0.0

        if (viewModel.inputNumberDouble > 0.0) {
            when (viewModel.selectedUnitExts) {
                "m" -> viewModel.inputMultiplayer = 1.0
                "mm" -> viewModel.inputMultiplayer = 0.001
                "cm" -> viewModel.inputMultiplayer = 0.01
                "km" -> viewModel.inputMultiplayer = 1000.0
                "in" -> viewModel.inputMultiplayer = 0.0254
                "ft" -> viewModel.inputMultiplayer = 0.3048
                "yd" -> viewModel.inputMultiplayer = 0.9144
                "mi" -> viewModel.inputMultiplayer = 1609.34
                "nmi" -> viewModel.inputMultiplayer = 1852.00
            }

            viewModel.calcNumber(viewModel::outPutNumberM,1.0,20)

            viewModel.calcNumber(viewModel::outPutNumberMm,0.001,20)

            viewModel.calcNumber(viewModel::outPutNumberCm,0.01,20)

            viewModel.calcNumber(viewModel::outPutNumberKm,1000.0,20)

            viewModel.calcNumber(viewModel::outPutNumberIn,0.0254,20)

            viewModel.calcNumber(viewModel::outPutNumberFt,0.3048,20)

            viewModel.calcNumber(viewModel::outPutNumberYd,0.9144,20)

            viewModel.calcNumber(viewModel::outPutNumberMi,1609.34,20)

            viewModel.calcNumber(viewModel::outPutNumberNmi,1852.00,20)


        } else {
            viewModel.outPutNumberM = (0.0).toString()
            viewModel.outPutNumberMm = viewModel.outPutNumberM
            viewModel.outPutNumberCm = viewModel.outPutNumberM
            viewModel.outPutNumberKm = viewModel.outPutNumberM
            viewModel.outPutNumberIn = viewModel.outPutNumberM
            viewModel.outPutNumberFt = viewModel.outPutNumberM
            viewModel.outPutNumberYd = viewModel.outPutNumberM
            viewModel.outPutNumberMi = viewModel.outPutNumberM
            viewModel.outPutNumberNmi = viewModel.outPutNumberM
        }
    }else{
        viewModel.outPutNumberM = ""
        viewModel.outPutNumberMm = viewModel.outPutNumberM
        viewModel.outPutNumberCm= viewModel.outPutNumberM
        viewModel.outPutNumberKm= viewModel.outPutNumberM
        viewModel.outPutNumberIn= viewModel.outPutNumberM
        viewModel.outPutNumberFt= viewModel.outPutNumberM
        viewModel.outPutNumberYd= viewModel.outPutNumberM
        viewModel.outPutNumberMi= viewModel.outPutNumberM
        viewModel.outPutNumberNmi= viewModel.outPutNumberM

    }

}