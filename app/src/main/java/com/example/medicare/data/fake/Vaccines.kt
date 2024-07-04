package com.example.medicare.data.fake

import com.example.medicare.core.enums.AgeUnit
import com.example.medicare.core.enums.Month
import com.example.medicare.core.toFullDate
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.model.date.Age
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.vaccine.Vaccine
import java.time.LocalDate
import kotlin.random.Random

val mmrVaccine = Vaccine(
    name = "MMR Vaccine",
    description = "Protects against Measles, Mumps, and Rubella",
    fromAge = Age(12,AgeUnit.MONTHS), // 12 months old
    toAge = Age(15, AgeUnit.MONTHS), // 15 months old
    availabilityStartDate = FullDate(1, Month.JAN, 2024),
    lastAvailableDate = LocalDate.now().plusDays(10).toFullDate(), // No end date specified
    conflicts = listOf("Egg allergy"),
    visitNumber = 2 // Second dose
)
val vaccines= listOf(
    // Measles, Mumps, and Rubella (MMR)
    Vaccine(
        name = "MMR Vaccine",
        description = "Protects against Measles, Mumps, and Rubella",
        fromAge = Age(12,AgeUnit.MONTHS), // 12 months old
        toAge = Age(15, AgeUnit.MONTHS), // 15 months old
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate()
    ),
    // DTaP (diphtheria, tetanus, and acellular pertussis)
    Vaccine(
        name = "DTaP Vaccine",
        description = "Protects against diphtheria, tetanus, and pertussis",
        fromAge = Age(2,AgeUnit.MONTHS), // 2 months old
        toAge = Age(6,AgeUnit.MONTHS), // 6 years old (booster shots recommended)
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate()
    ),
    // Poliovirus (Polio)
    Vaccine(
        name = "Poliovirus Vaccine",
        description = "Protects against poliovirus",
        fromAge = Age(2,AgeUnit.MONTHS), // 2 months old
        toAge = Age(4,AgeUnit.YEARS), // 4 years old (booster shots recommended)
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate()
    ),
    // Hepatitis B (HepB)
    Vaccine(
        name = "Hepatitis B Vaccine",
        description = "Protects against hepatitis B virus",
        fromAge = Age(1,AgeUnit.DAYS), // Birth
        toAge = Age(10,AgeUnit.YEARS), // No upper age limit
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate()
    ),
    // Haemophilus influenzae type b (Hib)
    Vaccine(
        name = "Hib Vaccine",
        description = "Protects against Haemophilus influenzae type b",
        fromAge = Age(2,AgeUnit.MONTHS), // 2 months old
        toAge = Age(5,AgeUnit.MONTHS), // 5 years old
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate()
    ),
    // Varicella (Chickenpox)
    Vaccine(
        name = "Varicella Vaccine",
        description = "Protects against varicella-zoster virus (chickenpox)",
        fromAge = Age(12,AgeUnit.MONTHS), // 12 months old
        toAge = Age(13,AgeUnit.YEARS), // 13 years old (second dose)
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate()
    ),
    // Pneumococcal conjugate (PCV13)
    Vaccine(
        name = "PCV13 Vaccine",
        description = "Protects against pneumococcal diseases",
        fromAge = Age(2,AgeUnit.MONTHS), // 2 months old
        toAge = Age(2,AgeUnit.YEARS), // 2 years old (series of 4 doses)
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate()
    ),
    // Rotavirus
    Vaccine(
        name = "Rotavirus Vaccine",
        description = "Protects against rotavirus infection",
        fromAge = Age(2,AgeUnit.MONTHS), // 2 months old
        toAge = Age(6,AgeUnit.MONTHS), // 6 months old (series of 3 doses)
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate()
    ),
    // Meningococcal (Meningococcal conjugate)
    Vaccine(
        name = "Meningococcal Vaccine",
        description = "Protects against meningococcal meningitis",
        fromAge = Age(11,AgeUnit.YEARS), // 11 or 12 years old (depending on recommendation)
        toAge = Age(16,AgeUnit.YEARS), // 16 years old (booster shot recommended)
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate(),
    ),

    // Tdap (tetanus, diphtheria, and acellular pertussis) - Booster
    Vaccine(
        name = "Tdap Vaccine (Booster)",
        description = "Protects against tetanus, diphtheria, and pertussis",
        fromAge = Age(11,AgeUnit.YEARS), // 11 or 12 years old (booster)
        toAge = Age(10,AgeUnit.YEARS), // No upper age limit (recommended every 10 years)
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate(),
    ),
// Human Papillomavirus (HPV)
    Vaccine(
        name = "HPV Vaccine",
        description = "Protects against human papillomavirus (HPV) infection",
        fromAge = Age(11,AgeUnit.YEARS), // 11 or 12 years old (recommended)
        toAge = Age(26,AgeUnit.YEARS), // 26 years old (may be given up to this age)
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate(),
    ),
// Hepatitis A (HepA)
    Vaccine(
        name = "Hepatitis A Vaccine",
        description = "Protects against hepatitis A virus",
        fromAge = Age(1,AgeUnit.YEARS), // 1 year old
        toAge = Age(10,AgeUnit.YEARS), // No upper age limit
        availabilityStartDate = FullDate(1, Month.JAN, 2020),
        lastAvailableDate = LocalDate.now().plusDays(10).toFullDate(),
    ),
// Influenza (Flu)
    Vaccine(
        name = "Flu Vaccine",
        description = "Protects against seasonal influenza viruses",
        fromAge = Age(6,AgeUnit.MONTHS), // 6 months old
        toAge = Age(10,AgeUnit.YEARS), // No upper age limit (recommended annually)
        availabilityStartDate = FullDate(10, Month.AUG, 2023), // Available during flu season
        lastAvailableDate = FullDate(3, Month.DEC, 2024), // Typically ends after flu season
    )
)
