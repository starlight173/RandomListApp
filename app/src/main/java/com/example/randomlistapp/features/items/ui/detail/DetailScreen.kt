package com.example.randomlistapp.features.items.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.randomlistapp.core.ui.theme.ViewState
import com.example.randomlistapp.features.items.domain.entities.Item

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    vm: ItemDetailViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsState()

    when (val s = state) {
        is ViewState.Idle -> Text("Idle…", modifier = modifier.padding(16.dp))

        is ViewState.Loading -> CircularProgressIndicator(modifier = modifier.padding(16.dp))

        is ViewState.Success -> ItemDetailContent(modifier = modifier, item = s.data)

        is ViewState.Failure -> Text(
            "Error: ${s.throwable.localizedMessage ?: s.throwable.javaClass.simpleName}",
            modifier = modifier.padding(16.dp)
        )

        else -> {}
    }
}

@Composable
private fun ItemDetailContent(
    modifier: Modifier = Modifier,
    item: Item
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        Text(item.title, style = MaterialTheme.typography.headlineSmall)
        Text(item.description, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Start)
    }
}