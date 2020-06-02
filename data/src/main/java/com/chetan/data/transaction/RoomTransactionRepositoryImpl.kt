package com.chetan.data.transaction

import com.chetan.data.note.registered.RegisteredTransactionDao
import com.chetan.data.toNoteTransactionListFromRegistered
import com.chetan.data.toRegisteredRoomTransaction
import com.chetan.domain.domainmodel.NoteTransaction
import com.chetan.domain.domainmodel.Result
import com.chetan.domain.repository.ITransactionRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking

class RoomTransactionRepositoryImpl(val transactionDao: RegisteredTransactionDao) : ITransactionRepository {
    override suspend fun getTransactions():
            Result<Exception, List<NoteTransaction>> = runBlocking(IO) {
        Result.build {
            transactionDao.getTransactions().toNoteTransactionListFromRegistered()
        }
    }

    override suspend fun deleteTransactions(): Result<Exception, Unit> = runBlocking(IO) {
        Result.build {
            transactionDao.deleteAll()
        }
    }

    override suspend fun updateTransactions(transaction: NoteTransaction):
            Result<Exception, Unit> = runBlocking(IO) {
        Result.build {
            transactionDao.insertOrUpdateTransaction(
                    transaction.toRegisteredRoomTransaction
            ).toUnit()
        }
    }

    private fun Long.toUnit(): Unit = Unit
}