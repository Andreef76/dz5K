   sealed class Attachment {
    abstract val type: String
//    val audioType = AudioAttachment(audio, "type1")
//    val photoType = PhotoAttachment(photo, "type2")
//    val videoType = VideoAttachment(video, "type3")
//    val post = Post(0, 10, 1, 1, 1, "НЕТ", true, true, true,0,0,"0",0, 0,0,true,0, null, null, null, null, null, null,null, null)
    fun addArrayAttachment(post: Post, attachment: Attachment) {
        post.attachments += attachment
//        post.attachments += audioType
//        post.attachments += videoType
//        post.attachments += photoType

    }

}
fun main() {
//    val attachment: Attachment = VideoAttachment(video, "video clip")
//    println(attachment.type)
}

data class Photo (
val id: Int,                      //Идентификатор фотографии.
val albumId: Int = 1,                 //Идентификатор альбома, в котором находится фотография.
val ownerId: Int,                //Идентификатор владельца фотографии.
val text: String = "",                 // Текст описания фотографии.
val date: Int = 1,                    // Дата добавления в формате Unixtime.
)
//val ph3 =  Photo(ownerId=5, id=6, albumId = 2, text = "a", date = 5) // так тоже можно
//val ph4 = Photo(ownerId = 2, date = 5, id = 3, albumId = 2, text = "Dgfsdgf") // вот и инструмент)
//
//val ph = Photo(0, ownerId=0) //всё ??? не по порядку когда идёт    увидел что этот параметр тоже был не задан
//   val ph2 = Photo(0,0,0,"sgdg",556)
class PhotoAttachment(val photo: Photo, override val type: String) : Attachment()
val photo = Photo(0, ownerId = 0)

data class Audio(
    val id: Int,                 // Идентификатор аудиозаписи.
    val ownerId: Int,            // Идентификатор владельца аудиозаписи.
    val artist: String,          // Исполнитель.
    val title: String,           // Название композиции.
    val duration: Int           //Длительность аудиозаписи в секундах.
)

class AudioAttachment(val audio: Audio, override val type: String) : Attachment()
val audio = Audio(0,0,"0","0",0)

data class Video(
    val id: Int,              // Идентификатор видеозаписи.
    val ownerId: Int,         // Идентификатор владельца видеозаписи.
    val title: String,        // Название видеозаписи.
    val description: String,  // Текст описания видеозаписи.
    val duration: Int         // Длительность ролика в секундах.
)
class VideoAttachment(val video: Video, override val type: String) : Attachment()
val video = Video(0,0,"0","0",0)

