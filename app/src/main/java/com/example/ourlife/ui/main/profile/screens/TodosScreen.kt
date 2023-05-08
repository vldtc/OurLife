package com.example.ourlife.ui.main.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ourlife.data.model.todos.TodosItemModel
import com.example.ourlife.ui.main.profile.ProfileViewModel
import com.example.ourlife.ui.theme.Primary
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.example.ourlife.ui.main.profile.DividerTab

@Composable
fun TodosContent() {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val todos by viewModel.userTodos.collectAsState()
    viewModel.getUserTodos()

    val totalTodos = todos.size
    var completedTodos = 0

    for (item in todos) if (item.completed == true) completedTodos++

    val notCompletedTodos = totalTodos - completedTodos

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
           Text(text = "Total: $totalTodos")
           Text(text = "Completed: $completedTodos")
           Text(text = "In progress: $notCompletedTodos")
        }
        Divider()
        LazyColumn(
            Modifier.padding(bottom = 58.dp)
        ) {
            items(todos.size) { item ->
                ProfileTodosView(todo = todos.get(item))
            }
        }
    }

}

@Composable
fun ProfileTodosView(
    todo: TodosItemModel

) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            elevation = 10.dp
        ) {
            Row(
                modifier = Modifier
                    .background(Primary)
                    .padding(4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = todo.title.toString(),
                    Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .weight(8f),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                if (todo.completed == true) {
                    Icon(
                        Icons.Default.Done,
                        contentDescription = "done",
                        modifier = Modifier.weight(1f),
                        tint = Color.White
                    )
                } else {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "done",
                        modifier = Modifier.weight(1f),
                        tint = Color.White
                    )
                }
            }

        }
    }

}
