drop table IF EXISTS alarm;

drop table IF EXISTS apply;

drop table IF EXISTS blacklist;

drop table IF EXISTS chat_message;

drop table IF EXISTS comment;

drop table IF EXISTS follow;

drop table IF EXISTS like_count;

drop table IF EXISTS montage;

drop table IF EXISTS note;

drop table IF EXISTS participants;

drop table IF EXISTS chat_room;

drop table IF EXISTS profile;

drop table IF EXISTS report;

drop table IF EXISTS wishlist;

drop table IF EXISTS recruitment;

drop table IF EXISTS member;

-- Member 테이블 생성
CREATE TABLE `member` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `login_id` varchar(100) NULL,
                          `email` varchar(100) NULL,
                          `phone` varchar(100) NULL,
                          `birth` varchar(100) NULL,
                          `gender` char(1) NULL,
                          `profile_image` varchar(100) NULL,
                          `stage_name` varchar(100) NULL,
                          `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                          `updated_at` timestamp NULL DEFAULT current_timestamp,
                          `kakao_id` varchar(250) NULL,
                          `name` varchar(100) NULL,
                          `oauth_type` varchar(100) NULL,
                          PRIMARY KEY (`id`)
);

-- Recruitment 테이블 생성
CREATE TABLE `recruitment` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `title` varchar(50) NOT NULL,
                               `content` text NOT NULL,
                               `member_id` bigint NOT NULL,
                               `category` varchar(10) NOT NULL,
                               `image` varchar(100) NULL,
                               `start_date` varchar(20) NOT NULL,
                               `end_date` varchar(20) NOT NULL,
                               `created_at` timestamp NULL DEFAULT current_timestamp,
                               `updated_at` timestamp NULL DEFAULT current_timestamp,
                               `private_recruitment` varchar(1) NULL DEFAULT 'F',
                               PRIMARY KEY (`id`),
                               CONSTRAINT `FK_Member_TO_Recruitment_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
);


-- Blacklist 테이블 생성
CREATE TABLE `blacklist` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `member_id` bigint NOT NULL,
                             `warning` char NOT NULL DEFAULT 'F',
                             `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                             `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                             PRIMARY KEY (`id`),
                             CONSTRAINT `FK_Member_TO_Blacklist_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
);

-- Alarm 테이블 생성
CREATE TABLE `alarm` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `member_id` bigint NOT NULL,
                         `type` char(3) NOT NULL DEFAULT '없음',
                         `is_read` char(1) NOT NULL DEFAULT 'F',
                         `created_at` timestamp NULL DEFAULT current_timestamp,
                         `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                         PRIMARY KEY (`id`),
                         CONSTRAINT `FK_Member_TO_Alarm_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
);


-- Follow 테이블 생성
CREATE TABLE `follow` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `following_id` bigint NOT NULL,
                          `follower_id` bigint NOT NULL,
                          `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                          `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                          PRIMARY KEY (`id`),
                          CONSTRAINT `FK_Member_TO_Follow_1` FOREIGN KEY (`following_id`) REFERENCES `member` (`id`),
                          CONSTRAINT `FK_Member_TO_Follow_2` FOREIGN KEY (`follower_id`) REFERENCES `member` (`id`)
);

-- Application 테이블 생성
CREATE TABLE `apply` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `recruitment_id` bigint NOT NULL,
                               `member_id` bigint NOT NULL,
                               `video_link` varchar(100) NOT NULL,
                               `content` varchar(200) NULL,
                               `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                               `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                               PRIMARY KEY (`id`),
                               CONSTRAINT `FK_Recruitment_TO_Apply_1` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment` (`id`),
                               CONSTRAINT `FK_Member_TO_Apply_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
);


-- Wishlist 테이블 생성
CREATE TABLE `wishlist` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `recruitment_id` bigint NOT NULL,
                            `member_id` bigint NOT NULL,
                            `created_at` timestamp NULL DEFAULT current_timestamp,
                            `updated_at` timestamp NULL DEFAULT current_timestamp,
                            PRIMARY KEY (`id`),
                            CONSTRAINT `FK_Recruitment_TO_Wishlist_1` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment` (`id`),
                            CONSTRAINT `FK_Member_TO_Wishlist_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
);

-- montage 테이블 생성
CREATE TABLE `montage` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `member_id` bigint NOT NULL,
                           `title` varchar(50) NOT NULL,
                           `link` varchar(255) NOT NULL,
                           `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                           `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                           PRIMARY KEY (`id`),
                           CONSTRAINT `FK_Member_TO_montage_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
);

