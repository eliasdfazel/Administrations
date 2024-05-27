package Woocommerce

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WoocommerceRetrieve {

    fun retrieveCategories() {

        val url = URL("https://geeksempire.co/wp-json/wc/v3/products/categories?consumer_key=ck_e469d717bd778da4fb9ec24881ee589d9b202662&consumer_secret=cs_ac53c1b36d1a85e36a362855d83af93f0d377686" +
                "&per_page=99&orderby=id&order=desc")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            BufferedReader(InputStreamReader(inputStream)).use {

                val response = StringBuffer()

                var inputLine = it.readLine()

                while (inputLine != null) {

                    response.append(inputLine)
                    inputLine = it.readLine()

                }

                it.close()

                val jsonResponse = Json.parseToJsonElement(response.toString()) as JsonArray

                jsonResponse.forEach { jsonElement ->

                    val categorySlug = jsonElement.jsonObject["slug"]
                    println("Category Slug: ${categorySlug}")

                }

            }
        }

    }

    fun retrieveTags() {

         val url = URL("https://geeksempire.co/wp-json/wc/v3/products/tags?consumer_key=ck_e469d717bd778da4fb9ec24881ee589d9b202662&consumer_secret=cs_ac53c1b36d1a85e36a362855d83af93f0d377686" +
                 "&per_page=99&orderby=id&order=desc")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            BufferedReader(InputStreamReader(inputStream)).use {

                val response = StringBuffer()

                var inputLine = it.readLine()

                while (inputLine != null) {

                    response.append(inputLine)
                    inputLine = it.readLine()

                }

                it.close()

                val jsonResponse = Json.parseToJsonElement(response.toString())


            }
        }

    }

}