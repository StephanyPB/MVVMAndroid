package com.example.examplemvvm.data

import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.network.QuoteService
import com.example.examplemvvm.domain.model.Quote
import com.example.examplemvvm.domain.model.toDomain
import javax.inject.Inject


class QuoteRespository @Inject constructor(private val api: QuoteService,
    private val quoteDao: QuoteDao) {


    suspend fun getAllQuotesFromApi():List<Quote>{
        val response:List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun  getAllQuotesFromDatabase():List<Quote>{
    val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain()}
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuote(){
        quoteDao.deleteAllQuotes()
    }
}