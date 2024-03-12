INSERT INTO montage(member_id, title, link) VALUES(1, 'title-test', 'lojskdfnkdf');
INSERT INTO montage(member_id, title, link) VALUES(1, 'title-tessdfsdft', 'lojskdddddfnkdf');
INSERT INTO montage(member_id, title, link) VALUES(2, 'titlesdfsdf-test', 'dddddlojskdfnkdf');

INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(1, 1, 'comment', NULL);
INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(1, 1, 'cupid', 1);
INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(2, 1, '숨겨왔던', 1);