-- Comment 테이블 생성
CREATE TABLE `comment` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `member_id` bigint NOT NULL,
                           `montage_id` bigint NOT NULL,
                           `content` varchar(100) NOT NULL,
                           `reference_id` bigint NULL,
                           `updated_at` timestamp NULL DEFAULT current_timestamp,
                           `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                           `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
                           PRIMARY KEY (`id`),
                           CONSTRAINT `FK_Member_TO_Comment_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
                           CONSTRAINT `FK_montage_TO_Comment_1` FOREIGN KEY (`montage_id`) REFERENCES `montage` (`id`)
);

-- chat_room 테이블 생성
CREATE TABLE `chat_room` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `title` varchar(100) NOT NULL,
                             `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                             `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                             PRIMARY KEY (`id`)
);

-- chat_message 테이블 생성
CREATE TABLE `chat_message` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `sender` bigint NOT NULL,
                                `chat_room_id` bigint NOT NULL,
                                `content` varchar(100) NOT NULL,
                                `is_read` char(1) NOT NULL DEFAULT 'F',
                                `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                                `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                                `type` char(5) NOT NULL,
                                PRIMARY KEY (`id`),
                                CONSTRAINT `FK_Member_TO_chat_message_1` FOREIGN KEY (`sender`) REFERENCES `member` (`id`),
                                CONSTRAINT `FK_chat_room_TO_chat_message_1` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`)
);

-- profile 테이블 생성
CREATE TABLE `profile` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `member_id` bigint NOT NULL,
                           `content` text NOT NULL,
                           `type` char(1) NOT NULL DEFAULT 'A',
                           `created_at` timestamp NULL DEFAULT current_timestamp,
                           `updated_at` timestamp NULL DEFAULT current_timestamp,
                           `portfolio` varchar(100) NULL DEFAULT '',
                           `private_post` varchar(1) NOT NULL DEFAULT 'F',
                           PRIMARY KEY (`id`),
                           CONSTRAINT `FK_Member_TO_profile_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
);

-- report 테이블 생성
CREATE TABLE `report` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `reason` varchar(255) NULL,
                          `reporter_id` bigint NOT NULL,
                          `reportee_id` bigint NOT NULL,
                          `image_link` varchar(100) NULL,
                          PRIMARY KEY (`id`),
                          CONSTRAINT `FK_Member_TO_report_1` FOREIGN KEY (`reporter_id`) REFERENCES `member` (`id`),
                          CONSTRAINT `FK_Member_TO_report_2` FOREIGN KEY (`reportee_id`) REFERENCES `member` (`id`)
);

-- Note 테이블 생성
CREATE TABLE `note` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `sender_id` bigint NOT NULL,
                        `receiver_id` bigint NOT NULL,
                        `content` varchar(100) NOT NULL,
                        `is_read` char(1) NOT NULL DEFAULT 'F',
                        `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                        `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                        PRIMARY KEY (`id`),
                        CONSTRAINT `FK_Member_TO_Note_1` FOREIGN KEY (`sender_id`) REFERENCES `member` (`id`),
                        CONSTRAINT `FK_Member_TO_Note_2` FOREIGN KEY (`receiver_id`) REFERENCES `member` (`id`)
);

-- Like_count 테이블 생성
CREATE TABLE `like_count` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `montage_id` bigint NOT NULL,
                              `member_id` bigint NOT NULL,
                              PRIMARY KEY (`id`),
                              `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                              `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                              CONSTRAINT `FK_montage_TO_Like_count_1` FOREIGN KEY (`montage_id`) REFERENCES `montage` (`id`),
                              CONSTRAINT `FK_Member_TO_Like_count_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
);

-- participants 테이블 생성
CREATE TABLE `participants` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `chat_room_id` bigint NOT NULL,
                                `member_id` bigint NOT NULL,
                                `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                                `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                                PRIMARY KEY (`id`),
                                CONSTRAINT `FK_chat_room_TO_participants_1` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`),
                                CONSTRAINT `FK_Member_TO_participants_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
);

