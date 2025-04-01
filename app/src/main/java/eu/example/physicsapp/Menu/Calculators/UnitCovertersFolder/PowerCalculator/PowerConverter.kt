package eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.PowerCalculator

import android.annotation.SuppressLint
import eu.example.physicsapp.Menu.Calculators.SharedViewModel

@SuppressLint("DefaultLocale")
fun powerConvert(viewModel: SharedViewModel) {
    @SuppressLint("DefaultLocale")
    viewModel.inputNumber = viewModel.inputNumber.replace(',','.')
    val test2 = viewModel.inputNumber.toDoubleOrNull() ?: ""
    if (viewModel.inputNumber!=""&&test2!="") {

        viewModel.inputNumberDouble = viewModel.inputNumber.toDoubleOrNull() ?: 0.0

    if(viewModel.inputNumberDouble>0.0){
        when (viewModel.selectedUnitExts) {
            "W" -> viewModel.inputMultiplayer = 1.0
            "mW" -> viewModel.inputMultiplayer = 0.001
            "kW" -> viewModel.inputMultiplayer = 1000.0
            "MW" -> viewModel.inputMultiplayer = 1000000.0
            "KM" -> viewModel.inputMultiplayer = 735.5
            "HP" -> viewModel.inputMultiplayer = 745.7
            "kcal/h" -> viewModel.inputMultiplayer = 1.162

        }

        viewModel.calcNumber(viewModel::outPutNumberW,1.0,20)

        viewModel.calcNumber(viewModel::outPutNumberWm,0.001,20)

        viewModel.calcNumber(viewModel::outPutNumberWk,1000.0,20)

        viewModel.calcNumber(viewModel::outPutNumberWM,1000000.0,20)

        viewModel.calcNumber(viewModel::outPutNumberKM,735.5,20)

        viewModel.calcNumber(viewModel::outPutNumberHP,745.7,20)

        viewModel.calcNumber(viewModel::outPutNumberKcalH,1.162,20)


    }else{
        viewModel.outPutNumberW = (0.0).toString()
        viewModel.outPutNumberWm = viewModel.outPutNumberW
        viewModel.outPutNumberWk= viewModel.outPutNumberW
        viewModel.outPutNumberWM= viewModel.outPutNumberW
        viewModel.outPutNumberKM= viewModel.outPutNumberW
        viewModel.outPutNumberHP= viewModel.outPutNumberW
        viewModel.outPutNumberKcalH= viewModel.outPutNumberW

    }
        }else{
        viewModel.outPutNumberW = ""
        viewModel.outPutNumberWm = viewModel.outPutNumberW
        viewModel.outPutNumberWk= viewModel.outPutNumberW
        viewModel.outPutNumberWM= viewModel.outPutNumberW
        viewModel.outPutNumberKM= viewModel.outPutNumberW
        viewModel.outPutNumberHP= viewModel.outPutNumberW
        viewModel.outPutNumberKcalH= viewModel.outPutNumberW

    }

}