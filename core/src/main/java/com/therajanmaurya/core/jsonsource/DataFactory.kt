package com.therajanmaurya.core.jsonsource

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

/**
 * DataFactory is reading the input stream of the local resources
 * and parsing the json file data into java object.
 */
class DataFactory {

    /**
     * Note : This Generic Method DeSerialize Both Object and List Type Json in POJO
     *
     *
     * Note : Do Not use Array [] in POJO classes for of any element initialization,
     * Use Instead ArrayList.
     *
     * @param listModel Class of the List Model
     * @param jsonName  Name of the Json in resources
     * @param <T>       return type
     * @return Return the List of the listModel by Deserializing the Json of resources
     * @Example of Deserializing List Type Json
     *
     *
     * TestDataFactory mTestDataFactory = new TestDataFactory();
     *
     *
     * List<Object> listObject = mTestDataFactory.convertJsonToDataObject(
     * new TypeToken<List></List><Object>>(){}, "ListObject.json")
     * @Example Of Deserializing Object Type Json
     *
     *
     * Object object = mTestDataFactory.convertJsonToDataObject(
     * new TypeToken<Object>(){}, "Object.json")
    </Object></Object></Object></T> */
    fun <T> convertJsonToDataObject(listModel: TypeToken<T>, jsonName: String): T {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(jsonName)
        val jsonReader = JsonReader(InputStreamReader(inputStream))
        return Gson().fromJson(jsonReader, listModel.type)
    }
}
