[README.md](https://github.com/user-attachments/files/27235439/README.md)
<div align="center">

# 🧒 WizKids

### Личный помощник воспитателя

*Мобильное приложение для педагогов, воспитателей и репетиторов —*  
*заменяет бумажные журналы, разрозненные заметки и ручной учёт финансов.*

<br>

![Android](https://img.shields.io/badge/Android-SDK%2034+-3DDC84?style=flat-square&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=flat-square&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8.0+-02303A?style=flat-square&logo=gradle&logoColor=white)

</div>

---

## 📱 Скриншоты

<div align="center">

| Главный экран | Профиль ребёнка |
|:---:|:---:|
| <img src="pictures/mainScreen.png" width="200"/> | <img src="pictures/firstChildProfile.png" width="200"/> <img src="pictures/SecondProfile.png" width="200"/>|

| Календарь | Документы | Профиль специалиста |
|:---:|:---:|:---:|
| <img src="pictures/Calendar.png" width="200"/> | <img src="pictures/viewDocs.png" width="200"/> | <img src="pictures/First_teacher_info.png" width="200"/> <img src="pictures/SecondTeacherInfo.png" width="200"/>|

</div>

---

## ✨ Возможности

| Модуль | Описание |
|--------|----------|
| 👥 **Управление детьми** | Профили воспитанников с фото, документами и этапами развития |
| 💰 **Финансовый учёт** | Баланс, история оплат, стоимость занятий, автоматический пересчёт |
| 📅 **Умный календарь** | Визуализация посещаемости, история визитов, счётчик успешных занятий |
| 🔍 **Поиск и фильтрация** | Сортировка по любым параметрам — имя, дата, статус оплаты |
| 👤 **Профиль специалиста** | Экспорт данных в PDF, персональная информация |
| 🌗 **Адаптивный дизайн** | Поддержка светлой и тёмной темы, плавные анимации |
| ✅ **Надёжность** | Полная обработка ошибок и состояний загрузки |

---

## 🏗 Архитектура

Проект построен на **Clean Architecture** с модульной структурой — каждый модуль независим, переиспользуем и компилируется отдельно, что ускоряет сборку.

```
WizKids/
├── app/                        # Entry point, навигация
│
├── core/
│   ├── core-data/              # Room, DAO, Repository impl, маппинг
│   ├── core-domain/            # Модели, репозитории (интерфейсы), Use Cases
│   ├── core-navigation/        # NavRoutes — единые маршруты навигации
│   ├── core-ui/                # Shared-компоненты, тема, утилиты UI
│   ├── core-util/              # Вспомогательные утилиты
│   └── core-viewModel/         # Базовые ViewModel
│
└── feature/
    ├── feature-main/                    # Главный экран
    ├── feature-childInformation/        # Карточка ребёнка
    ├── feature-addNewOrChangeInfoChild/ # Добавление / редактирование ребёнка
    ├── feature-addNewOrChangeInfoUser/  # Редактирование профиля специалиста
    ├── feature-calendarScreen/          # Экран календаря
    ├── feature-upcomingDates/           # Предстоящие занятия
    └── feature-userProfile/             # Профиль специалиста
```

### Ключевые архитектурные решения

- **Single Activity** — вся навигация через Jetpack Compose Navigation, единый lifecycle, упрощённое управление состояниями
- **MVVM** — чёткое разделение UI и бизнес-логики через ViewModel + StateFlow
- **Repository Pattern** — слой данных полностью скрыт за интерфейсами из `core-domain`
- **Use Cases** — каждая бизнес-операция инкапсулирована в отдельный класс

---

## 🛠 Технологический стек

| Категория | Технология |
|-----------|-----------|
| **Язык** | Kotlin 1.9+ |
| **UI** | Jetpack Compose, Material 3 |
| **Навигация** | Jetpack Compose Navigation |
| **База данных** | Room (TypeConverters, миграции) |
| **DI** | Koin |
| **Асинхронность** | Coroutines, StateFlow |
| **Сериализация** | Gson |

---

## 🚀 Установка и запуск

### Требования

- Android Studio Hedgehog или новее
- Android SDK 34+
- Kotlin 1.9+
- Gradle 8.0+

### Шаги

```bash
# 1. Клонировать репозиторий
git clone https://github.com/your-username/WizKids.git

# 2. Открыть в Android Studio
# File → Open → выбрать папку WizKids

# 3. Синхронизировать Gradle и запустить
# Run → Run 'app'  (Shift + F10)
```

Приложение работает полностью офлайн — интернет-соединение не требуется.

---

## 📬 Контакты

Вопросы, баги, предложения — пишите:

**vladislav.yurshin.work@yandex.ru**
