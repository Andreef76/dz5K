import WallService.add
import WallService.createComment
import WallService.invalidComment
import WallService.reports
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class WallServiceTest {
    @Test
    fun updateExistingTrue() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(
            Post(
                1,
                1,
                1,
                1,
                1,
                "1",
                false,
                false,
                false,
                0,
                0,
                "0",
                0,
                0,
                0,
                true,
                0,
                likes,
                commentInfo,
                copyrights,
                repost,
                postSources,
                geos,
                view,
                donuts
            )
        )
        service.add(
            Post(
                3,
                0,
                0,
                0,
                0,
                "0",
                false,
                false,
                false,
                0,
                0,
                "0",
                0,
                0,
                0,
                true,
                0,
                likes,
                commentInfo,
                copyrights,
                repost,
                postSources,
                geos,
                view,
                donuts
            )
        )
        service.add(
            Post(
                5,
                2,
                2,
                2,
                2,
                "2",
                false,
                false,
                false,
                0,
                0,
                "0",
                0,
                0,
                0,
                true,
                0,
                likes,
                commentInfo,
                copyrights,
                repost,
                postSources,
                geos,
                view,
                donuts
            )
        )
        service.add(
            Post(
                7,
                2,
                2,
                2,
                2,
                "2",
                false,
                false,
                false,
                0,
                0,
                "0",
                0,
                0,
                0,
                true,
                0,
                likes,
                commentInfo,
                copyrights,
                repost,
                postSources,
                geos,
                view,
                donuts
            )
        )
        // создаём информацию об обновлении
        val update = Post(
            3,
            5,
            5,
            5,
            5,
            "5",
            true,
            true,
            true,
            0,
            0,
            "0",
            0,
            0,
            0,
            true,
            0,
            likes,
            commentInfo,
            copyrights,
            repost,
            postSources,
            geos,
            view,
            donuts
        )
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
        service.add(
            Post(
                1,
                1,
                1,
                1,
                1,
                "1",
                false,
                false,
                false,
                0,
                0,
                "0",
                0,
                0,
                0,
                true,
                0,
                likes,
                commentInfo,
                copyrights,
                repost,
                postSources,
                geos,
                view,
                donuts
            )
        )
        service.add(
            Post(
                0,
                0,
                0,
                0,
                0,
                "0",
                false,
                false,
                false,
                0,
                0,
                "0",
                0,
                0,
                0,
                true,
                0,
                likes,
                commentInfo,
                copyrights,
                repost,
                postSources,
                geos,
                view,
                donuts
            )
        )
        service.add(
            Post(
                5,
                2,
                2,
                2,
                2,
                "2",
                false,
                false,
                false,
                0,
                0,
                "0",
                0,
                0,
                0,
                true,
                0,
                likes,
                commentInfo,
                copyrights,
                repost,
                postSources,
                geos,
                view,
                donuts
            )
        )
        // создаём информацию об обновлении
        val update = Post(
            3,
            5,
            5,
            5,
            5,
            "5",
            true,
            true,
            true,
            0,
            0,
            "0",
            0,
            0,
            0,
            true,
            0,
            likes,
            commentInfo,
            copyrights,
            repost,
            postSources,
            geos,
            view,
            donuts
        )
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
        val post = Post(
            0,
            10,
            1,
            1,
            1,
            "НЕТ",
            true,
            true,
            true,
            0,
            0,
            "0",
            0,
            0,
            0,
            true,
            0,
            likes,
            commentInfo,
            copyrights,
            repost,
            postSources,
            geos,
            view,
            donuts
        )
        add(post)
        val result = add(post)
        assertEquals(1, result.id)
    }

    @Test
    fun createCommentTest() {
        val post = Post(
            4,
            10,
            1,
            1,
            1,
            "НЕТ",
            true,
            true,
            true,
            0,
            0,
            "0",
            0,
            0,
            0,
            true,
            0,
            likes,
            commentInfo,
            copyrights,
            repost,
            postSources,
            geos,
            view,
            donuts
        )
        WallService.add(post)
        WallService.add(post)
        WallService.add(post)
        WallService.add(post)
        val result = createComment(3, comment)     // при значении postId > 3 будет ошибка
        assertEquals("${comment.text}", result.text)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentTestThrow() {
        val post = Post(
            2,
            1,
            1,
            1,
            1,
            "НЕТ",
            true,
            true,
            true,
            0,
            0,
            "0",
            0,
            0,
            0,
            true,
            0,
            likes,
            commentInfo,
            copyrights,
            repost,
            postSources,
            geos,
            view,
            donuts
        )
        WallService.add(post)
        WallService.add(post)
        WallService.add(post)
        createComment(3, comment) //  при значении postId <= 2 будет ошибка т.к. 3 у нас объекта
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportsTestNoId() {
        val comment = Comment(1, 1, 1, "1")
        WallService.comments += Comment(0, 1, 1, "1")
        WallService.reports(1, 1)

    }

    @Test(expected = ReasonNotFoundException::class)
    fun reportsTestFine() {
        val comment = Comment(1, 1, 1, "1")
        WallService.comments += Comment(1, 1, 1, "1")
        WallService.reports(1, 7)
    }

    @Test
    fun reportsTestBlockId() {
        val comment = Comment(1, 1, 1, "1")
        WallService.comments += comment
        val result = reports(1,1)
        assertEquals("1", result.text)
    }

}