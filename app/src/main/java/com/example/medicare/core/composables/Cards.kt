package com.example.dispensary.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicare.R
import com.example.medicare.ui.theme.green
import com.example.medicare.ui.theme.primary_container
import com.example.medicare.ui.theme.secondary
import com.example.medicare.ui.theme.secondary_container
import com.example.medicare.ui.theme.tertiary

@Composable
fun SectionCardComponent(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    title: String,
) {
    Column(
        modifier = modifier
            .width(120.dp)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(
                    width = 1.dp, color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                ),
            colors = CardDefaults.cardColors(containerColor = primary_container)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.padding(10.dp),
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponsibleDoctorCardComponent(
    doctorName: String,
    doctorSpecialization: String,
    @DrawableRes doctorImage: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .height(100.dp)
            ) {
                Text(
                    text = doctorName,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = doctorSpecialization,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                ElevatedButtonComponent(
                    text = R.string.book,
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(id = doctorImage),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaccinationAppointmentCardComponent(
    onClick: () -> Unit,
    dayOfMonth: String,
    dayOfWeek: String,
    patientName: String,
    appointmentTime: String,
    vaccineName: String,
    remainingTime: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = secondary_container)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Card(
                colors = CardDefaults.cardColors(containerColor = tertiary)
            ) {
                Column(
                    modifier = Modifier
                        .width(44.dp)
                        .height(56.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = dayOfMonth,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onTertiary,
                    )
                    Text(
                        text = dayOfWeek,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onTertiary,
                    )
                }
            }
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = patientName,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = appointmentTime,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = remainingTime,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
                Text(
                    text = vaccineName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicAppointmentCardComponent(
    onClick: () -> Unit,
    dayOfMonth: String,
    dayOfWeek: String,
    patientName: String,
    appointmentTime: String,
    doctorName: String,
    doctorSpecialization: String,
    remainingTime: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = secondary_container)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Card(
                colors = CardDefaults.cardColors(containerColor = tertiary)
            ) {
                Column(
                    modifier = Modifier
                        .width(44.dp)
                        .height(56.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = dayOfMonth,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onTertiary,
                    )
                    Text(
                        text = dayOfWeek,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onTertiary,
                    )
                }
            }
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = patientName,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = appointmentTime,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = remainingTime,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = doctorName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = doctorSpecialization,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildCardComponent(
    onClick: () -> Unit,
    upNumber: String,
    downNumber: String,
    childName: String,
    fatherName: String,
    motherName: String,
    dateOfBirth: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = secondary_container)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Card(
                colors = CardDefaults.cardColors(containerColor = tertiary)
            ) {
                Column(
                    modifier = Modifier
                        .width(44.dp)
                        .height(56.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = upNumber,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onTertiary,
                    )
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .width(24.dp)
                            .background(color = MaterialTheme.colorScheme.onTertiary)
                    )
                    Text(
                        text = downNumber,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onTertiary,
                    )
                }
            }
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = childName,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = fatherName,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.outline,
                    )
                    Text(
                        text = dateOfBirth,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
                Text(
                    text = motherName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableVaccinationNotificationCardComponent(
    onClick: () -> Unit,
    @DrawableRes image: Int,
    availabilityStartDate: String,
    availabilityEndDate: String,
    title: String,
    state: String,
    doctorName: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = secondary_container)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .width(44.dp)
                    .height(56.dp),
            )
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "$availabilityStartDate - $availabilityEndDate",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.outline,
                    )
                    Text(
                        text = state,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Text(
                    text = doctorName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentReminderNotificationCardComponent(
    onClick: () -> Unit,
    @DrawableRes image: Int,
    time: String,
    patientName: String,
    remainingTime: String,
    doctorName: String,
    doctorSpecialization: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = secondary_container)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .width(44.dp)
                    .height(56.dp),
            )
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = patientName,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.outline,
                    )
                    Text(
                        text = remainingTime,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = doctorName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = doctorSpecialization,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateCardComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    dayOfMonth: String,
    dayOfWeek: String,
    isSelected: Boolean = false,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (!isSelected) primary_container else secondary,
        ),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .width(44.dp)
                .height(56.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = dayOfMonth,
                style = MaterialTheme.typography.bodyLarge,
                color = if (!isSelected)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSecondary,
            )
            Text(
                text = dayOfWeek,
                style = MaterialTheme.typography.bodyLarge,
                color = if (!isSelected)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}

@Composable
fun TimeCardComponent(
    time: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.small
            )
            .clickable {
            },
    ) {
        Text(
            text = time,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
        )
    }
}

@Composable
fun ClinicInformationCardComponent(
    @DrawableRes doctorImage: Int,
    @DrawableRes clinicImage: Int,
    doctorName: String,
    doctorSpecialization: String,
    workStartTime: String,
    workEndTime: String,
    isOpenNow: Boolean,
    sectionName: String,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = painterResource(id = doctorImage),
                    contentDescription = null,
                    modifier = Modifier
                        .height(120.dp)
                        .width(110.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = doctorName,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = doctorSpecialization,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$workStartTime - $workEndTime",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    text = if (isOpenNow) stringResource(id = R.string.open_now)
                    else stringResource(id = R.string.closed_now),
                    color = if (isOpenNow) green
                    else Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(36.dp),
                        colors = CardDefaults.cardColors(containerColor = primary_container)
                    ) {
                        Image(
                            painter = painterResource(id = clinicImage),
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp),
                        )
                    }
                    Text(
                        text = sectionName,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SectionCardComponentPreview() {
    MaterialTheme {
        Surface {
            SectionCardComponent(
                modifier = Modifier,
                image = R.drawable.vaccines,
                title = stringResource(id = R.string.test_vaccines)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResponsibleDoctorCardComponentPreview() {
    MaterialTheme {
        Surface {
            ResponsibleDoctorCardComponent(
                modifier = Modifier,
                doctorImage = R.drawable.doctor_image,
                doctorName = stringResource(id = R.string.test_doctor_name),
                doctorSpecialization = stringResource(id = R.string.test_doctor_specialization),
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun VaccinationCardAppointmentComponentPreview() {
    MaterialTheme {
        Surface {
            VaccinationAppointmentCardComponent(
                modifier = Modifier,
                onClick = {},
                dayOfMonth = stringResource(id = R.string.test_day_of_month),
                dayOfWeek = stringResource(id = R.string.test_day_of_week),
                patientName = stringResource(id = R.string.test_patient_name),
                appointmentTime = stringResource(id = R.string.test_time),
                vaccineName = stringResource(id = R.string.test_vaccine_name),
                remainingTime = stringResource(id = R.string.test_remaining_time),
            )
        }
    }
}

@Preview
@Composable
private fun ClinicCardAppointmentComponentPreview() {
    MaterialTheme {
        Surface {
            ClinicAppointmentCardComponent(
                modifier = Modifier,
                onClick = {},
                dayOfMonth = stringResource(id = R.string.test_day_of_month),
                dayOfWeek = stringResource(id = R.string.test_day_of_week),
                patientName = stringResource(id = R.string.test_patient_name),
                appointmentTime = stringResource(id = R.string.test_time),
                doctorName = stringResource(id = R.string.test_doctor_name),
                doctorSpecialization = stringResource(id = R.string.test_eye),
                remainingTime = stringResource(id = R.string.test_remaining_time),
            )
        }
    }
}

@Preview
@Composable
private fun ChildCardComponentPreview() {
    MaterialTheme {
        Surface {
            ChildCardComponent(
                modifier = Modifier,
                onClick = {},
                childName = stringResource(id = R.string.test_child_name),
                dateOfBirth = stringResource(id = R.string.test_date_of_birth),
                upNumber = stringResource(id = R.string.test_up_number),
                downNumber = stringResource(id = R.string.test_dow_number),
                fatherName = stringResource(id = R.string.test_father_name),
                motherName = stringResource(id = R.string.test_mother_name),
            )
        }
    }
}

@Preview
@Composable
private fun NotificationCardComponentPreview() {
    MaterialTheme {
        Surface {
            AvailableVaccinationNotificationCardComponent(
                modifier = Modifier,
                onClick = {},
                image = R.drawable.inoculate,
                availabilityStartDate = stringResource(id = R.string.test_availability_start_date),
                availabilityEndDate = stringResource(id = R.string.test_availability_end_date),
                title = stringResource(id = R.string.vaccination_available),
                state = stringResource(id = R.string.available),
                doctorName = stringResource(id = R.string.test_doctor_name)
            )
        }
    }
}

@Preview
@Composable
private fun AppointmentReminderNotificationCardComponentPreview() {
    MaterialTheme {
        Surface {
            AppointmentReminderNotificationCardComponent(
                modifier = Modifier,
                onClick = {},
                image = R.drawable.schedule,
                time = stringResource(id = R.string.test_time),
                patientName = stringResource(id = R.string.vaccination_available),
                remainingTime = stringResource(id = R.string.available),
                doctorName = stringResource(id = R.string.test_doctor_name),
                doctorSpecialization = stringResource(id = R.string.test_eye),
            )
        }
    }
}

@Preview
@Composable
private fun DateCardComponentPreview() {
    MaterialTheme {
        Surface {
            Row {
                DateCardComponent(
                    modifier = Modifier,
                    onClick = {},
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                )
                DateCardComponent(
                    modifier = Modifier,
                    onClick = {},
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    isSelected = true
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimeCardComponentPreview() {
    MaterialTheme {
        Surface {
            TimeCardComponent(
                time = stringResource(id = R.string.test_time)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SectionInformationCardComponentPreview() {
    MaterialTheme {
        Surface {
            ClinicInformationCardComponent(
                doctorImage = R.drawable.doctor_image,
                clinicImage = R.drawable.eye,
                doctorSpecialization = stringResource(id = R.string.test_doctor_specialization),
                doctorName = stringResource(id = R.string.test_doctor_name),
                workStartTime = stringResource(id = R.string.test_work_start_time),
                workEndTime = stringResource(id = R.string.test_work_end_time),
                isOpenNow = true,
                sectionName = stringResource(id = R.string.test_eye)
            )
        }
    }
}

