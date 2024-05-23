package com.example.amphibians.data

import org.junit.Test
import kotlinx.coroutines.test.runTest
import org.junit.Assert

class AmphibianRepositoryIntegrationTest {
    @Test
    fun fetchesDataFromApi() = runTest {
        val repo = DefaultAppContainer().amphibianRepository
        Assert.assertEquals(6, repo.getAmphibians().size)
    }
}
