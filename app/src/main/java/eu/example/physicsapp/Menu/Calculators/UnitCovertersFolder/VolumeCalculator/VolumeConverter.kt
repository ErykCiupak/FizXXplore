package eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.VolumeCalculator

import android.annotation.SuppressLint
import eu.example.physicsapp.Menu.Calculators.SharedViewModel
@SuppressLint("DefaultLocale")
fun volumeConverter(viewModel: SharedViewModel) {
    @SuppressLint("DefaultLocale")
    viewModel.inputNumber = viewModel.inputNumber.replace(',','.')
    val test2 = viewModel.inputNumber.toDoubleOrNull() ?: ""
    if (viewModel.inputNumber!=""&&test2!=""){

        viewModel.inputNumberDouble = viewModel.inputNumber.toDoubleOrNull() ?: 0.0

        if(viewModel.inputNumberDouble>0.0){
            when (viewModel.selectedUnitExts) {
                "m³" -> viewModel.inputMultiplayer = 1.0
                "L" -> viewModel.inputMultiplayer = 0.001
                "bbl" -> viewModel.inputMultiplayer = 0.158987
                "cm³" -> viewModel.inputMultiplayer = 0.000001
                "ft³" -> viewModel.inputMultiplayer = 0.0283168
                "gal GB" -> viewModel.inputMultiplayer = 0.00454609
                "gal US" -> viewModel.inputMultiplayer = 0.00378541
                "in³" -> viewModel.inputMultiplayer = 0.000016387064

            }


            viewModel.calcNumber(viewModel::outPutNumberM3,1.0,20)

            viewModel.calcNumber(viewModel::outPutNumberL,0.001,20)

            viewModel.calcNumber(viewModel::outPutNumberBbl,0.158987,20)

            viewModel.calcNumber(viewModel::outPutNumberCm3,0.000001,20)

            viewModel.calcNumber(viewModel::outPutNumberFt3,0.0283168,20)

            viewModel.calcNumber(viewModel::outPutNumberGalB,0.00454609,20)

            viewModel.calcNumber(viewModel::outPutNumberGalU,0.00378541,20)

            viewModel.calcNumber(viewModel::outPutNumberIn3,0.000016387064,20)


        }else{
            viewModel.outPutNumberM3 = (0.0).toString()
            viewModel.outPutNumberL = viewModel.outPutNumberM3
            viewModel.outPutNumberBbl= viewModel.outPutNumberM3
            viewModel.outPutNumberCm3= viewModel.outPutNumberM3
            viewModel.outPutNumberFt3= viewModel.outPutNumberM3
            viewModel.outPutNumberGalB= viewModel.outPutNumberM3
            viewModel.outPutNumberGalU= viewModel.outPutNumberM3
            viewModel.outPutNumberIn3= viewModel.outPutNumberM3


        }
    }else{
        viewModel.outPutNumberM3 = ""
        viewModel.outPutNumberL = viewModel.outPutNumberM3
        viewModel.outPutNumberBbl= viewModel.outPutNumberM3
        viewModel.outPutNumberCm3= viewModel.outPutNumberM3
        viewModel.outPutNumberFt3= viewModel.outPutNumberM3
        viewModel.outPutNumberGalB= viewModel.outPutNumberM3
        viewModel.outPutNumberGalU= viewModel.outPutNumberM3
        viewModel.outPutNumberIn3= viewModel.outPutNumberM3

    }



}