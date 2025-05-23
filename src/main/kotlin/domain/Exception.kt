package org.example.domain
abstract class WeatherAppException(message: String) : Exception(message)

class InvalidCityException(message: String) : WeatherAppException(message)
class InvalidRegionException(message: String) : WeatherAppException(message)
class InvalidCountryException(message: String) : WeatherAppException(message)
class InvalidLocalTimeException(message: String) : WeatherAppException(message)
class InvalidTemperatureException(message: String) : WeatherAppException(message)
class InvalidDescriptionException(message: String) : WeatherAppException(message)
