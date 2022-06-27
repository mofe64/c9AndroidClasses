package com.nubari.introtocompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nubari.introtocompose.Login
import com.nubari.introtocompose.ui.theme.backgroundColor
import com.nubari.introtocompose.R
import com.nubari.introtocompose.ui.theme.locationColor
import com.nubari.introtocompose.ui.theme.peachColor

@Composable
fun Home() {
    val search = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(20.dp)
    ) {
        Header(
            headerText = "Jakarta",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        PromoCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            promoPercentage = 32
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = search.value,
            onValueChange = { newValue ->
                search.value = newValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(20)
                ),
            placeholder = { Text(text = "Search here") },
            shape = RoundedCornerShape(20),
            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xfff9f9f9),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "",
                )
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MealTab(meal = "sushi",active = true)
            Spacer(modifier = Modifier.width(20.dp))
            MealTab(meal = "ramen")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    Home()
}