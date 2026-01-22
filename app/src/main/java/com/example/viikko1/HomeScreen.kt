package com.example.viikko1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel = viewModel()
) {
    val tasks = taskViewModel.tasks.value
    var newTaskTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tehtävälista",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ➕ ADD TASK
        TextField(
            value = newTaskTitle,
            onValueChange = { newTaskTitle = it },
            label = { Text("Uusi tehtävä") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (newTaskTitle.isNotBlank()) {
                    taskViewModel.addTask(newTaskTitle)
                    newTaskTitle = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 📋 TASK LISTA
        tasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(task.title)

                Button(onClick = {
                    taskViewModel.toggleDone(task.id)
                }) {
                    Text(if (task.done) "Undo" else "Done")
                }
            }
        }
    }
}
