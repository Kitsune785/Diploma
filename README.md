# Дипломный проект по професии "Инженер по тестированию"

##### [Ссылка](https://github.com/netology-code/qamid-diplom) на задание

#### План тестирования: https://github.com/Kitsune785/QA-Diploma/blob/main/Plan.md 
#### Чек-лист проекта с отметками о пройденых  и непройденых тестах: https://docs.google.com/spreadsheets/d/1C9Hw9XV9K7vRva9GwGNAoen85fFeLZm_VZxGpimer1M/edit?usp=sharing
#### Тест-кейсы: https://docs.google.com/spreadsheets/d/1qiIf5p73QkpAco0pgMdqyuPkTXXCz7NAtwFZBomLRnY/edit?usp=sharing
---




Запуск автотестов:
1. Склонировать репозиторий на компьютер
2. Запустить Android Studio, открыв склонированный проект
3. Запустить приложение на эмуляторе API 29
4. Ввести команду в терминале ./gradlew connectedAndroidTest или запустить все тесты правым кликом по папке AndroidTest (Run 'Tests in 'ru.iteco.f...')
5. При необходимости выгрузить отчет Allure
    - Открыть Device File Explorer
    - Выбрать мулятор на котором производилось тестирование
    - Открыть папку data
    - В ней открыть папку data
    - В самом низу открыть папку ru.iteco.fmhandroid
    - Сохранить (правой кнопкой мыши) папку allure-results
    - Открыть отчет с помощью приложения Allure (в зависимости от ОС, инструкция по установке есть [тут](https://docs.qameta.io/allure-report/#_installing_a_commandline))
