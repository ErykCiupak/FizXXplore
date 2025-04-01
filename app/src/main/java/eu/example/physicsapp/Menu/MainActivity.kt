package eu.example.physicsapp.Menu


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.example.physicsapp.Menu.Quizy.QuizScreen
import eu.example.physicsapp.Menu.Academy.Academy
import eu.example.physicsapp.Menu.Academy.Battery.AccademyBatterySelect
import eu.example.physicsapp.Menu.Academy.CircutBreaker.CircutBreakerSelect
import eu.example.physicsapp.Menu.Academy.ElectricDevices.AccademyElectricDevicesSelect
import eu.example.physicsapp.Menu.Academy.ElectricElements.ElectricElementsSelect
import eu.example.physicsapp.Menu.Academy.ElectricWiresAcademy.AccademyElectricWiresSelect
import eu.example.physicsapp.Menu.Academy.ElectroTechBasics.ElectroTechBasicsSelect
import eu.example.physicsapp.Menu.Academy.LowVoltage.LowVoltageSelect
import eu.example.physicsapp.Menu.Academy.ShockProtection.ShockProtectionSelect
import eu.example.physicsapp.Menu.Calculators.Basics.BasicsSelect
import eu.example.physicsapp.Menu.Calculators.SharedViewModel
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.AreaCalculator.CalcArea
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.LenghtCalculator.CalcLenght
import eu.example.physicsapp.ui.theme.PhysicsAppTheme
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.VolumeCalculator.CalcVolume
import eu.example.physicsapp.Menu.Calculators.CalculateOption
import eu.example.physicsapp.Menu.Calculators.Basics.ColumbCalculator.ColumbCalculator
import eu.example.physicsapp.Menu.Calculators.Basics.DecodeResistor.DeReristorCalculator
import eu.example.physicsapp.Menu.Calculators.Basics.WireConduction.WireCondCalculator
import eu.example.physicsapp.Menu.Calculators.CircuitsAC.CuACSelect
import eu.example.physicsapp.Menu.Calculators.CircuitsAC.StarTrangle.StarTrangleCalculator
import eu.example.physicsapp.Menu.Calculators.CircuitsDC.CuDCSelect
import eu.example.physicsapp.Menu.Calculators.CircuitsDC.DevideVoltage.DevVolCalculator
import eu.example.physicsapp.Menu.Calculators.CircuitsDC.OhmLaw.OhmCalculator
import eu.example.physicsapp.Menu.Calculators.CircuitsDC.SumResistors.SumResistorCalculator
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.CalculatorDensity.CalcDensity
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.EnergyCalculator.CalcEnergy
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.MassCalculator.CalcMass
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.PowerCalculator.CalcPower
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.UnitConverterOption
import eu.example.physicsapp.Menu.Quizy.QuizSelect
import eu.example.physicsapp.Menu.Quizy.QuizViewModel
import eu.example.physicsapp.Menu.Symbols.Symbols
import eu.example.physicsapp.Menu.Tablice.Battery.BatterySelect
import eu.example.physicsapp.Menu.Tablice.Battery.ElectroChemEquiv.ElectroChemEquivScreen
import eu.example.physicsapp.Menu.Tablice.Battery.MetalTensions.MetalTensionScreen
import eu.example.physicsapp.Menu.Tablice.ElectricDevices.ElectricDevicesSelect
import eu.example.physicsapp.Menu.Tablice.ElectricDevices.ResistorsLinesCode.ResistorsLinesCodeScreen
import eu.example.physicsapp.Menu.Tablice.ElectricalWires.ElectrWireselect
import eu.example.physicsapp.Menu.Tablice.ElectricalWires.Rj45.RjScreen
import eu.example.physicsapp.Menu.Tablice.ElectricalWires.WireColorCoding.WireColorCodingScreen
import eu.example.physicsapp.Menu.Tablice.ElectricalWires.WiresDiameter.WireDiamerScreen
import eu.example.physicsapp.Menu.Tablice.ElectricDevices.IkDefenceSteps.IkDefenceStepsScreen
import eu.example.physicsapp.Menu.Tablice.ElectricDevices.IpDefenceSteps.IpDefenceStepsScreen
import eu.example.physicsapp.Menu.Tablice.PhycisBasic.PhycisBasicSelect
import eu.example.physicsapp.Menu.Tablice.PhycisBasic.Prefixes.Prefixes
import eu.example.physicsapp.Menu.Tablice.PhycisBasic.ResistansOfMetals.MetalsResistans
import eu.example.physicsapp.Menu.Tablice.PhycisBasic.SiUnits.PhycisUnits
import eu.example.physicsapp.Menu.Tablice.TableSelect


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            PhysicsAppTheme {
                Surface(color = MaterialTheme.colorScheme.primary){
                    MyApp(SharedViewModel())
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModels: SharedViewModel){
    val navController = rememberNavController()
    NavHost(navController= navController, startDestination = "startActivity") {
        composable("startActivity") { FirstScreen(navController) }

        //KALKULATORY
            //Przelicznik jednostek
        composable("calcOption") { CalculateOption(navController) }
        composable("unitConverter") { UnitConverterOption(navController) }
        composable("calcLenght") { CalcLenght(navController, viewModels) }
        composable("calcArea") { CalcArea(navController, viewModels) }
        composable("calcVolume") { CalcVolume(navController, viewModels) }
        composable("calcPower") { CalcPower(navController, viewModels) }
        composable("calcEnergy"){ CalcEnergy(navController, viewModels) }
        composable("calcMass"){ CalcMass(navController, viewModels) }
        composable("calcDensity"){ CalcDensity(navController, viewModels) }

            //Kalkulatory podstawy
        composable("basicsSelect"){ BasicsSelect(navController) }
        composable("calcColumb"){ ColumbCalculator(navController) }
        composable("calcWireCondu"){ WireCondCalculator(navController,viewModels) }
        composable("calcDecodeResistor"){ DeReristorCalculator(navController) }

            //Obwody DC
        composable("obwodDCSelect"){ CuDCSelect(navController) }
        composable("calcOhmLaw"){ OhmCalculator(navController) }
        composable("calcVoltDiv"){ DevVolCalculator(navController) }
        composable("calcConnResis"){ SumResistorCalculator(navController) }
        composable("calcCapaChar"){ ColumbCalculator(navController) }
        composable("calcCoilChar"){ ColumbCalculator(navController) }

            //Obwody AC
        composable("obwodACSelect"){ CuACSelect(navController) }
        composable("calcConvertStar"){ StarTrangleCalculator(navController) }

        //QUIZY
        composable("quizMain5") { QuizScreen(viewModel = viewModel<QuizViewModel>(), navController,6) }
        composable("quizMain15") { QuizScreen(viewModel = viewModel<QuizViewModel>(), navController,16) }
        composable("quizMain20") { QuizScreen(viewModel = viewModel<QuizViewModel>(), navController,21) }
        composable("quizSelect") { QuizSelect(navController)}

        //TABLICE
        composable("tableSelect"){TableSelect(navController)}

            //Podstawy fizyki
        composable("phycisBasicSelect"){PhycisBasicSelect(navController)}
        composable("phycisUnits"){ PhycisUnits(navController) }
        composable("prefixes"){ Prefixes(navController) }
        composable("metalsResistans"){ MetalsResistans(navController) }

            //Baterie
        composable("batterySelect"){ BatterySelect(navController) }
        composable("metalTension"){ MetalTensionScreen(navController) }
        composable("electroChemEqui"){ ElectroChemEquivScreen(navController) }

            //Przewody elektryczne
        composable("electroWiresSelect"){ ElectrWireselect(navController) }
        composable("wireColorCodding"){ WireColorCodingScreen(navController) }
        composable("wireDiamCodding"){ WireDiamerScreen(navController) }
        composable("rj45"){ RjScreen(navController) }

            //Urzadzenia elektryczne
        composable("electriDevicesSelect"){ ElectricDevicesSelect(navController) }
        composable("resistorsLinesCode"){ ResistorsLinesCodeScreen(navController) }
        composable("ikDefenceSteps"){ IkDefenceStepsScreen(navController) }
        composable("ipDefenceSteps"){ IpDefenceStepsScreen(navController) }

        //SYMBOLE
        composable("symbols"){ Symbols(navController) }

        //AKADEMIA
        composable ("academy"){ Academy(navController) }
            //Podstawy elektrotechniki
        composable ("electroTechBasics"){ ElectroTechBasicsSelect(navController) }

            //Elementy elektryczne
        composable ("electricElementsBasics"){ ElectricElementsSelect(navController) }
            //Baterie
        composable ("academyBatterySelect"){ AccademyBatterySelect(navController) }
            //Przewody elektryczne
        composable ("academyEleWiresSelect"){ AccademyElectricWiresSelect(navController) }
            //Urządzenia elektryczne
        composable ("academyEleDevicesSelect"){ AccademyElectricDevicesSelect(navController) }
            //Sieci i instalacje niskiego napiecia
        composable ("lowVoltageSelect"){ LowVoltageSelect(navController) }
            //Wyłączniki różnicowoprądowe
        composable ("circutBreakerSelect"){ CircutBreakerSelect(navController) }
            //Ochrona przeciwporażeniowa
        composable ("shockProtectSelect"){ShockProtectionSelect(navController)}
    }
}

