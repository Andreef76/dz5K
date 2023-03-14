import WallService.add
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class WallServiceTest {
    @Test
    fun updateExistingTrue() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(1, 1, 1, 1, 1, "1", false, false, false,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts))
        service.add(Post(3, 0, 0, 0, 0, "0", false, false, false,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts))
        service.add(Post(5, 2, 2, 2, 2, "2", false, false, false,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts))
        service.add(Post(7, 2, 2, 2, 2, "2", false, false, false,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts))
        // создаём информацию об обновлении
        val update = Post(7, 5, 5, 5, 5, "5", true, true, true,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts)
        // выполняем целевое действие
        val result = service.update(update)
        // проверяем результат (используйте assertTrue или assertFalse)
        assertTrue(result)
    }

    @Test
    fun updateExistingFalse() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(1, 1, 1, 1, 1, "1", false, false, false,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts))
        service.add(Post(0, 0, 0, 0, 0, "0", false, false, false,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts))
        service.add(Post(5, 2, 2, 2, 2, "2", false, false, false,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts))
        // создаём информацию об обновлении
        val update = Post(3, 5, 5, 5, 5, "5", true, true, true,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts)
        // выполняем целевое действие
        val result = service.update(update)
        // проверяем результат (используйте assertTrue или assertFalse)
        assertFalse(result)
    }

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }
    @Test
    fun addTest() {

        val post = (Post(0, 10, 1, 1, 1, "НЕТ", true, true, true,0,0,"0",0, 0,0,true,0, likes, comment, copyrights, repost, postSources, geos, view, donuts))
        val result = add(post)

        assertEquals(1, result.id)
    }
}

