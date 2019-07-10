package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import ru.skillbranch.devintensive.extensions.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Cena")
//        val user3 = User("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true)

//        user.printMe()
//        user2.printMe()
//        user3.printMe()

        println("$user")
    }

    @Test
    fun test_factory() {
        //val user = User.makeUser("John Cena")
        //val user2 = User.makeUser("John Wick")
        val user3 = User.makeUser("John Silverhand")
        //val user = user3.copy()
        print(user3)
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")
        fun getUserInfo() = user
        //val (id, firstName, lastName) = getUserInfo()
        //println("$id, $firstName, $lastName")
        //println("${user.c}")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        //val user2 = user.copy()
    }

    @Test
    fun test_parsing_full_name() {
        println(Utils.parseFullName(null))   //null null
        println(Utils.parseFullName(""))     //null null
        println(Utils.parseFullName(" "))    //null null
        println(Utils.parseFullName("John")) //John null
    }

    @Test
    fun test_date() {
        println(Date().format())                //14:00:00 27.06.19
        println(Date().format("HH:mm")) //14:00
    }

    @Test
    fun test_date_change() {
        println(Date().add( 2, TimeUnits.SECOND)) //Thu Jun 27 14:00:02 GST 2019
        println(Date().add(-4, TimeUnits.DAY))    //Thu Jun 23 14:00:00 GST 2019

        println( Date().add(-2, TimeUnits.HOUR)?.humanizeDiff() )  //2 часа назад
        println(Date().add(-5, TimeUnits.DAY)?.humanizeDiff())   //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE)?.humanizeDiff()) //через 2 минуты
        println(Date().add(7, TimeUnits.DAY)?.humanizeDiff())    //через 7 дней
        println(Date().add(-400, TimeUnits.DAY)?.humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY)?.humanizeDiff())  //более чем через год
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Mikhail Ivanov")
        val txtMessage = BaseMessage.makeMessage(user, chat = Chat("test"), payload = "any text msg", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, chat = Chat("test"), payload = "any image url", type = "image")
        // Василий отправил сообщение "any text message" только что
        val oneMessage = BaseMessage.makeMessage(user, chat = Chat("test"), date=Date(), payload = "any text message", type = "text")
        // Василий получил изображение "https://anyurl.com" 2 часа назад
        val twoMessage = BaseMessage.makeMessage(user, chat= Chat("test"), date=Date(), payload = "https://anyurl.com", type = "image", isIncoming=true)

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
        when(oneMessage){
            is BaseMessage  -> println("this is base message")
            is TextMessage  -> println("this is text message")
            is ImageMessage -> println("this is image url")
        }
    }

    @Test
    fun hometask_2_User_Builder_works()
    {
        val user:User = User.Builder().id("TEST_ID")
            .firstName("John")
            .lastName("Doe")
            .avatar(null)
            .rating(0)
            .respect(0)
            .lastVisit(Date())
            .isOnline(false)
            .build() // должен вернуть объект User
        println(user)
    }
}
