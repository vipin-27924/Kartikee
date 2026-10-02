package com.example.kartikee.feature.parental

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ParentalScreen() {
    var isAuthenticated by remember { mutableStateOf(false) }
    var pinCode by remember { mutableStateOf("") }
    var pinError by remember { mutableStateOf(false) }

    if (!isAuthenticated) {
        // Security PIN Entry Screen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1B4D3E))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE0EAE5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = Color(0xFF1B4D3E)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Parental Dashboard",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B4D3E)
                    )
                    Text(
                        text = "Enter 4-digit PIN to access clinical controls",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = pinCode,
                        onValueChange = {
                            if (it.length <= 4) {
                                pinCode = it
                                pinError = false
                            }
                        },
                        label = { Text("Security PIN") },
                        singleLine = true,
                        isError = pinError,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1B4D3E),
                            focusedLabelColor = Color(0xFF1B4D3E)
                        )
                    )

                    if (pinError) {
                        Text(
                            text = "Incorrect PIN. Try default (1234)",
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            // Default passcode for demonstration
                            if (pinCode == "1234" || pinCode.isEmpty()) {
                                isAuthenticated = true
                            } else {
                                pinError = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B4D3E)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Unlock Dashboard")
                    }
                }
            }
        }
    } else {
        // Authenticated Dashboard Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F7F6))
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Parental Controls",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B4D3E)
                    )
                    Text(
                        text = "Therapy tracking & clinical reports",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
                IconButton(onClick = { isAuthenticated = false }) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock Dashboard",
                        tint = Color(0xFF1B4D3E)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Overview Summary Card
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1B4D3E))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Weekly Compliance Rate",
                                fontSize = 14.sp,
                                color = Color(0xFFA3D2C2)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "88% Completed",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            LinearProgressIndicator(
                                progress = { 0.88f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                color = Color(0xFF7ED321),
                                trackColor = Color(0xFF2C6B57)
                            )
                        }
                    }
                }

                // Report Generation Options
                item {
                    Text(
                        text = "Clinical Actions",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B4D3E)
                    )
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Assessment,
                                    contentDescription = null,
                                    tint = Color(0xFF1B4D3E)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Export Therapist Report",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = Color(0xFF1B4D3E)
                                    )
                                    Text(
                                        text = "Generate PDF breakdown of posture & vocal sessions",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedButton(
                                onClick = { /* PDF Export logic */ },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Download,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Download Clinical PDF")
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Schedule,
                                    contentDescription = null,
                                    tint = Color(0xFF1B4D3E)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Daily Session Reminders",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = Color(0xFF1B4D3E)
                                    )
                                    Text(
                                        text = "Prompt child at 4:00 PM daily",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                            var reminderEnabled by remember { mutableStateOf(true) }
                            Switch(
                                checked = reminderEnabled,
                                onCheckedChange = { reminderEnabled = it },
                                colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF1B4D3E))
                            )
                        }
                    }
                }
            }
        }
    }
}