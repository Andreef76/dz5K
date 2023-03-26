import java.lang.RuntimeException
import java.util.Objects

data class Post(
    var id: Int,                // Идентификатор владельца стены
    val ownerId: Int,          // Идентификатор владельца стены
    val fromId: Int,           // Идентификатор автора записи
    val createdBy: Int,        // Идентификатор администратора, который опубликовал запись
    val date: Int,              // Время публикации записи
    val text: String,           // Текст записи
    val canPin: Boolean,       //Информация о том, может ли текущий пользователь закрепить запись
    val canDelete: Boolean,    // Информация о том, может ли текущий пользователь удалить запись
    val canEdit: Boolean,       // Информация о том, может ли текущий пользователь редактировать запись
    val replyPostId: Int? = null,       //  Идентификатор записи, в ответ на которую была оставлена текущая.
    val friendsOnly: Int? = null,       // если запись была создана с опцией «Только для друзей»
    val postType: String? = null,       // Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val signerId: Int? = null,         // Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем;
    val isPinned: Int,           // Информация о том, что запись закреплена.
    val markedAsAds: Int,        // Информация о том, содержит ли запись отметку «реклама» (1 — да, 0 — нет).
    val isFavorite: Boolean = false,     // true, если объект добавлен в закладки у текущего пользователя.
    val postponedId: Int,         // Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере
    //var copyHistory: Array<Reposts> = emptyArray(),   /*   Массив, содержащий историю репостов для записи. Возвращается только в том случае, если запись является репостом.
//Каждый из объектов массива, в свою очередь, является объектом-записью стандартного формата.*/
    val like: Like?,
    val commentsInfo: CommentsInfo?,
    val copyright: Copyright?,
    val reposts: Reposts?,
    val postSource: PostSource?,
    val geo: Geo?,
    val views: Views?,
    val donut: Donut?,
    var attachments: Array<Attachment> = emptyArray()     // хранит в массиве объекты класса Attachment
)

data class Like(
    val count: Int,              //  число пользователей, которым понравилась запись
    val userLikes: Boolean,     //  наличие отметки «Мне нравится» от текущего пользователя
    val canLike: Boolean,       //  информация о том, может ли текущий пользователь поставить отметку «Мне нравится»
    val canPublish: Boolean     // информация о том, может ли текущий пользователь сделать репост записи
)

val likes = Like(count = 0, userLikes = true, canLike = true, canPublish = true)


data class CommentsInfo(                    // Информация о комментариях к записи
    val count: Int,                // количество комментариев
    val canPost: Boolean,          //информация о том, может ли текущий пользователь комментировать запись
    val groupsCanPost: Boolean,    // информация о том, могут ли сообщества комментировать запись;
    val canClose: Boolean,         // может ли текущий пользователь закрыть комментарии к записи;
    val canOpen: Boolean
)       // может ли текущий пользователь открыть комментарии к записи.)

val commentInfo = CommentsInfo(count = 0, canPost = true, groupsCanPost = true, canClose = true, canOpen = true)


data class Copyright(                  // Источник материала, объект с полями
    val id: Int,
    val link: String,
    val name: String,
    val type: String
)

val copyrights = Copyright(id = 0, link = "0", name = "0", type = "0")


data class Reposts(                   // Информация о репостах записи («Рассказать друзьям»)
    val count: Int,              //число пользователей, скопировавших запись
    val userReposted: Boolean    // наличие репоста от текущего пользователя
)

var repost = Reposts(count = 0, userReposted = true)
fun addRepostArray(post: Post) {        /*   Массив, содержащий историю репостов для записи.
                                            Возвращается только в том случае, если запись является репостом.
                                            Каждый из объектов массива, в свою очередь, является объектом-записью стандартного формата.*/
    WallService.copyHistory += repost
}


data class PostSource(
    val type: String,      /*
  .запись создана посредством отправки SMS-сообщения на специальный номер.*/
    val platform: String,     //Название платформы, если оно доступно. Возможные значения: android; iphone; wphone.
    val data: String,        /*
.т опросов (для type = widget).*/
    val url: String    //URL ресурса, с которого была опубликована запись.
)

val postSources = PostSource(type = "vk", platform = "android", data = "like", url = "aaa.com")


data class Geo(
    val type: String,         // тип места;
    val coordinates: String, // координаты места;
    val plase: Plase
)

val geos = Geo(
    type = "0",
    coordinates = "0",
    Plase(
        id = 0,
        title = "0",
        latitude = 0,
        longitude = 0,
        created = 0,
        icon = "0",
        checkins = 0,
        updated = 0,
        type = 0,
        country = 0,
        city = 0,
        address = "0"
    )
)

data class Plase(             // описание места (если оно добавлено). Объект места. ВХОДИТ В СОСТАВ ОБЪЕКТА GEO
    val id: Int,         // Идентификатор места.
    val title: String,   // Название места.
    val latitude: Int,   // Географическая широта, заданная в градусах (от -90 до 90).
    val longitude: Int,   // Географическая долгота, заданная в градусах (от -90 до 90).
    val created: Int,     // Дата создания места в Unixtime.
    val icon: String,     // Иконка места, URL изображения.
    val checkins: Int,    // Число отметок в этом месте.
    val updated: Int,     // Дата обновления места в Unixtime.
    val type: Int,        // Тип места.
    val country: Int,     // Идентификатор страны.
    val city: Int,        // Идентификатор города.
    val address: String   // Адрес места.
)
//val plases = Plase(id = 0, title = "0", latitude = 0, longitude = 0, created = 0, icon = "0", checkins = 0, updated = 0, type = 0, country = 0,city = 0, address = "0")

