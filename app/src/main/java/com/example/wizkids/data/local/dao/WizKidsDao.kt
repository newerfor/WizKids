package com.example.wizkids.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wizkids.data.local.entity.ChildEntity
import com.example.wizkids.data.local.entity.UserEntity
import com.example.wizkids.data.local.entity.VisitEntity
import com.example.wizkids.data.local.constant.SqlConstants
@Dao
interface WizKidsDao {
    @Query("SELECT * FROM child WHERE id = :id")
    suspend fun getChildById(id: Int?): ChildEntity?

    @Query(
        """
    SELECT * FROM child 
    WHERE (:searchName IS NULL OR :searchName = '' OR name LIKE '%' || :searchName || '%')
    AND (
        (:minAge IS NULL AND :maxAge IS NULL)
        OR
        (
            CAST(strftime('%Y', 'now') AS INTEGER) - 
            CAST(substr(dateOfBirth, 7, 4) AS INTEGER) 
            BETWEEN 
            COALESCE(:minAge, :minAgeDefault) AND COALESCE(:maxAge, :maxAgeDefault)
        )
    )
    AND (:balanceOperator IS NULL OR 
        CASE :balanceOperator
            WHEN :greaterOp THEN visitPrice > currentBalance
            WHEN :lessOp THEN visitPrice < currentBalance
            WHEN :equalOp THEN visitPrice = currentBalance
            WHEN :greaterOrEqualOp THEN visitPrice >= currentBalance
            WHEN :lessOrEqualOp THEN visitPrice <= currentBalance
            WHEN :notEqualOp THEN visitPrice != currentBalance
            ELSE 1
        END)
    OR (:showOnlyDebt = 1 AND currentBalance < 0)
    """
    )
    suspend fun getChildren(
        searchName: String?,
        minAge: Int?,
        maxAge: Int?,
        balanceOperator: String?,
        showOnlyDebt: Boolean?,
        minAgeDefault: Int = SqlConstants.MIN_AGE_DEFAULT,
        maxAgeDefault: Int = SqlConstants.MAX_AGE_DEFAULT,
        greaterOp: String = SqlConstants.Operators.GREATER,
        lessOp: String = SqlConstants.Operators.LESS,
        equalOp: String = SqlConstants.Operators.EQUAL,
        greaterOrEqualOp: String = SqlConstants.Operators.GREATER_OR_EQUAL,
        lessOrEqualOp: String = SqlConstants.Operators.LESS_OR_EQUAL,
        notEqualOp: String = SqlConstants.Operators.NOT_EQUAL
    ): List<ChildEntity>


    @Query("SELECT * FROM visits")
    suspend fun getVisits(): List<VisitEntity>

    @Query("SELECT * FROM visits WHERE date = :visit")
    suspend fun getVisitByDate(visit: String): List<VisitEntity>

    @Query("SELECT * FROM visits WHERE childId = :id")
    suspend fun getVisitByChildId(id: Int): List<VisitEntity>

    @Query("SELECT * FROM user")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChild(child: ChildEntity): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVisit(visit: VisitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Query("DELETE FROM child WHERE id = :childId")
    suspend fun deleteChildById(childId: Int)

    @Query("DELETE FROM visits WHERE id = :visitId")
    suspend fun deleteVisitById(visitId: Int)

    @Query("DELETE FROM user WHERE id = :userId")
    suspend fun deleteUser(userId: Int = 0)

}