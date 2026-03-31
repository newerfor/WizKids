package com.example.core_data.local.mapper

import com.example.core_data.local.entity.ChildDayOfWeekVisitEntity
import com.example.core_data.local.entity.ChildEntity
import com.example.core_data.local.entity.DocumentModel
import com.example.core_data.local.entity.UserEntity
import com.example.core_data.local.entity.VisitEntity
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.model.DomainVisitModel

class SaveMapper {
    fun mapChildModelToEntity(child: DomainChildModel): ChildEntity {
        return ChildEntity(
            id = child.id,
            imagePath = child.imagePath,
            name = child.name,
            additionalInfo = child.additionalInfo,
            dateOfBirth = child.dateOfBirth,
            documents = child.documents.map {
                DocumentModel(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    imagePaths = it.imagePaths
                )
            },
            learningStages = child.learningStages,
            visitPrice = child.visitPrice,
            currentBalance = child.currentBalance,
            childDayOfWeekVisit = ChildDayOfWeekVisitEntity(
                dayOfWeek = child.childDayOfWeekVisit.dayOfWeek as Map<String, Boolean>,
                firstDate = child.childDayOfWeekVisit.firstDate,
                secondDate = child.childDayOfWeekVisit.secondDate,
                time = child.childDayOfWeekVisit.time,
            ),
            numbers_visits = child.numbers_visits,
            general_profit = child.general_profit
        )
    }

    fun mapVisitModelToEntity(visit: DomainVisitModel, childId: Int): VisitEntity {
        return VisitEntity(
            id = visit.id,
            date = visit.date,
            time = visit.time,
            visitName = visit.visitName,
            visitStatus = visit.visitStatus,
            notes = visit.notes,
            payStatus = visit.payStatus,
            childId = childId,
            childName = visit.childName,
            price_of_visit =visit.price_of_visit
        )
    }

    fun mapUserModelToEntity(user: DomainUserModel): UserEntity {
        return UserEntity(
            imagePath = user.imagePath,
            name = user.name,
            dateOfBirth = user.dateOfBirth,
            about = user.about,
            workExperience = user.workExperience,
            phone = user.phone,
            email = user.email,
            educationLevel = user.educationLevel,
            specialization = user.specialization,
            documents = user.documents.map{
                DocumentModel(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    imagePaths = it.imagePaths
                )
            }
        )
    }
}