data class Views(        // информация о просмотрах записи. Объект с единственным полем
    val count: Int      // число просмотров записи.
)

val view = Views(count = 0)

data class Donut(
    val isDonut: Boolean,       // запись доступна только платным подписчикам VK Donut;
    val paidDuration: Int,       // время, в течение которого запись будет доступна только платным подписчикам VK Donut;
    val placeholder: Objects?, // добавить объект как поле - placeholder (object) — заглушка для пользователей, которые не оформили подписку VK Donut. Отображается вместо содержимого записи.
    val canPublishFreeCopy: Boolean,   // можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut;
    val editMode: String          /* информация о том, какие значения VK Donut можно изменить в записи. Возможные значения:
                          •  all — всю информацию о VK Donut.
                          •  duration — время, в течение которого запись будет доступна только платным подписчикам VK Donut.*/
)

val donuts = Donut(isDonut = true, paidDuration = 0, placeholder = null, canPublishFreeCopy = true, editMode = "all")


class PostNotFoundException(message: String) : RuntimeException(message)
class CommentNotFoundException(message: String) : RuntimeException(message)
class ReasonNotFoundException(message: String) : RuntimeException(message)
data class Comment(             // Комментарий на стене
    val id: Int,                //  Идентификатор комментария.
    val fromId: Int,            // Идентификатор автора комментария.
    val date: Int,              // Дата создания комментария в формате Unixtime.
    val text: String            // Текст комментария.
)
val comment = Comment(1, 1, 1, "1")

object WallService {                                              //  Объект WallService, который хранит посты в массиве
    var posts = emptyArray<Post>()                     // хранит посты в массиве
    var copyHistory = emptyArray<Reposts>()         // хранит Репосты в массиве
    var comments = emptyArray<Comment>()  // хранит комментарии к существующим постам
    private var lastIg = 0
    private var commentIdLast = 0
    var invalidComment = emptyArray<Comment>()    // хранит недопустимые комментарии

    fun clear() {    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        posts = emptyArray()
        comments = emptyArray()
        invalidComment = emptyArray()
        commentIdLast = 0
        lastIg = 0        // также здесь нужно сбросить счетчик для id постов, если он у вас используется
    }   //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

     fun reports (commentId: Int, reason: Int) : Comment {
         var count = 0
         for ((index, comment) in comments.withIndex()) {
             if (comment.id == commentId) {
                 count++
             }
             if (count == 0) throw CommentNotFoundException("Комментарий не найден")

             when (reason) {
                 in (1..8).minus(7) -> invalidComment += comment
                 else -> throw ReasonNotFoundException("Комментарий не является недопустимым")
             }

         }
         return invalidComment.last()
     }

//Создаём функцию в которую передаёте id комментария и причину, в функции проверяете есть ли такой комментарий
// и существует ли такая причина, если нет - выбрасываете ошибку, если да - добавляете в новый массив и возвращаем true,
// либо сам репорт.

    fun createComment(postId: Int, comment: Comment): Comment {
        for ((index, post) in posts.withIndex()) {
            if (postId == post.id) {
                comments += comment.copy(id = commentIdLast++)
                return comments.last()
            }
        }
        throw PostNotFoundException("Пост с таким ID не найден")
    }

    fun add(post: Post): Post {       //  Метод для создания записи
        posts += post.copy(id = lastIg++)
        return posts.last()
    }

    fun update(post: Post): Boolean {    //  Метод для обновления записи
        for ((index, newPost) in posts.withIndex()) {
            if (post.id == posts[index].id) {
                posts[index] = newPost.copy(
                    post.id,
                    post.ownerId,
                    post.fromId,
                    post.createdBy,
                    post.date,
                    post.text,
                    post.canPin,
                    post.canDelete,
                    post.canEdit,
                    post.replyPostId,
                    post.friendsOnly,
                    post.postType,
                    post.signerId,
                    post.isPinned,
                    post.markedAsAds,
                    post.isFavorite,
                    post.postponedId,
                    post.like,
                    post.commentsInfo,
                    post.copyright,
                    post.reposts,
                    post.postSource,
                    post.geo,
                    post.views,
                    post.donut
                )
                return true
            }
        }
        return false
    }
}


fun main() {
    WallService.add(Post(0,10,1,1,1,"НЕТ",true,true,true,
            0,
            0,
            "0",
            0,
            0,
            0,
            true,
            0,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    )
    println(WallService.posts[0])
    WallService.add(
        Post(
            0,
            17,
            17,
            17,
            17,
            "DDD",
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
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    )
    println(WallService.posts[1])
    WallService.add(
        Post(
            0,
            22,
            22,
            22,
            22,
            "DDD",
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
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    )
    println(WallService.posts[2])
    WallService.add(
        Post(
            3,
            44,
            44,
            44,
            44,
            "DDD",
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
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    )
    println(WallService.posts[3])
    println()
    WallService.update(
        Post(
            2,
            2,
            2,
            2,
            2,
            "ДА",
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
    println(WallService.posts[2])
    println(WallService.createComment(2, comment))  // вот же он йд 2
    println(WallService.createComment(3, comment))  //то что в main() ничего общего с тестами не имеет. я понимаю. я сейчас про то что тут не работает
    println(WallService.createComment(1, comment))
    println()
    println(WallService.reports(3,5))


}


