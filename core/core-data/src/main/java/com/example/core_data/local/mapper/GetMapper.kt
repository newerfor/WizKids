package com.example.core_data.local.mapper

import com.example.core_data.local.entity.ChildEntity
import com.example.core_data.local.entity.UserEntity
import com.example.core_data.local.entity.VisitEntity
import com.example.core_domain.model.DomainChildDayOfWeekVisit
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainDocumentsModel
import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.model.DomainVisitModel

class GetMapper {
    fun mapChildToModel(child: ChildEntity?): DomainChildModel? {
        return child?.let {
            DomainChildModel(
                id = child.id,
                imagePath = child.imagePath,
                name = child.name,
                additionalInfo = child.additionalInfo,
                dateOfBirth = child.dateOfBirth,
                documents = child.documents.map {
                    DomainDocumentsModel(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        imagePaths = it.imagePaths
                    )
                },
                learningStages = child.learningStages,
                visitPrice = child.visitPrice,
                currentBalance = child.currentBalance,
                childDayOfWeekVisit = DomainChildDayOfWeekVisit(
                    dayOfWeek = child.childDayOfWeekVisit.dayOfWeek,
                    firstDate = child.childDayOfWeekVisit.firstDate,
                    secondDate = child.childDayOfWeekVisit.secondDate,
                    time = child.childDayOfWeekVisit.time
                ),
                numbers_visits = child.numbers_visits,
                general_profit = child.general_profit,
            )
        }
    }

    fun mapVisitToModel(visit: VisitEntity?): DomainVisitModel? {
        return visit?.let {
            DomainVisitModel(
                id = visit.id,
                date = visit.date,
                time = visit.time,
                visitName = visit.visitName,
                visitStatus = visit.visitStatus,
                notes = visit.notes,
                payStatus = visit.payStatus,
                childId = visit.childId,
                childName = visit.childName,
                price_of_visit = visit.price_of_visit
            )
        }
    }

    fun mapUserToModel(user: UserEntity?): DomainUserModel? {
        return user?.let {
            DomainUserModel(
                imagePath = user.imagePath,
                name = user.name,
                dateOfBirth = user.dateOfBirth,
                about = user.about,
                workExperience = user.workExperience,
                phone = user.phone,
                email = user.email,
                educationLevel = user.educationLevel,
                specialization = user.specialization,
                documents = user.documents.map {
                    DomainDocumentsModel(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        imagePaths = it.imagePaths
                    )
                }
            )
        }
    }
}