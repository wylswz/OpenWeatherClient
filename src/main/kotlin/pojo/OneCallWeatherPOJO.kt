package com.xmbsmdsj.pojo

import com.xmbsmdsj.serializers.TimezoneSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*


/**
 * @property id Weather condition id @see classpath:weatherId.txt
 * @property main Weather type: Rain, Snow, etc
 * @property description: Plain text description
 * @property icon icon id !()[http://openweathermap.org/img/wn/10d@2x.png]
 */
@Serializable
class WeatherMeta(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)

/**
 * @property dt: Time of forecasted data in Unix timestamp
 * @property sunrise: Time of sunrise in Unix timestamp [sunset]
 * @property temp: Temperature in Kelvin [feelsLike]
 * @property pressure: pressure at sea level in hPa
 * @property humidity: in %
 * @property dewPoint: The dew point is the temperature to which air must be cooled to become saturated with water vapor.
 *                      When cooled further, the airborne water vapor will condense to form liquid water (dew).
 *                      When air cools to its dew point through contact with a surface that is colder than the air,
 *                      water will condense on the surface
 * @property clouds Cloudiness in %
 * @property uvi Midday UV index
 *              The UV index is a linear[clarification needed] scale, with higher values representing a greater risk
 *              of sunburn (which is correlated with other health risks) due to UV exposure. An index of 0 corresponds
 *              to zero UV radiation, as is essentially the case at night.
 * @property visibility in metres
 * @property windSpeed: m/s
 * @property windGust A gust or wind gust is a brief increase in the speed of the wind, usually less than 20 seconds.
 *                      It is of a more transient character than a squall, which lasts minutes, and is followed by a
 *                      lull or slackening in the wind speed.[1] Generally, winds are least gusty over large water
 *                      surfaces and most gusty over rough land and near high buildings.
 *
 * @property windDeg Wind direction, degrees (meteorological)
 * @property rain "1h" -> Rain volume for last hour, mm
 * @property weather: @see [WeatherMeta]
 */
@Serializable
class Current(
    var dt: Long,
    var sunrise: Long,
    var sunset: Long,
    var temp: Double,  //kelvin
    @SerialName("feels_like") var feelsLike: Double,
    var pressure: Double,
    var humidity: Double,
    @SerialName("dew_point") var dewPoint: Double,
    var clouds: Double,
    var uvi: Double,
    var visibility: Double,
    @SerialName("wind_speed") var windSpeed: Double,
    @SerialName("wind_gust") var windGust: Double? = null,
    @SerialName("wind_deg")var windDeg: Double? = null,
    var rain: Map<String, String>? = null,
    var weather: List<WeatherMeta>
)

@Serializable
class Hourly(
    var dt: Long,
    var temp: Double,
    @SerialName("feels_like") var feelsLike: Double,
    var pressure: Double,
    var humidity: Double,
    @SerialName("dew_point") var dewPoint: Double,
    var clouds: Double,
    var visibility: Double,
    @SerialName("wind_gust")var windGust: Double? = null,
    @SerialName("wind_speed")var windSpeed: Double,
    @SerialName("wind_deg")var windDeg: Double?,
    var pop: Double,
    var rain: Map<String, String>? = null,
    var snow: Map<String, String>? = null,
    var weather: List<WeatherMeta>
)

@Serializable
class OneCallResponse(
    var lat: Double,
    var lon: Double,
    @Serializable(with = TimezoneSerializer::class) var timezone: TimeZone,
    @SerialName("timezone_offset")var timezoneOffset: Long,
    var current: Current,
    var hourly: List<Hourly>
){}