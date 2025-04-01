package eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.AreaCalculator

import android.annotation.SuppressLint
import eu.example.physicsapp.Menu.Calculators.SharedViewModel

@SuppressLint("DefaultLocale", "RestrictedApi")
fun areaConvert(viewModel: SharedViewModel) {
    @SuppressLint("DefaultLocale")
    viewModel.inputNumber = viewModel.inputNumber.replace(',','.')
    val test2 = viewModel.inputNumber.toDoubleOrNull() ?: ""
    if (viewModel.inputNumber!=""&&test2!=""){

        viewModel.inputNumberDouble = viewModel.inputNumber.toDoubleOrNull() ?: 0.0

        if(viewModel.inputNumberDouble>0.0){
            when (viewModel.selectedUnitExts) {
                "m²" -> viewModel.inputMultiplayer = 1.0
                "cm²" -> viewModel.inputMultiplayer = 0.0001
                "mm²" -> viewModel.inputMultiplayer = 0.000001
                "km²" -> viewModel.inputMultiplayer = 1000000.0
                "ar" -> viewModel.inputMultiplayer = 100.0
                "ha" -> viewModel.inputMultiplayer = 10000.0
                "in²" -> viewModel.inputMultiplayer = 0.00064516
                "ft²" -> viewModel.inputMultiplayer = 0.092903
                "yd²" -> viewModel.inputMultiplayer = 0.836127
                "mi²" -> viewModel.inputMultiplayer = 2589988.0
                "ac" -> viewModel.inputMultiplayer = 4046.86
            }

            viewModel.calcNumber(viewModel::outPutNumberM2,1.0,20)

            viewModel.calcNumber(viewModel::outPutNumberCm2,0.0001,20)

            viewModel.calcNumber(viewModel::outPutNumberMm2,0.000001,20)

            viewModel.calcNumber(viewModel::outPutNumberKm2,1000000.0,20)

            viewModel.calcNumber(viewModel::outPutNumberAr,100.0,20)

            viewModel.calcNumber(viewModel::outPutNumberHa,10000.0,20)

            viewModel.calcNumber(viewModel::outPutNumberIn2,0.00064516,20)

            viewModel.calcNumber(viewModel::outPutNumberFt2,0.092903,20)

            viewModel.calcNumber(viewModel::outPutNumberYd2,0.836127,20)

            viewModel.calcNumber(viewModel::outPutNumberMi2,2589988.0,20)

            viewModel.calcNumber(viewModel::outPutNumberAc,4046.86,20)

        }else{
            viewModel.outPutNumberM2 = (0.0).toString()
            viewModel.outPutNumberCm2 = viewModel.outPutNumberM2
            viewModel.outPutNumberMm2= viewModel.outPutNumberM2
            viewModel.outPutNumberKm2= viewModel.outPutNumberM2
            viewModel.outPutNumberAr= viewModel.outPutNumberM2
            viewModel.outPutNumberHa= viewModel.outPutNumberM2
            viewModel.outPutNumberIn2= viewModel.outPutNumberM2
            viewModel.outPutNumberFt2= viewModel.outPutNumberM2
            viewModel.outPutNumberYd2= viewModel.outPutNumberM2
            viewModel.outPutNumberMi2= viewModel.outPutNumberM2
            viewModel.outPutNumberAc= viewModel.outPutNumberM2
        }
    }else{
        viewModel.outPutNumberM2 = ""
        viewModel.outPutNumberCm2 = viewModel.outPutNumberM2
        viewModel.outPutNumberMm2= viewModel.outPutNumberM2
        viewModel.outPutNumberKm2= viewModel.outPutNumberM2
        viewModel.outPutNumberAr= viewModel.outPutNumberM2
        viewModel.outPutNumberHa= viewModel.outPutNumberM2
        viewModel.outPutNumberIn2= viewModel.outPutNumberM2
        viewModel.outPutNumberFt2= viewModel.outPutNumberM2
        viewModel.outPutNumberYd2= viewModel.outPutNumberM2
        viewModel.outPutNumberMi2= viewModel.outPutNumberM2
        viewModel.outPutNumberAc= viewModel.outPutNumberM2
    }
}