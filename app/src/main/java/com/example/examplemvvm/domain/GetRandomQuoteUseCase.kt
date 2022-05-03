package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRespository
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRespository ){

     suspend operator fun invoke(): Quote?{
        val quotes = repository.getAllQuotesFromDatabase()
        if(!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }

}