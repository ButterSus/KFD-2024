package com.buttersus

import com.buttersus.client.Client

fun main() {
    val mockClient = Client()
    val response = mockClient.perform(200, "OK")
        .andExpect {
            status {
                isOk()
            }
            body {
                isNotNull()
            }
        }
        .andDo { response ->
            println(response)
        }
        .response
}
