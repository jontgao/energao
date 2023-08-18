package com.example.energao.ui.screens

import androidx.annotation.StringRes
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.energao.R
import com.example.energao.ui.theme.EnergaoTheme
import com.example.energao.ui.theme.customColors

/**************************************************************************************************
 * Bases
 **************************************************************************************************/
/**
 * Card base
 * For now, a card with padding. This composable is primarily to allow room for growth.
 */
@Composable
fun EnergaoCardBase(
    modifier: Modifier = Modifier,
    cardContent: @Composable () -> Unit
) {
    Card(modifier = modifier) {
        Box(modifier = Modifier.padding(dimensionResource(R.dimen.card_padding))) {
            cardContent()
        }
    }
}

/**
 * Header paragraph base
 * Base card with a header and then paragraph text.
 */
@Composable
fun EnergaoHeaderParagraphCard(
    headerText: String,
    paragraphText: String,
    modifier: Modifier = Modifier
) {
    EnergaoCardBase(modifier = modifier) {
        Column {
            Text(
                text = headerText,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = paragraphText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/**
 * Big stat base
 * Base card with a large, heavily-emphasized number and then a small description below. Can also
 * have a small unit next to the large number.
 */
@Composable
fun EnergaoBigStatCard(
    statNumber: Float,
    @StringRes unitTextId: Int,
    @StringRes descriptionTextId: Int,
    modifier: Modifier = Modifier
) {
    EnergaoCardBase(modifier = modifier) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = statNumber.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = stringResource(id = unitTextId),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            Text(
                text = stringResource(id = descriptionTextId),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/**************************************************************************************************
 * App composables
 **************************************************************************************************/
/**
 * Percente of charge card
 * Displays the percent of charge
 */
@Composable
fun PercentOfChargeCard(
    percentOfCharge: Int,
    hoursRemaining: Float,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Card(modifier = Modifier
            .height(dimensionResource(R.dimen.percent_of_charge_height))
            .weight(1f)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                PercentOfChargeBackground(percentOfCharge)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.card_padding))
                        .fillMaxSize()
                ) {
                    PercentOfChargeHeader(percentOfCharge)
                    Text(
                        /*TODO: make actual number*/
                        text = LocalContext.current.resources.getQuantityString(
                            R.plurals.percent_of_charge_hours_remaining,
                            if (hoursRemaining == 1f) 1 else 2,
                            hoursRemaining
                        ),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .width(dimensionResource(R.dimen.percent_of_charge_hours_remaining_width))
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .width(dimensionResource(R.dimen.percent_of_charge_battery_tip_width))
                .height(dimensionResource(R.dimen.percent_of_charge_battery_tip_height))
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(R.dimen.percent_of_charge_battery_tip_start_corner_radius),
                        topEnd = dimensionResource(R.dimen.percent_of_charge_battery_tip_end_corner_radius),
                        bottomEnd = dimensionResource(R.dimen.percent_of_charge_battery_tip_end_corner_radius),
                        bottomStart = dimensionResource(R.dimen.percent_of_charge_battery_tip_start_corner_radius)
                    )
                )
        )
    }
}

/**
 * Percent of charge header
 * Energao lightning bolt logo with the percent of charge
 */
@Composable
fun PercentOfChargeHeader(
    percentOfCharge: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        /**TODO: Having difficulty doing the circle behind the lightning bolt**/
        Image(
            painter = painterResource(id = R.drawable.energao_lightning_bolt),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.percent_of_charge_lightning_bolt_padding))
                .width(dimensionResource(R.dimen.percent_of_charge_lightning_bolt_width))
                .height(dimensionResource(R.dimen.percent_of_charge_lightning_bolt_height))
        )
        Text(
            text = stringResource(R.string.percent_of_charge_percent, percentOfCharge),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displayLarge
        )
    }
}

/**
 * Percent of charge background
 * "Splashy" background of battery filling up
 */
