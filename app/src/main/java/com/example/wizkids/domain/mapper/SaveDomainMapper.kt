package com.example.wizkids.domain.mapper

import androidx.compose.ui.input.key.Key.Companion.D
import com.example.wizkids.data.local.model.ChildModel
import com.example.wizkids.data.local.model.DocumentsModel
import com.example.wizkids.data.local.model.UserModel
import com.example.wizkids.data.local.model.VisitModel
import com.example.wizkids.domain.model.DomainChildModel
import com.example.wizkids.domain.model.DomainDocumentsModel
import com.example.wizkids.domain.model.DomainUserModel
import com.example.wizkids.domain.model.DomainVisitModel

class SaveDomainMapper {
    fun mapDomainChildModelToDataChildModel(child: DomainChildModel): ChildModel {
        return ChildModel(
            id = child.id,
            imagePath = child.imagePath,
            name = child.name,
            additionalInfo = child.additionalInfo,
            dateOfBirth = child.dateOfBirth,
            documents = child.documents.map { mapDomainDocumentsModelToDataDocumentsModel(it) },
            learningStages = child.learningStages,
            visitPrice = child.visitPrice,
            currentBalance = child.currentBalance
        )
    }
    fun mapDomainVisitModelToDataVisitModel(visit: DomainVisitModel): VisitModel {
        return VisitModel(
            id = visit.id,
            date = visit.date,
            time = visit.time,
            visitName = visit.visitName,
            visitStatus = visit.visitStatus,
            notes = visit.notes,
            payStatus = visit.payStatus,
            childId = visit.childId
        )
    }
    fun mapDomainUserModelToDataUserModel(user: DomainUserModel): UserModel {
        return UserModel(
            imagePath = user.imagePath,
            name = user.name,
            dateOfBirth = user.dateOfBirth,
            about = user.about,
            workExperience = user.workExperience,
            phone = user.phone,
            email = user.email,
            educationLevel = user.educationLevel,
            specialization = user.specialization,
            documents = user.documents.map { mapDomainDocumentsModelToDataDocumentsModel(it) }
        )
    }
    fun mapDomainDocumentsModelToDataDocumentsModel(documents: DomainDocumentsModel): DocumentsModel {
        return DocumentsModel(
            id = documents.id,
            name = documents.name,
            description = documents.description,
            imagePaths = documents.imagePaths
        )
    }
}