package com.assessment.presentation.userdetailscreen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.assessment.domain.model.User
import com.google.gson.Gson

@Composable
fun UserDetailScreen(user: String) {
    val user = Gson().fromJson(Uri.decode(user), User::class.java)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Profile Picture
        Image(
            painter = rememberAsyncImagePainter(user.image),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Full Name
        Text(
            text = "${user.firstName} ${user.lastName}",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Age
        Text(
            text = "Age: ${user.age}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Email
        Text(
            text = "Email: ${user.email}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Spacer before the address section
        Spacer(modifier = Modifier.height(24.dp))

        // Divider for Address Section
        Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 1.dp)

        Spacer(modifier = Modifier.height(8.dp))



        // Additional Details Section
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Address Title
                Text(
                    text = "Address:",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )

                // Address Content
                Text(
                    text = user.address,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}
