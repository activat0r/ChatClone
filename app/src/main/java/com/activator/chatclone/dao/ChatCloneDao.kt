package com.activator.chatclone.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.activator.chatclone.entities.CallDetails
import com.activator.chatclone.entities.ChatMessages
import com.activator.chatclone.main.entities.ChatScreen
import com.activator.chatclone.entities.StatusDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatCloneDao {
    @Insert
    fun insertNewChat(message: ChatMessages)

    @Query("Select RoomDetails.roomName ,message.stringMessage,  message.time from  (SELECT t1.* FROM ChatMessages t1 JOIN (SELECT RoomId, MAX(time) date FROM ChatMessages GROUP BY RoomId) t2 ON t1.RoomId = t2.RoomId AND t1.time = t2.date) as message join RoomDetails where RoomDetails.roomId = message.RoomId")
    fun getMessagedContacts(): Flow<ArrayList<ChatScreen>>

    @Query ("Select * from ChatMessages where  roomId = :room ")
    fun getMessagesForRoom(room :String): Array<ChatMessages>

    @Query("Select * from CallDetails")
    fun getAllCalls(): Array<CallDetails>

    @Query("Select * from StatusDetails where date >= :time")
    fun getAllStatusBefore(time: Long): Array<StatusDetails>

}