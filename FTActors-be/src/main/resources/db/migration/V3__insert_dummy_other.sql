INSERT INTO montage(member_id, title, link) VALUES(1, 'title-test', 'lojskdfnkdf');
INSERT INTO montage(member_id, title, link) VALUES(1, 'title-tessdfsdft', 'lojskdddddfnkdf');
INSERT INTO montage(member_id, title, link) VALUES(2, 'titlesdfsdf-test', 'dddddlojskdfnkdf');
INSERT INTO montage(member_id, title, link) VALUES(3, 'iu', 'dddddlojskdfnkdf');
INSERT INTO montage(member_id, title, link) VALUES(4, 'ty', 'dddddlojskdfnkdf');


INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(1, 1, 'comment', NULL);
INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(1, 1, 'cupid', 1);
INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(2, 1, '숨겨왔던', 1);

INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(3, 2, '더미데이터', NULL);
INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(3, 2, '이게 테스트야', NULL);
INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(4, 2, 'hehe', 3);

INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(5, 3, 'hehe', null);
INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(4, 4, 'hehe', null);
INSERT INTO comment(member_id, montage_id, content, reference_id) VALUES(4, 4, 'hehe', 4);

