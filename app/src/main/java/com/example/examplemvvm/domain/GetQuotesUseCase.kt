package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRespository
import com.example.examplemvvm.data.database.entities.toDatabase
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase@Inject constructor(private  val repository: QuoteRespository) {
    suspend  operator fun invoke():List<Quote>{
        val quotes = repository.getAllQuotesFromApi()

        return if(quotes.isNotEmpty()){
            repository.clearQuote()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }
}
