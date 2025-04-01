package eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.EnergyCalculator



import android.annotation.SuppressLint
import eu.example.physicsapp.Menu.Calculators.SharedViewModel

@SuppressLint("DefaultLocale")
fun energyConvert(viewModel: SharedViewModel) {
    @SuppressLint("DefaultLocale")
    viewModel.inputNumber = viewModel.inputNumber.replace(',','.')
    val test2 = viewModel.inputNumber.toDoubleOrNull() ?: ""
    if (viewModel.inputNumber!=""&&test2!="") {

        viewModel.inputNumberDouble = viewModel.inputNumber.toDoubleOrNull() ?: 0.0

        if(viewModel.inputNumberDouble>0.0){
            when (viewModel.selectedUnitExts) {
                "J" -> viewModel.inputMultiplayer = 1.0
                "kJ" -> viewModel.inputMultiplayer = 1000.0
                "Wh" -> viewModel.inputMultiplayer = 3600.0
                "kWh" -> viewModel.inputMultiplayer = 3600000.0
                "cal" -> viewModel.inputMultiplayer = 4.184
                "kcal" -> viewModel.inputMultiplayer =  4184.0
                "eV" -> viewModel.inputMultiplayer = 0.0000000000000000001602
                "BTU" -> viewModel.inputMultiplayer = 1055.06

            }

            viewModel.calcNumber(viewModel::outPutNumberJ,1.0,30)

            viewModel.calcNumber(viewModel::outPutNumberKJ,1000.0,30)

            viewModel.calcNumber(viewModel::outPutNumberWh,3600.0,30)

            viewModel.calcNumber(viewModel::outPutNumberKWh,3600000.0,30)

            viewModel.calcNumber(viewModel::outPutNumberCal,4.184,30)

            viewModel.calcNumber(viewModel::outPutNumberKcal,4184.0,30)

            viewModel.calcNumber(viewModel::outPutNumberEV,0.0000000000000000001602,50)

            viewModel.calcNumber(viewModel::outPutNumberBTU,1055.06,30)


        }else{
            viewModel.outPutNumberJ = (0.0).toString()
            viewModel.outPutNumberKJ = viewModel.outPutNumberJ
            viewModel.outPutNumberWh= viewModel.outPutNumberJ
            viewModel.outPutNumberKWh= viewModel.outPutNumberJ
            viewModel.outPutNumberCal= viewModel.outPutNumberJ
            viewModel.outPutNumberKcal= viewModel.outPutNumberJ
            viewModel.outPutNumberEV= viewModel.outPutNumberJ
            viewModel.outPutNumberBTU= viewModel.outPutNumberJ

        }
    }else{
        viewModel.outPutNumberJ = ""
        viewModel.outPutNumberKJ = viewModel.outPutNumberJ
        viewModel.outPutNumberWh= viewModel.outPutNumberJ
        viewModel.outPutNumberKWh= viewModel.outPutNumberJ
        viewModel.outPutNumberCal= viewModel.outPutNumberJ
        viewModel.outPutNumberKcal= viewModel.outPutNumberJ
        viewModel.outPutNumberEV= viewModel.outPutNumberJ
        viewModel.outPutNumberBTU= viewModel.outPutNumberJ

    }

}