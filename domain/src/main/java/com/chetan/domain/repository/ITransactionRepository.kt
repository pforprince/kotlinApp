package com.chetan.domain.repository

import com.chetan.domain.domainmodel.Result
import com.chetan.domain.domainmodel.NoteTransaction

interface ITransactionRepository {
    suspend fun getTransactions():Result<Exception, List<NoteTransaction>>

    suspend fun deleteTransactions(): Result<Exception, Unit>

    suspend fun updateTransactions(transaction: NoteTransaction):Result<Exception, Unit>
}