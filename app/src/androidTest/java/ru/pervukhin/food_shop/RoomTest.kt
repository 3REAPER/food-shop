package ru.pervukhin.food_shop

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import ru.pervukhin.food_shop.data.database.AppDataBase
import ru.pervukhin.food_shop.data.database.CartDishDao
import ru.pervukhin.food_shop.data.database.CartDishEntity
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var db: AppDataBase
    private lateinit var dao: CartDishDao

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.getCartDishDao()
    }

    @After
    @Throws(IOException::class)
    fun clear() {
        db.close()
    }

    @Test
    fun testEmptyList() {
        runBlocking {
            launch {
                assertEquals(List<CartDishEntity?>(0,{null}), dao.getAll())
            }
        }
    }


    @Test
    fun testGetAll() {
        val item1 = CartDishEntity(1, "пельмени", 299, 500, "http://", 1)
        val item2 = CartDishEntity(2, "пицца", 699, 800, "http://", 1)
        val expected: List<CartDishEntity> = listOf(item1, item2)
        runBlocking {
            launch {
                dao.insert(item1)
                dao.insert(item2)
                assertEquals(expected, dao.getAll())
            }
        }
    }

    @Test
    fun testInsert() {
        val expected = CartDishEntity(1, "пельмени", 299, 500, "http://", 1)
        runBlocking {
            launch {
                dao.insert(expected)
                assertEquals(expected, dao.getAll()[0])
            }
        }

    }

    @Test
    fun testDelete() {
        val expected = CartDishEntity(1, "пельмени", 299, 500, "http://", 1)
        runBlocking {
            launch {
                dao.insert(expected)
                dao.delete(expected.id)
                assertNull(dao.getById(expected.id))
            }
        }

    }


    @Test
    fun testGetById() {
        val expected = CartDishEntity(1, "пельмени", 299, 500, "http://", 1)
        runBlocking {
            launch {
                dao.insert(expected)
                assertEquals(expected, dao.getById(expected.id))
            }
        }

    }

    @Test
    fun testGetByIdNull() {
        runBlocking {
            launch {
                assertNull(dao.getById(1))
            }
        }

    }


    @Test
    fun testPlusOne() {
        val expected = CartDishEntity(1, "пельмени", 299, 500, "http://", 1)
        runBlocking {
            launch {
                dao.insert(expected)
                dao.plusOne(expected.id)
                assertEquals(expected.count + 1, dao.getById(expected.id).count)
            }
        }
    }

    @Test
    fun testMinusOne() {
        val expected = CartDishEntity(1, "пельмени", 299, 500, "http://", 2)
        runBlocking {
            launch {
                dao.insert(expected)
                dao.minusOne(expected.id)
                assertEquals(expected.count - 1, dao.getById(expected.id).count)
            }
        }
    }
}