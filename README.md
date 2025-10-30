# Medicare (Dispensary automation application)
An innovative mobile application suite designed to streamline organizational and data management processes in free dispensaries. This project focuses on enhancing transparency and operational efficiency for both patients and doctors.

## Overview
Medicare is a comprehensive platform developed as a showcase of a semester academic project. It addresses critical issues in healthcare administration, particularly in underserved communities, by providing a robust system for managing patient data, vaccination schedules, and appointments. The application suite consists of multiple interconnected mobile applications, each tailored for a specific user role within a dispensary.

## How it Works
The Medicare application suite consists of two distinct mobile applications that communicate with a central backend system:

* User App:  This application allows users (patients or their parents) to view their health records, track vaccination schedules, and display appointments.

* Doctor App:  This application enables doctors to view patient history, review vaccination records, and display their schedules.

## Features
* Patient Management: The system provides a centralized way to archive children's vaccination records and appointment history, ensuring data integrity and easy access.

* Appointment Scheduling: Users can easily view and manage upcoming appointments, reducing organizational overhead.

* Data Transparency: By digitizing records, the platform creates a highly transparent system that minimizes errors and improves overall operational efficiency.

* Intuitive UI/UX: The applications feature a clean and user-friendly interface designed to simplify complex healthcare workflows for all user types.

## Technologies
* Architecture: Engineered a robust MVVM (Model-View-ViewModel) and Dual-App Architecture (Patient & Doctor clients) communicating via a central Firebase backend.
* Design & DI: Successfully built the entire system using Kotlin for the first time, leveraging Hilt-Dagger for robust dependency injection and Jetpack Compose for the entire modern UI.
* Backend: Utilized Firebase Authentication, Firestore, and FCM (Firebase Cloud Messaging) for real-time data synchronization and notifications, with a Ktor server handling specific business logic.

## Screenshots of the app:
<table style="width:100;">
  <thead>
    <tr>
      <th colspan="3" align="center">Patient App Screenshots</th>
    </tr>
    <tr>
      <th align="center">Paitent Home</th>
      <th align="center">Vaccination History</th>
      <th align="center">Appointments</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center"><img src="https://github.com/user-attachments/assets/b9144a7f-d26a-43d9-9eff-f7dfe65a0820" alt="User Home Screen" width="250"/></td>
      <td align="center"><img src="https://github.com/user-attachments/assets/8d7f2873-9305-4d8b-9150-92932539445c" alt="Vaccination History Screen" width="250"/></td>
      <td align="center"><img src="https://github.com/user-attachments/assets/f8e3cb49-2da2-4e6d-a0f7-7ac0a801a263" alt="Appointments Screen" width="250"/></td>
    </tr>
    <tr>
      <td align="center">Children</td>
      <td align="center">Add Child</td>
      <td align="center">Vaccination Table</td>
    </tr>
    <tr>
      <td align="center"><img src="https://github.com/user-attachments/assets/b8ff6b79-260d-49b2-8d2f-ff12379e53b6" alt="Children Screen" width="250" height="550"/></td>
      <td align="center"><img src="https://github.com/user-attachments/assets/3c7799cc-1174-4f48-9b48-b2ac1b9e3fd8" alt="Add Child Screen" width="250" height="550"/></td>
      <td align="center"><img src="https://github.com/user-attachments/assets/6b92daad-8b9d-4cb4-89e0-99077fd053df" alt="Vaccination Table Screen" width="250" height="550"/></td>
    </tr>
    <tr>
      <td align="center">Book Appointment</td>
    </tr>
    <tr>
      <td align="center"><img width="250" height="550" alt="Vaccination Table Screen" src="https://github.com/user-attachments/assets/248b7cd7-09a5-4b15-b6bf-0cf0142c336e" /></td>
    </tr>
  </tbody>
</table>

<table style="width:100;">
  <thead>
    <tr>
      <th colspan="3" align="center">Doctor App Screenshots</th>
    </tr>
    <tr>
      <th align="center">Doctor Schedule</th>
      <th align="center">Doctor Profile</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center"><img width="250" height="550" alt="Doctor Schedule Screen" src="https://github.com/user-attachments/assets/96f3abba-7acc-4ff0-893b-9bd33f614fbe" /></td>
      <td align="center"><img width="250" height="550" alt="Doctor Profile Screen" src="https://github.com/user-attachments/assets/19b5f255-e036-49db-9388-a23b5ee291ab" /></td>
    </tr>
  </tbody>
</table>



