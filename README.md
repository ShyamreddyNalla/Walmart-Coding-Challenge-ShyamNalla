# **Explore Countries App – Walmart Assessment Project**


##  **Description**

A clean and efficient Android application that displays a list of countries using modern development practices. Built with **MVVM** and **Clean Architecture**, the app consumes a remote API and presents country details through a sleek XML-based interface with **RecyclerView**.

---

<img alt="screenshot" height="600" src="https://github.com/user-attachments/assets/090c63ed-fabd-4f40-b519-2f0d4aca8d8c" width="280"/>

##  **Features**

-  Fetch and display a list of countries with details.  
-  Handle loading, success, and error states gracefully.  
-  Responsive UI with RecyclerView and ViewBinding.  
-  ViewModel manages state using Kotlin Coroutines and Flow.  
-  Unit tests to ensure business logic correctness.

---

##  **Technologies Used**

- **Kotlin**: Primary programming language  
- **Clean Architecture**: Separation of concerns and scalability  
- **MVVM**: Modern architecture for maintainable code  
- **Coroutines & Flow**: Asynchronous data handling  
- **LiveData**: Observable UI state management  
- **RecyclerView (XML)**: Efficient and customizable list UI  
- **MockK + JUnit**: Unit testing the ViewModel and use case  
- **ViewModel + ViewBinding**: Lifecycle-aware and type-safe UI updates

## **Clean Architecture – MVVM Layered Structure**
```
com.shyam.walmart_countries_assessment/

├── data/
│   ├── constants/
│   ├── entity/
│   ├── mappers/
│   ├── remote/
│   └── repository/
├── domain/
│   ├── Country.kt
│   ├── GetCountriesUseCase.kt
│   └── IRepository.kt
├── presentation/
│   ├── adaptor/
│   ├── view/
│   └── viewmodel/
├── utils/
│   ├── InternetCheck.kt
│   └── UiState.kt
└── test/
    ├── IRepositoryTest.kt
    ├── UseCaseTest.kt
    └── ViewModelTest.kt
```

