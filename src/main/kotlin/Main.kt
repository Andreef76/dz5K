data class Post(
    var id: Int,                // Идентификатор владельца стены
    val ownerId: Int,          // Идентификатор владельца стены
    val fromId: Int,           // Идентификатор автора записи
    val createdBy: Int,        // Идентификатор администратора, который опубликовал запись
    val date: Int,              // Время публикации записи
    val text: String,           // Текст записи
    val canPin: Boolean,       //Информация о том, может ли текущий пользователь закрепить запись
    val canDelete: Boolean,    // Информация о том, может ли текущий пользователь удалить запись
    val canEdit: Boolean       // Информация о том, может ли текущий пользователь редактировать запись
)

object WallService {        //  Объект WallService, который хранит посты в массиве
    var posts = emptyArray<Post>()
    fun clear() {    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        posts = emptyArray()    // метод очистки clear
        // также здесь нужно сбросить счетчик для id постов, если он у вас используется
    }   //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    fun add(post: Post): Post {       //  Метод для создания записи
        if (post.id == 0) post.id += 1
        for ((index, newPost) in posts.withIndex()) {
            if (post.id != posts[index].id) {
                var newId = post.id
                val newPost = Post(
                    newId,
                    post.ownerId,
                    post.fromId,
                    post.createdBy,
                    post.date,
                    post.text,
                    post.canPin,
                    post.canDelete,
                    post.canEdit
                )
            } else {
                post.id += 1
            }
        }
        posts += post
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
                    post.canEdit
                )
                return true
            }
        }
        return false
    }
}

class Like(
    val count: Int,              //  число пользователей, которым понравилась запись
    val user_likes: Boolean,     //  наличие отметки «Мне нравится» от текущего пользователя
    val can_like: Boolean,       //  информация о том, может ли текущий пользователь поставить отметку «Мне нравится»
    val can_publish: Boolean     // информация о том, может ли текущий пользователь сделать репост записи
)
val likes = Like(count = 0, user_likes = true, can_like = true, can_publish = true)

fun main() {
    WallService.add(Post(0, 10, 1, 1, 1, "НЕТ", true, true, true))
    println(WallService.posts[0])
    WallService.add(Post(0, 17, 17, 17, 17, "DDD", true, true, true))
    println(WallService.posts[1])
    WallService.add(Post(0, 22, 22, 22, 22, "DDD", true, true, true))
    println(WallService.posts[2])
    WallService.add(Post(3, 44, 44, 44, 44, "DDD", true, true, true))
    println(WallService.posts[3])
    println()
    WallService.update(Post(2, 2, 2, 2, 2, "ДА", false, false, false))
    println(WallService.posts[1])

}


