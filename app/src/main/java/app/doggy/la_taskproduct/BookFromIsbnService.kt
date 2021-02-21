package app.doggy.la_taskproduct

import retrofit2.http.GET
import retrofit2.http.Query

interface BookFromIsbnService {

    @GET("volumes")
    suspend fun getBook(@Query("q") q: String): BookFromIsbn

}