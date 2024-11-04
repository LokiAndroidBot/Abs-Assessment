package com.assessment.presentation.userlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.assessment.domain.model.User

@Composable
fun UserListItem(
    user: User, onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(user.id) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF444444)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xCCFFFFFF) // A light gray "cachey" background color
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(user.thumbnail).crossfade(true).scale(Scale.FILL).build(),
                    contentDescription = "${user.firstName}'s Profile Image",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    loading = {
                        // Placeholder color for the loading state
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )
                    },
                    error = {
                        // Placeholder in case of error
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                        )
                    })

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "${user.firstName} ${user.lastName}",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = user.address,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListItemPreview() {
    // Sample user data
    val user = User(
        id = "1",
        firstName = "John",
        lastName = "Doe",
        email = "john.doe@example.com",
        address = "123 Main St, Springfield, USA",
        thumbnail = "https://example.com/profile.jpg" // Valid image URL
    )

    // Create a lambda for the onClick action
    val onClick: (String) -> Unit = { userId -> /* Handle click */ }

    // Render the UserListItem with a valid user
    UserListItem(user = user, onClick = onClick)
}
