-- Отключение проверок внешних ключей
SET FOREIGN_KEY_CHECKS = 0;

-- Создание таблицы student
CREATE TABLE IF NOT EXISTS `students` (
                                          `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                          `first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Создание таблицы teacher
CREATE TABLE IF NOT EXISTS `teachers` (
                                          `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                          `first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Создание таблицы subjects
CREATE TABLE IF NOT EXISTS `subjects` (
                                          `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                          `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Создание таблицы grades
CREATE TABLE IF NOT EXISTS `grades` (
                                        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                        `score` INT NOT NULL,
                                        `comment` VARCHAR(255) DEFAULT NULL,
    `date` DATE NOT NULL,
    `student_id` BIGINT UNSIGNED NOT NULL,
    `teacher_id` BIGINT UNSIGNED NOT NULL,
    `subject_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Создание таблицы users
CREATE TABLE IF NOT EXISTS `users` (
                                       `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                       `username` VARCHAR(100) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` ENUM('TEACHER','STUDENT') NOT NULL,
    `teacher_id` BIGINT UNSIGNED DEFAULT NULL,
    `student_id` BIGINT UNSIGNED DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Включение проверок внешних ключей
SET FOREIGN_KEY_CHECKS = 1;



-- Отключение проверок внешних ключей
SET FOREIGN_KEY_CHECKS = 0;

-- Добавление внешнего ключа для связи student_id в таблице grades с таблицей student
ALTER TABLE `grades`
    ADD CONSTRAINT `fk_grades_student_id`
        FOREIGN KEY (`student_id`) REFERENCES `students`(`id`) ON DELETE CASCADE;

-- Добавление внешнего ключа для связи teacher_id в таблице grades с таблицей teacher
ALTER TABLE `grades`
    ADD CONSTRAINT `fk_grades_teacher_id`
        FOREIGN KEY (`teacher_id`) REFERENCES `teachers`(`id`) ON DELETE CASCADE;

-- Добавление внешнего ключа для связи subject_id в таблице grades с таблицей subjects
ALTER TABLE `grades`
    ADD CONSTRAINT `fk_grades_subject_id`
        FOREIGN KEY (`subject_id`) REFERENCES `subjects`(`id`) ON DELETE CASCADE;

-- Добавление внешнего ключа для связи teacher_id в таблице users с таблицей teacher
ALTER TABLE `users`
    ADD CONSTRAINT `fk_users_teacher_id`
        FOREIGN KEY (`teacher_id`) REFERENCES `teachers`(`id`) ON DELETE CASCADE;

-- Добавление внешнего ключа для связи student_id в таблице users с таблицей student
ALTER TABLE `users`
    ADD CONSTRAINT `fk_users_student_id`
        FOREIGN KEY (`student_id`) REFERENCES `students`(`id`) ON DELETE CASCADE;

-- Включение проверок внешних ключей
SET FOREIGN_KEY_CHECKS = 1;
