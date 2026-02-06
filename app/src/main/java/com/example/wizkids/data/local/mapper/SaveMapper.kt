package com.example.wizkids.data.local.mapper

import com.example.wizkids.data.local.entity.ChildEntity
import com.example.wizkids.data.local.entity.UserEntity
import com.example.wizkids.data.local.entity.VisitEntity
import com.example.wizkids.data.local.model.ChildModel
import com.example.wizkids.data.local.model.UserModel
import com.example.wizkids.data.local.model.VisitModel

class SaveMapper {
    fun mapChildModelToEntity(child: ChildModel): ChildEntity {
        return ChildEntity(
            id =  child.id,
            imagePath =  child.imagePath,
            name =  child.name,
            additionalInfo = child.additionalInfo ,
            dateOfBirth =  child.dateOfBirth,
            documents =  child.documents,
            learningStages = child.learningStages ,
            visitPrice =  child.visitPrice,
            currentBalance = child.currentBalance
        )
    }

    fun mapVisitModelToEntity(visit: VisitModel, childId: Int): VisitEntity {
        return VisitEntity(
            id =  visit.id,
            date =  visit.date,
            time =  visit.time,
            visitName = visit.visitName ,
            visitStatus =  visit.visitStatus,
            notes =  visit.notes,
            payStatus = visit.payStatus ,
            childId = childId
        )
    }

    fun mapUserModelToEntity(user: UserModel): UserEntity {
        return UserEntity(
            imagePath = user.imagePath ,
            name = user.name ,
            dateOfBirth =  user.dateOfBirth,
            about =  user.about,
            workExperience =  user.workExperience,
            phone =  user.phone,
            email =  user.email,
            educationLevel =  user.educationLevel,
            specialization =  user.specialization,
            documents = user.documents
        )
    }
}