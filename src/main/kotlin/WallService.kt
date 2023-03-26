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

        fun addRepostArray(post: Post) {        /*   Массив, содержащий историю репостов для записи.
                                            Возвращается только в том случае, если запись является репостом.
                                            Каждый из объектов массива, в свою очередь, является объектом-записью стандартного формата.*/
            WallService.copyHistory += repost

        }
        fun reports (commentId: Int, reason: Int) : Comment {
            var count = 0
            for ((index, comment: Comment) in comments.withIndex()) {
                if ((comment.id + 1) == commentId) {
                    count++
                }
                when (reason) {
                    in (1..8).minus(7) -> invalidComment += comment
                    else -> throw ReasonNotFoundException("Комментарий не является недопустимым")
                }
            } // Проверку за цикл нужно было вынести
            if (count == 0) throw CommentNotFoundException("Комментарий не найден")
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
