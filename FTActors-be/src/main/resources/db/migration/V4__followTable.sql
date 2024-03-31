Drop table If exists follow;
-- Follow 테이블 생성
CREATE TABLE `follow` (
                          `following_id` bigint NOT NULL,
                          `follower_id` bigint NOT NULL,
                          `created_at` timestamp NOT NULL DEFAULT current_timestamp,
                          `updated_at` timestamp NOT NULL DEFAULT current_timestamp,
                          PRIMARY KEY (`following_id` , `follower_id`),
                          CONSTRAINT `FK_Member_TO_Follow_1` FOREIGN KEY (`following_id`) REFERENCES `member` (`id`),
                          CONSTRAINT `FK_Member_TO_Follow_2` FOREIGN KEY (`follower_id`) REFERENCES `member` (`id`)
);
