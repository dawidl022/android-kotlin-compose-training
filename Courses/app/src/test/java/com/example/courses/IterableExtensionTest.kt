package com.example.courses

import org.junit.Assert.assertEquals
import org.junit.Test

class IterableExtensionTest {
    @Test
    fun pairs_givenEmptyList_returnsEmptyList() {
        val emptyList = emptyList<Int>()

        assertEquals(emptyList, emptyList.pairs())
    }

    @Test
    fun pairs_givenListWithSingleElement_returnsListWithSingleUnmatchedPair() {
        val list = listOf(1)

        assertEquals(listOf(Pair(1, null)), list.pairs())
    }

    @Test
    fun pairs_givenListWithTwoElements_returnsListWithSingleMatchedPair() {
        val list = listOf(1, 2)

        assertEquals(listOf(Pair(1, 2)), list.pairs())
    }

    @Test
    fun pairs_givenListWithThreeElements_returnsListWithTwoPairs() {
        val list = listOf(1, 2, 3)

        assertEquals(listOf(Pair(1, 2), Pair(3, null)), list.pairs())
    }
}
