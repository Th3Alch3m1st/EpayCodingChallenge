# Coding Challenge

## Screenshots
<a href="url"><img src="https://user-images.githubusercontent.com/35175271/144740827-b786883a-4cba-4f8a-a093-9726cf1c2adb.png" height="480" width="230" />
<a href="url"><img src="https://user-images.githubusercontent.com/35175271/144740848-876dcdb2-f75d-4545-90ba-cd7032654b0b.png" height="480" width="230" />
<a href="url"><img src="https://user-images.githubusercontent.com/35175271/144740872-421d70c0-dc1e-472b-b97a-1e145211bde9.png" height="480" width="230" />
<a href="url"><img src="https://user-images.githubusercontent.com/35175271/144740888-06654143-ce44-4da0-87d9-76af1e06fdc9.png" height="480" width="230" />
<a href="url"><img src="https://user-images.githubusercontent.com/35175271/144740908-cb3c52c4-a73c-432d-963d-57152785efbf.png" height="480" width="230" />
<a href="url"><img src="https://user-images.githubusercontent.com/35175271/144740920-25ff68f2-caff-4d8b-8b4a-2dead0578d3e.png" height="480" width="230" />



## Architecture
MVVM

## Third-party libraries
- Architecture: Lifecycle, ViewModel, AppCompat, Android KTX
- UI component: Material
- Data Binding
- Coroutine, Flow
- Dependency injector: Hilt
- Networking: Retrofit, Moshi
- Unit testing: JUnit4, AssertJ, MockitoKotlin, Espresso Arch core testing (InstantTaskExecutorRule), Kotlinx coroutines test
- UI testing: Espresso
- Full list: https://github.com/Th3Alch3m1st/EpayCodingChallenge/blob/master/buildSrc/src/main/java/Dependencies.kt

## performance and code style
- ViewModel separate UI and Data layer. ViewModel allows data to survive configuration changes and improve testabilities.
- In City search different Grid spans are handled on orientation changes, In Portrait Mode 2 Grid item loaded and In Landscape mode 3 grid item loaded.
- In city search Filter class is used to search inside a list, it filters data from a large list asynchronously.
- Jetpack DataBinding to bind the layouts views and it's null safe.
- Use Kotlin DSL for gradle management - it helps better gradle management in multi module projects. And increase readability, provide code navigation and auto suggestions
- Separate network repository in different module, to maintain separation of concern design principle
- Write code maintaining SOLID principle
- User mapper class to convert network response into UI model
- Write Unit test and UI test to ensure app stability and performance
- Write some infix function to increase unit test readability
- Add documentation in UI test to explain test scenario and write short comment for unit test


## Build tools
- Android Studio Arctic Fox | 2020.3.1 Patch 3
- Gradle 7.0.2

## Troubleshoot
1. Get the error when compiling
Gradle plugin requires Java 11 but IDE uses Java 1.8 ![required-java11](https://user-images.githubusercontent.com/35175271/144035750-16757d5e-2fa1-4e9a-8007-9ca0d8ba1239.png)

One of a solution is going to Android Studio Preferences->Built, Execution, Deployment->Build Tools->Gradle-> Gradle Project and select Java 11 and try to compile again
![select-java-11](https://user-images.githubusercontent.com/35175271/144036093-103e7a65-52cf-4e56-b39b-5d4fbfcda64a.png)
