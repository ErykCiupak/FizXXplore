package eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.CalculatorDensity


import android.annotation.SuppressLint
import eu.example.physicsapp.Menu.Calculators.SharedViewModel

@SuppressLint("DefaultLocale", "RestrictedApi")
fun densityConvert(viewModel: SharedViewModel) {
    @SuppressLint("DefaultLocale")
    viewModel.inputNumber = viewModel.inputNumber.replace(',','.')
    val test2 = viewModel.inputNumber.toDoubleOrNull() ?: ""
    if (viewModel.inputNumber!=""&&test2!=""){

        viewModel.inputNumberDouble = viewModel.inputNumber.toDoubleOrNull() ?: 0.0

        if(viewModel.inputNumberDouble>0.0){
            when (viewModel.selectedUnitExts) {
                "kg/m³" -> viewModel.inputMultiplayer = 1.0
                "g/ml" -> viewModel.inputMultiplayer = 1000.0
                "lb/ft³" -> viewModel.inputMultiplayer = 16.0185
                "lb/in³" -> viewModel.inputMultiplayer = 1000000.0

            }

            viewModel.calcNumber(viewModel::outPutNumberKgm3,1.0,20)

            viewModel.calcNumber(viewModel::outPutNumberGml,0.0001,20)

            viewModel.calcNumber(viewModel::outPutNumberLbft,0.000001,20)

            viewModel.calcNumber(viewModel::outPutNumberLbin,27680.5,20)

        }else{
            viewModel.outPutNumberKgm3 = (0.0).toString()
            viewModel.outPutNumberGml = viewModel.outPutNumberKgm3
            viewModel.outPutNumberLbft= viewModel.outPutNumberKgm3
            viewModel.outPutNumberLbin= viewModel.outPutNumberKgm3

        }
    }else{
        viewModel.outPutNumberKgm3 = ""
        viewModel.outPutNumberGml = viewModel.outPutNumberKgm3
        viewModel.outPutNumberLbft= viewModel.outPutNumberKgm3
        viewModel.outPutNumberLbin= viewModel.outPutNumberKgm3

    }
}