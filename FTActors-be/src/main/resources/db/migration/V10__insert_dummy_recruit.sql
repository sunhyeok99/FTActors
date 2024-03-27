INSERT INTO recruitment (title, content, member_id, category, image, image_name, start_date, end_date, file, file_name) VALUES
('Title1', 'Content1', 1, 'Category1', 'Image1', 'ImageName1', '2024-03-26', '2024-04-26', 'File1', 'FileName1'),
('Title2', 'Content2', 2, 'Category2', 'Image2', 'ImageName2', '2024-03-27', '2024-04-27', 'File2', 'FileName2'),
('Title3', 'Content3', 3, 'Category3', 'Image3', 'ImageName3', '2024-03-28', '2024-04-28', 'File3', 'FileName3'),
('Title4', 'Content4', 4, 'Category4', 'Image4', 'ImageName4', '2024-03-29', '2024-04-29', 'File4', 'FileName4'),
('Title5', 'Content5', 5, 'Category5', 'Image5', 'ImageName5', '2024-03-30', '2024-04-30', 'File5', 'FileName5');

INSERT INTO apply (recruitment_id, member_id, video_link, saved_name, content) VALUES
(1, 3, 'VideoLink1', 'SavedName1', 'Content1'),
(2, 4, 'VideoLink2', 'SavedName2', 'Content2'),
(3, 1, 'VideoLink3', 'SavedName3', 'Content3'),
(4, 1, 'VideoLink4', 'SavedName4', 'Content4'),
(5, 1, 'VideoLink5', 'SavedName5', 'Content5');


INSERT INTO wishlist (recruitment_id, member_id) VALUES
(1, 3),
(1, 2),
(1, 5),
(2, 1),
(3, 5),
(4, 2),
(4, 1);


INSERT INTO follow (following_id, follower_id) VALUES
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 1),
(2, 4);

