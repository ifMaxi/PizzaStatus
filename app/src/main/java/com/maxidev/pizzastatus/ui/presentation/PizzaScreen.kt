package com.maxidev.pizzastatus.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.pizzastatus.R
import com.maxidev.pizzastatus.data.network.model.PizzaModel
import com.maxidev.pizzastatus.ui.presentation.components.CoilImageComponent
import com.maxidev.pizzastatus.ui.presentation.components.StatusComponent
import com.maxidev.pizzastatus.ui.theme.soraFont
import com.maxidev.pizzastatus.utils.CodeList.listOfCodes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaScreen(
    modifier: Modifier = Modifier,
    viewModel: PizzaViewModel = hiltViewModel()
) {
    val state: PizzaStatusState by viewModel.statusState.collectAsStateWithLifecycle()
    var query = viewModel.search.value
    var active by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Box(modifier = modifier.fillMaxWidth()) {
                SearchBar(
                    modifier = Modifier.align(Alignment.TopCenter),
                    query = query,
                    onQueryChange = viewModel::onSearchChange,
                    onSearch = {
                        scope.launch {
                            if (query.isEmpty()) {
                                active = false
                            } else {
                                active = false
                                viewModel.deliverPizza(it)
                            }
                        }
                    },
                    active = active,
                    onActiveChange = { active = true },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search_placeholder)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    },
                    tonalElevation = SearchBarDefaults.Elevation
                ) {
                    listOfCodes.forEach { code ->
                        ListItem(
                            modifier = Modifier
                                .clickable {
                                    query = code
                                    viewModel.deliverPizza(code)
                                    active = false
                                },
                            headlineContent = {
                                Text(
                                    text = code
                                )
                            },
                            leadingContent = {
                                Icon(
                                    imageVector = Icons.Outlined.Star,
                                    contentDescription = null
                                )
                            },
                            tonalElevation = ListItemDefaults.Elevation,
                            shadowElevation = ListItemDefaults.Elevation
                        )
                        Divider()
                    }
                }
            }
        }
    ) { paddingValues ->
        StatusCheck(
            status = state,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}

@Composable
private fun StatusCheck(
    status: PizzaStatusState,
    modifier: Modifier = Modifier,
) {
    when (status) {
        is PizzaStatusState.Error -> StatusComponent(
            message = R.string.error,
            rawRes = R.raw.pizza_error
        )
        is PizzaStatusState.Loading -> StatusComponent(
            message = R.string.loading,
            rawRes = R.raw.pizza_loading
        )
        is PizzaStatusState.Success -> PizzaCard(
            model = status.onSuccess,
            modifier = modifier
        )
        is PizzaStatusState.Wait -> StatusComponent(
            message = R.string.waiting,
            rawRes = R.raw.pizza_wait
        )
    }
}

@Composable
private fun PizzaCard(
    modifier: Modifier = Modifier,
    model: PizzaModel
) {
    ScreenContent(
        image = model.image,
        category = model.category,
        modifier = modifier
    )
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    image: String,
    category: String
) {
    val roundCorner = RoundedCornerShape(5)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Category: $category",
            fontFamily = soraFont,
            fontSize = 25.sp
        )
        Card(
            modifier = Modifier
                .padding(4.dp)
                .clip(roundCorner)
                .border(
                    BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    shape = roundCorner
                ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            CoilImageComponent(
                image = image
            )
        }
    }
}