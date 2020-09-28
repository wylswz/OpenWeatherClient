# Openweather Client

Nothing interesting

This client encapsulates some apis from https://openweathermap.org/

To initialize a client

```kotlin
val oneCallApi = Client("your token").oneCallApi()
val weatherOverview = oneCallApi.currentWeather(lat, lon).execute()

// do sth with weatherOverview
```