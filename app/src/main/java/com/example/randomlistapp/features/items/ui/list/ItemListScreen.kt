package com.example.randomlistapp.features.items.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.randomlistapp.core.ui.theme.ViewState
import com.example.randomlistapp.features.items.domain.entities.Item

@Composable
fun ItemListScreen(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit,
    vm: ItemListViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsState()

    when (val s = state) {
        is ViewState.Idle -> Text("Idle…", modifier = modifier)

        is ViewState.Loading -> CircularProgressIndicator(modifier = modifier)

        is ViewState.Success -> {
            val items = s.data
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(items.size, key = { items[it].id }) { index ->
                    val item = items[index]
                    ItemRow(item = item, modifier = Modifier.fillMaxWidth()) {
                        onItemClick(item.id)
                    }
                }
            }
        }

        is ViewState.Failure -> {
            Text("Error: ${s.throwable.localizedMessage}", modifier = modifier)
        }

        else -> { /* no-op, added to make when exhaustive */ }
    }
}

@Composable
private fun ItemRow(
    item: Item,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(item.title, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        item.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}
