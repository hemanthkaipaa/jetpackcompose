package com.kaipa.jetpackcompose.ilearn.components

import android.R.attr.enabled
import android.R.attr.onClick
import android.R.attr.text
import android.R.attr.value
import androidx.annotation.Dimension.Companion.DP
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kaipa.jetpackcompose.ilearn.ui.theme.Purple40
import com.kaipa.jetpackcompose.ilearn.ui.theme.Purple80
import com.kaipa.jetpackcompose.ilearn.ui.theme.PurpleGrey40
import com.kaipa.jetpackcompose.ilearn.ui.theme.PurpleGrey80

@Composable
fun ColumnWrapper(
    modifier: Modifier = Modifier,
    padding: Dp = 0.dp,
    orientation: Arrangement.Vertical = Arrangement.Top,
    alignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit
) {
    val finalModifier = modifier
        .fillMaxWidth()
        .then(
            if (padding > 0.dp) {
                Modifier.padding(padding)
            } else {
                Modifier
            }
        )

    Column(
        modifier = finalModifier,
        verticalArrangement = orientation,
        horizontalAlignment = alignment,
        content = content
    )
}

@Composable
fun RowWrapper(
    modifier: Modifier = Modifier,
    padding: Dp = 0.dp,
    arrangement: Arrangement.Horizontal = Arrangement.Center,
    alignment: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable RowScope.() -> Unit
) {
    val finalModifier = modifier
        .fillMaxWidth()
        .then(
            if (padding > 0.dp) {
                Modifier.padding(padding)
            } else {
                Modifier
            }
        )
    Row(
        modifier = finalModifier,
        horizontalArrangement = arrangement,
        verticalAlignment = alignment,
        content = content
    )
}

@Composable
fun ButtonWrapper(
    modifier: Modifier = Modifier,
    height: Dp = 0.dp,
    width: Dp = 0.dp,
    size: Dp = 0.dp,
    padding: Dp = 0.dp,
    isPaddingValues: Boolean = true,
    enabled: Boolean = true,
    text: String? = null,
    imageVector: ImageVector? = null,
    shape: Shape = CircleShape,
    elevation: Dp = 4.dp,
    buttonColor: Color = Color.White,
    buttonTintColor: Color = Color.Black,
    onClick: () -> Unit
) {

    val finalModifier = modifier
        .then(
            if (height > 0.dp) {
                Modifier.height(height)
            } else if (width > 0.dp) {
                Modifier.width(width)
            } else if (size > 0.dp) {
                Modifier.size(size)
            } else {
                Modifier
            }
        )
        .then(padding.let {
            if (it > 0.dp) {
                Modifier.padding(it)
            } else {
                Modifier
            }
        })
    val contentPaddingValue = isPaddingValues.let {
        if (!it) PaddingValues(0.dp) else ButtonDefaults.ContentPadding
    }
    Button(
        modifier = finalModifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        elevation = ButtonDefaults.buttonElevation(elevation),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        contentPadding = contentPaddingValue
    ) {
        text?.run {
            Text(text = this, modifier = Modifier.fillMaxSize())
        }
        imageVector?.run {
            Icon(imageVector = this, contentDescription = "icon", tint = buttonTintColor)
        }
    }
}

@Composable
fun AddSpace(padding: Dp = 0.dp, height: Dp = 10.dp) {
    Spacer(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .padding(padding)
    )
}

@Composable
fun CardWrapper(
    modifier: Modifier = Modifier,
    cardHeight: Dp = 0.dp,
    cardPadding: Dp = 16.dp,
    cardColor: Color = Color.White,
    cardShape: Shape = RoundedCornerShape(4.dp),
    cardElevation: CardElevation = CardDefaults.elevatedCardElevation(4.dp),
    cardContent: @Composable ColumnScope.() -> Unit
) {
    val finalModifier = modifier
        .then(
            if (cardPadding > 16.dp) {
                Modifier.padding(cardPadding)
            } else {
                Modifier.padding(16.dp)
            }
        )
        .fillMaxWidth()
        .then(
            cardHeight.let { if (it > 0.dp) Modifier.height(it) else Modifier.wrapContentHeight() }
        )
    Card(
        finalModifier,
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = cardShape,
        elevation = cardElevation,
        content = cardContent
    )
}

@Composable
fun OutlinedTextFieldWrapper(
    mutableValue: MutableState<String>,
    modifier: Modifier = Modifier,
    padding: Dp = 16.dp,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    actions: KeyboardActions = KeyboardActions.Default,
    label: String,
    placeholder: String = "",
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null
) {
    val finalModifier = modifier
        .fillMaxWidth()
        .then(
            if (padding != 16.dp) {
                Modifier.padding(padding)
            } else {
                Modifier.padding(16.dp)
            }
        )

    OutlinedTextField(
        mutableValue.value,
        onValueChange = {
            mutableValue.value = it
        },
        modifier = finalModifier,
        singleLine = singleLine,
        maxLines = maxLines,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = actions,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            startIcon?.let { Icon(imageVector = it, contentDescription = "startIcon") }
        },
        trailingIcon = {
            endIcon?.run { Icon(imageVector = this, contentDescription = "endIcon") }
        }

    )
}

@Composable
fun SliderWrapper(
    modifier: Modifier = Modifier,
    padding: Dp = 0.dp,
    sliderState: MutableState<Float>,
    steps: Int = 9,
    thumbColor: Color = Purple80,
    activeTrackColor: Color = Purple40,
    inactiveTrackColor: Color = PurpleGrey80,
    activeTickColor: Color = Purple40,
    inactiveTickColor: Color = PurpleGrey40,
    onValueChange : (Float) ->Unit
) {
    val finalModifier = modifier
        .fillMaxWidth()
        .then(
            padding.let { if (it > 0.dp) Modifier.padding(it) else Modifier.padding(16.dp) }
        )
    Slider(
        modifier = finalModifier,
        value = sliderState.value,
        onValueChange = onValueChange,
        valueRange = 0f..1f,
        steps = steps,
        colors = SliderDefaults.colors(
            thumbColor = thumbColor,

            activeTrackColor = activeTrackColor,
            inactiveTrackColor = inactiveTrackColor,
            activeTickColor = activeTickColor,
            inactiveTickColor = inactiveTickColor
        )
    )
}