package com.example.today.mydata

class WeatherData {
    data class today(
        var success: String,
        var result: ResultBean,
        var records: Records
    ) {

        data class ResultBean(
            var resource_id: String,
            var fields: List<FieldsBean>
        ) {

            data class FieldsBean(
                var id: String,
                var type: String
            )
        }

        data class Records(
            var datasetDescription: String,
            var location: List<LocationBean>
        ) {

            data class LocationBean(
                var locationName: String,
                var weatherElement: List<WeatherElementBean>
            ) {

                data class WeatherElementBean(
                    var elementName: String,
                    var time: List<TimeBean>
                ) {

                    data class TimeBean(
                        var startTime: String,
                        var endTime: String,
                        var parameter: ParameterBean
                    ) {

                        data class ParameterBean(
                            var parameterName: String,
                            var parameterValue: String,
                            var parameterUnit: String
                        )
                    }
                }
            }
        }
    }

    data class Aweather(
        var locationName: String,
        var Wx: String,
        var AT: String,
        var T: String,
        var CI: String,
        var PoP6h: String
    )

    data class week(var myDate: String, var temp: String, var PoP: String)



}
