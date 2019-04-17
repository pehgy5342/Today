package com.example.today.mydata

class WeatherData {
    data class today(
        val success: String,
        val result: ResultBean,
        val records: Records
    ) {

        data class ResultBean(
            val resource_id: String,
            val fields: List<FieldsBean>
        ) {

            data class FieldsBean(
                val id: String,
                val type: String
            )
        }

        data class Records(
            val datasetDescription: String,
            val location: List<LocationBean>
        ) {

            data class LocationBean(
                val locationName: String,
                val weatherElement: List<WeatherElementBean>
            ) {

                data class WeatherElementBean(
                    val elementName: String,
                    val time: List<TimeBean>
                ) {

                    data class TimeBean(
                        val startTime: String,
                        val endTime: String,
                        val parameter: ParameterBean
                    ) {

                        data class ParameterBean(
                            val parameterName: String,
                            val parameterValue: String,
                            val parameterUnit: String
                        )
                    }
                }
            }
        }
    }

    data class Aweather(
        val locationName: String,
        val Wx: String,
        val AT: String,
        val T: String,
        val CI: String,
        val PoP6h: String
    )

    data class week(val myDate: String, val temp: String, val PoP: String)



}
