# zemonga tecnical test by Jhon Trujillo
zemonga final test

##Getting started
This app bring post from an api server and show it using some rules defined by zemonga tecnical test.
the api works with this endpoint 

* [GET post](https://jsonplaceholder.typicode.com/posts) 
* [GET User](https://jsonplaceholder.typicode.com/users) 
* [GET comments?postId={id}](https://jsonplaceholder.typicode.com/comments?postId=1) 

At the moment the database is created, the 'SyncDataWorker' worker is trigger and in that point all the records are downloaded and 
save it into the database.

##Architecture patterns

This app try to use the clean architeture aproach to create three decoupled layers, each one with a funtion. 

put image here 

So, you have three layers

data: in this module we have services and data base
domain: Businnes rules and models (for this project, we don't have special bussiness rules, so we only have the domain models)
presentation: Android framework and UI classes

The modules interact between them using mappers and interfaces, avoiding to use directly implementations. We use the depedency inversion
principle and boundaries interfaces.

[Dependency Inversion Principle (DIP)](https://stackify.com/dependency-inversion-principle/)

The Repository classes and viewModel implementations are injected using a factory strategy but It is easy to be replaced
with dagger2 or koin, because almost all clases respect the injection depedency pattern.

For the presentation layer, we are using MVVM
* [MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel)

So, at the end, we have this implementation.



## Libraries
* [Room](https://developer.android.com/jetpack/androidx/releases/room)
* [Workout manager](https://developer.android.com/topic/libraries/architecture/workmanager/advanced/coroutineworker)
* [Navigation Component]https://developer.android.com/guide/navigation/navigation-getting-started)
* [ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2) 
* [Data Binding](https://developer.android.com/topic/libraries/data-binding) 
* [Retrofit2](https://square.github.io/retrofit/)
* [Coroutines](https://developer.android.com/kotlin/coroutines)

## Testing
* [Junit](https://developer.android.com/training/testing/unit-testing/local-unit-tests)
* [Mockito](https://site.mockito.org/)
* [jacoco](https://www.eclemma.org/jacoco/)


