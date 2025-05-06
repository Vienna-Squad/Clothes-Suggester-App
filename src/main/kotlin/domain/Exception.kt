package org.example.domain
abstract class WeatherAppException(message: String) : Exception(message)

class WeatherApiException(message: String) : WeatherAppException(message)
class InvalidCityException(message: String) : WeatherAppException(message)
class CityNotFoundException(message: String) : WeatherAppException(message)

