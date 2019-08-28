package com.kartum.datasource

import com.kartum.network.APIinterface

class CommonRepository(apiInterface: APIinterface){
//    var apiInterface: APIinterface? = apiInterface
//
//    fun getAdvertisement(advertiseDataModel: AdvertiseDataModel): MutableLiveData<AdvertisementData?> {
//        Debug.e("tag","searchEvent" + Gson().toJson(advertiseDataModel))
//        val data = MutableLiveData<AdvertisementData?>()
//        val call = apiInterface!!.getAdvertisement(advertiseDataModel)
//        call.enqueue(object : Callback<AdvertisementData?> {
//            override fun onFailure(call: Call<AdvertisementData?>, t: Throwable) {
//                data.value = AdvertisementData(Constant.getFailureCode())
//            }
//
//            override fun onResponse(call: Call<AdvertisementData?>, response: Response<AdvertisementData?>) {
//                try {
//                    if (response.isSuccessful) {
//                        val findEventData = response.body()
//                        data.value = findEventData
//                    } else {
//                        try {
//                            val inputAsString = response.errorBody()!!.source().inputStream().bufferedReader().use { it.readText() }
//                            Debug.e("Input",inputAsString)
//                            val sb = StringBuilder()
//                            sb.append(inputAsString)
//                            val findEventData = Gson().fromJson<AdvertisementData?>(JSONObject(inputAsString).toString(),object : TypeToken<AdvertisementData?>() {}.type)
//                            findEventData!!.statusCode = Constant.getFailureCode()
//                            data.value = findEventData
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                            data.value = AdvertisementData(Constant.getFailureCode())
//                        }
//                    }
//                } catch (e: Exception) {
//                    data.value = AdvertisementData(Constant.getFailureCode())
//                }
//            }
//        })
//        return data
//    }


}