package com.example.todo.presentation.home_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.R
import com.example.todo.ui.theme.ButtonColor
import com.example.todo.ui.theme.ToDoTheme

@Composable
fun BottomButton(
    openDialog: () -> Unit
) {
    Button(
        onClick = { openDialog()},
        modifier = Modifier
            .size(width = 544.dp, height = 80.dp)
            .padding(horizontal = 10.dp)
            .padding(bottom = 5.dp, top = 10.dp),
        colors = ButtonDefaults.buttonColors(ButtonColor)
    ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text =  stringResource(id = R.string.add_task),
                style = MaterialTheme.typography.labelLarge
            )
        }


    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BottomButton_Show() {
    ToDoTheme {
        BottomButton(openDialog = {})
    }
}
