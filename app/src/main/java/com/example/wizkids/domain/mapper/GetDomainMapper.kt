package com.example.wizkids.domain.mapper

import com.example.wizkids.data.local.model.ChildModel
import com.example.wizkids.data.local.model.DocumentsModel
import com.example.wizkids.data.local.model.UserModel
import com.example.wizkids.data.local.model.VisitModel
import com.example.wizkids.domain.model.DomainChildDayOfWeekVisit
import com.example.wizkids.domain.model.DomainChildModel
import com.example.wizkids.domain.model.DomainDocumentsModel
import com.example.wizkids.domain.model.DomainUserModel
import com.example.wizkids.domain.model.DomainVisitModel

class GetDomainMapper {
    fun mapDataChildModelToDomainChildModel(child: ChildModel?): DomainChildModel? {
        return child?.let {
            DomainChildModel(
                id = child.id,
                imagePath = child.imagePath,
                name = child.name,
                additionalInfo = child.additionalInfo,
                dateOfBirth = child.dateOfBirth,
                documents = child.documents.map { mapDataDocumentsModelToDomainDocumentsModel(it) },
                learningStages = child.learningStages,
                visitPrice = child.visitPrice,
                currentBalance = child.currentBalance,
                childDayOfWeekVisit = DomainChildDayOfWeekVisit(
                    dayOfWeek = child.childDayOfWeekVisit.dayOfWeek,
                    firstDate = child.childDayOfWeekVisit.firstDate,
                    secondDate = child.childDayOfWeekVisit.secondDate,
                    time = child.childDayOfWeekVisit.time,
                )
            )
        }
    }

    fun mapDataVisitModelToDomainVisitModel(visit: VisitModel?): DomainVisitModel? {
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
                childName = visit.childName
            )
        }
    }

    fun mapDataUserModelToDomainUserModel(user: UserModel?): DomainUserModel? {
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
                documents = user.documents.map { mapDataDocumentsModelToDomainDocumentsModel(it) }
            )
        }
    }

    fun mapDataDocumentsModelToDomainDocumentsModel(documents: DocumentsModel): DomainDocumentsModel {
        return DomainDocumentsModel(
            id = documents.id,
            name = documents.name,
            description = documents.description,
            imagePaths = documents.imagePaths
        )
    }

}