@Composable
fun PercentOfChargeBackground(
    percentOfCharge: Int,
    modifier: Modifier = Modifier
) {
    // Creates an [InfiniteTransition] instance for managing child animations.
    val infiniteTransition = rememberInfiniteTransition()

    // Creates a child animation of float type as a part of the [InfiniteTransition].
    val animationDuration = 2500
    val scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            // Infinitely repeating a 1000ms tween animation using default easing curve.
            animation = tween(animationDuration),
            repeatMode = RepeatMode.Restart
        )
    )
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = CubicBezierEasing(1f, 0f, 0.9f, 0f)
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val bottomSize = Size(size.width * (percentOfCharge / 100f), size.height)
        drawRect(
            color = customColors.percentOfChargeBottom,
            size = bottomSize
        )

        val midSize = Size(bottomSize.width * scale, bottomSize.height)
        drawRect(
            color = customColors.percentOfChargeMiddle,
            size = midSize,
            alpha = alpha
        )
    }
}

/**
 * Battery health card
 * Displays any issues regarding the battery's health. For now, just displays "No issues."
 */
@Composable
fun BatteryHealthCard(
    batteryHealth: String,
    modifier: Modifier = Modifier
) {
    EnergaoHeaderParagraphCard(
        headerText = stringResource(R.string.battery_health),
        paragraphText = batteryHealth,
        modifier = modifier.fillMaxWidth()
    )
}

/**
 * Battery temp card
 * Displays the battery's temperature.
 */
@Composable
fun BatteryTempCard(
    temp: Float,
    modifier: Modifier = Modifier
) {
    EnergaoBigStatCard(
        statNumber = temp,
        unitTextId = R.string.degrees_celsius,
        descriptionTextId = R.string.battery_temperature,
        modifier = modifier
    )
}

/**
 * Battery voltage card
 * Displays the battery's voltage.
 */
@Composable
fun BatteryVoltageCard(
    voltage: Float,
    modifier: Modifier = Modifier
) {
    EnergaoBigStatCard(
        statNumber = voltage,
        unitTextId = R.string.volts,
        descriptionTextId = R.string.battery_voltage,
        modifier = modifier
    )
}

/**
 * Battery temperature and voltage cards row
 * The two cards in a row
 */
@Composable
fun BatteryTempAndVoltageCardsRow(
    temp: Float,
    voltage: Float,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.card_spaced_by)),
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        val subcardModifier = Modifier
            .weight(1f)
            .fillMaxHeight()
        BatteryTempCard(temp = temp, modifier = subcardModifier)
        BatteryVoltageCard(voltage = voltage, modifier = subcardModifier)
    }
}

/**
 * Savings card
 * Displays various saving amounts
 */
@Composable
fun SavingsCard(
    savingsEnergy: Float,
    savingsBills: Float,
    savingsCO2: Float,
    modifier: Modifier = Modifier
) {
    EnergaoCardBase(
        modifier = modifier.fillMaxWidth()
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.savings_intro_desc_spacing))
        ) {
            Text(
                text = stringResource(R.string.savings_intro_text),
                style = MaterialTheme.typography.bodyLarge
            )

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                SavingsItem(
                    boldString = savingsEnergy.toString(),
                    descStringId = R.string.savings_energy_desc
                )
                SavingsItem(
                    boldString = savingsBills.toString(),
                    descStringId = R.string.savings_bills_desc
                )
                SavingsItem(
                    boldString = savingsCO2.toString(),
                    descStringId = R.string.savings_co2_desc
                )
            }
        }
    }
}

@Composable
fun SavingsItem(
    boldString: String,
    @StringRes descStringId: Int,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.savings_desc_spacing)),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = boldString,
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(id = descStringId),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

/**************************************************************************************************
 * Previews
 **************************************************************************************************/
@Preview
@Composable
fun PreviewPercentOfChargeCard() {
    EnergaoTheme {
        PercentOfChargeCard(
            percentOfCharge = 89,
            hoursRemaining = 13.22f
        )
    }
}

@Preview
@Composable
fun BatteryHealthCardPreview() {
    EnergaoTheme {
        BatteryHealthCard("No issues.")
    }
}

@Preview
@Composable
fun BatteryTempAndBatteryVoltageCardsPreview() {
    EnergaoTheme {
        Box(modifier = Modifier.padding(dimensionResource(R.dimen.card_spaced_by))) {
            BatteryTempAndVoltageCardsRow(89.1f, 13.22f)
        }
    }
}

@Preview
@Composable
fun SavingsCardPreview() {
    EnergaoTheme {
        SavingsCard(
            101.5f,
            1234f,
            0.7f,
        )
    }
}
