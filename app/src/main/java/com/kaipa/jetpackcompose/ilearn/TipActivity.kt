package com.kaipa.jetpackcompose.ilearn

import android.R.attr.value
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaipa.jetpackcompose.ilearn.components.AddSpace
import com.kaipa.jetpackcompose.ilearn.components.ButtonWrapper
import com.kaipa.jetpackcompose.ilearn.components.CardWrapper
import com.kaipa.jetpackcompose.ilearn.components.ColumnWrapper
import com.kaipa.jetpackcompose.ilearn.components.OutlinedTextFieldWrapper
import com.kaipa.jetpackcompose.ilearn.components.RowWrapper
import com.kaipa.jetpackcompose.ilearn.components.SliderWrapper
import com.kaipa.jetpackcompose.ilearn.ui.theme.MyApplicationTheme
import com.kaipa.jetpackcompose.ilearn.ui.theme.Pink80

class TipActivity : ComponentActivity() {
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                MainSurface()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainSurface() {
        val enteredBill = remember { mutableStateOf("") }
        val splitPersons = remember { mutableStateOf(1) }
        val sliderState = remember { mutableStateOf(0f) }

        // non hoist vars
        val totalBillAmount = enteredBill.value.let {
            if (it.trim().isNotEmpty()) it.toDouble() else 0.00
        }
        val sliderValue = sliderState.value.let {
            it * 100
        }
        val totalTipAmount = let {
            if (totalBillAmount == 0.00) 0.00 else (totalBillAmount * sliderValue) / 100
        }
        val totalSplitPersons = splitPersons.value

        val totalPerPerson = let {
            (totalBillAmount + totalTipAmount) / totalSplitPersons
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(corner = CornerSize(4.dp)),
            color = Color.White
        ) {
            ColumnWrapper {
                TopAppBar(modifier = Modifier.fillMaxWidth(), title = {
                    Text(text = "Tip App")
                }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Magenta))
                TopHeader(totalPerPerson)
                BillInfo(
                    enteredBill,
                    splitPersons,
                    sliderState,
                    totalSplitPersons,
                    totalTipAmount,
                    sliderValue
                )
            }
        }
    }

    @Composable
    fun TopHeader(totalPerPerson: Double = 0.0) {
        val totalPerPerson = "%.2f".format(totalPerPerson)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(32.dp),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            color = Pink80
        ) {
            ColumnWrapper(Modifier, orientation = Arrangement.Center) {
                Text(text = "Total Per Person", style = MaterialTheme.typography.headlineSmall)
                AddSpace()
                Text(
                    text = "$$totalPerPerson",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }

    @Composable
    fun BillInfo(
        enteredBill: MutableState<String>,
        splitPersons: MutableState<Int>,
        sliderState: MutableState<Float>,
        defaultSplitPersons: Int,
        tipAmount: Double,
        sliderValue: Float
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val isValidState = enteredBill.let { if (it.value.trim().isNotEmpty()) true else false }
        val tipAmount = tipAmount.let {
            "%.0f".format(it)
        }

        CardWrapper {
            ColumnWrapper {
                OutlinedTextFieldWrapper(
                    enteredBill,
                    label = "Enter Bill",
                    placeholder = "Bill Amount",
                    startIcon = Icons.Default.AttachMoney,
                    actions = KeyboardActions {
                        if (!isValidState) return@KeyboardActions
                        keyboardController?.hide()
                    },
                )
                RowWrapper(padding = 8.dp) {
                    Text(
                        text = "Split", modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    )
                    RowWrapper(Modifier.weight(1f)) {
                        ButtonWrapper(
                            imageVector = Icons.Default.Remove,
                            elevation = 4.dp,
                            size = 50.dp,
                            isPaddingValues = false
                        ) {
                            splitPersons.value.let { if (it != 1) splitPersons.apply { value-- } }
                        }
                        Text(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            text = defaultSplitPersons.toString(),
                            textAlign = TextAlign.Center
                        )

                        ButtonWrapper(
                            imageVector = Icons.Default.Add,
                            elevation = 4.dp,
                            size = 50.dp,
                            isPaddingValues = false
                        ) {
                            splitPersons.apply { value++ }
                        }
                    }
                }
                RowWrapper(padding = 8.dp) {
                    Text(
                        text = "Tip", modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    )
                    Text(
                        text = "$$tipAmount", modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "%.0f".format(sliderValue).plus(" %"),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                SliderWrapper(sliderState = sliderState) {
                    sliderState.value = it
                }
            }

        }
    }

    @Preview
    @Composable
    fun Preview() {
        MyApplicationTheme() {
            MainSurface()
        }
    }
}