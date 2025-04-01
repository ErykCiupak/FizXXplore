package eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.MassCalculator


import android.annotation.SuppressLint
import eu.example.physicsapp.Menu.Calculators.SharedViewModel

@SuppressLint("DefaultLocale", "RestrictedApi")
fun massConvert(viewModel: SharedViewModel) {
    @SuppressLint("DefaultLocale")
    viewModel.inputNumber = viewModel.inputNumber.replace(',','.')
    val test2 = viewModel.inputNumber.toDoubleOrNull() ?: ""
    if (viewModel.inputNumber!=""&&test2!=""){

        viewModel.inputNumberDouble = viewModel.inputNumber.toDoubleOrNull() ?: 0.0

        if(viewModel.inputNumberDouble>0.0){
            when (viewModel.selectedUnitExts) {
                "kg" -> viewModel.inputMultiplayer = 1.0
                "g" -> viewModel.inputMultiplayer = 0.001
                "lb" -> viewModel.inputMultiplayer = 0.453592
                "oz" -> viewModel.inputMultiplayer = 0.0283495
                "t" -> viewModel.inputMultiplayer = 1000.0
                "t GB" -> viewModel.inputMultiplayer = 1016.05
                "t US" -> viewModel.inputMultiplayer = 907.184

            }

            viewModel.calcNumber(viewModel::outPutNumberKg,1.0,20)

            viewModel.calcNumber(viewModel::outPutNumberG,0.001,20)

            viewModel.calcNumber(viewModel::outPutNumberLb,0.453592,20)

            viewModel.calcNumber(viewModel::outPutNumberOz,0.0283495,20)

            viewModel.calcNumber(viewModel::outPutNumberT,1000.0,20)

            viewModel.calcNumber(viewModel::outPutNumberTGB,1016.05,20)

            viewModel.calcNumber(viewModel::outPutNumberTUS,907.184,20)




        }else{
            viewModel.outPutNumberKg = (0.0).toString()
            viewModel.outPutNumberG = viewModel.outPutNumberKg
            viewModel.outPutNumberLb= viewModel.outPutNumberKg
            viewModel.outPutNumberOz= viewModel.outPutNumberKg
            viewModel.outPutNumberT= viewModel.outPutNumberKg
            viewModel.outPutNumberTGB= viewModel.outPutNumberKg
            viewModel.outPutNumberTUS= viewModel.outPutNumberKg

        }
    }else{
        viewModel.outPutNumberKg = ""
        viewModel.outPutNumberG = viewModel.outPutNumberKg
        viewModel.outPutNumberLb= viewModel.outPutNumberKg
        viewModel.outPutNumberOz= viewModel.outPutNumberKg
        viewModel.outPutNumberT= viewModel.outPutNumberKg
        viewModel.outPutNumberTGB= viewModel.outPutNumberKg
        viewModel.outPutNumberTUS= viewModel.outPutNumberKg

    }
}