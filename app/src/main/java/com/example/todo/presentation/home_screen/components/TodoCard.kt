package com.example.todo.presentation.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.domain.model.Todo
import com.example.todo.ui.theme.ToDoTheme

@Composable
fun TodoCard(
    todo: Todo,
    onDone:() -> Unit,
    onUpdate: (id: Int) -> Unit,
    modifier: Modifier
) {

    Card(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 5.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            Color(
                red = 207,
                green = 231,
                blue = 223
            )
        ),
        elevation = CardDefaults.cardElevation(),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(intrinsicSize = IntrinsicSize.Max),
        ) {
            Row(
                //this Row
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.taskTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                )
                if (todo.isImportant) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Color(
                            red = 37,
                            green = 113,
                            blue = 87,
                        )
                    )
                }

            }
            Divider(
                modifier = modifier.fillMaxWidth(.8f),
                thickness = DividerDefaults.Thickness
            )
            Spacer(modifier = modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(.8f)
                ) {
                    Text(
                        text = todo.task,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 3
                    )
                }
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier.weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                        tint = Color(
                            red = 37,
                            green = 113,
                            blue = 87,
                        ),
                        modifier = modifier.clickable { onUpdate(todo.id) }
                    )
                }
            }
            Spacer(modifier = modifier.height(10.dp))
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            )
            {
                Button(
                    onClick = { onDone() },
                    colors = ButtonDefaults.buttonColors(
                        Color(
                            red = 15,
                            green = 77,
                            blue = 58
                        )
                    ),
                    elevation = ButtonDefaults.buttonElevation(20.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = modifier
                            .size(18.dp)
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        text = "Done",
                        modifier = Modifier,
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Show() {
    ToDoTheme {
        TodoCard(todo = Todo(0,"Musaib", "dfkjvndkfjvndfv", true), onUpdate = {}, onDone = {}, modifier = Modifier)

    }
}