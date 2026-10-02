package com.example.kartikee.feature.parental

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kartikee.core.network.RetrofitClient
import com.example.kartikee.core.network.models.ComplianceReportResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ParentalViewModel : ViewModel() {

    private val _reportState = MutableStateFlow<ComplianceReportResponse?>(null)
    val reportState: StateFlow<ComplianceReportResponse?> = _reportState

    fun fetchParentalReport(jwtToken: String) {
        // Launch coroutine on ViewModelScope
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getParentalReport(token = "Bearer $jwtToken")
                if (response.isSuccessful) {
                    _reportState.value = response.body()
                } else {
                    // Handle HTTP error (e.g., 401 Unauthorized)
                }
            } catch (e: Exception) {
                // Handle network failure/exception
            }
        }
    }
}