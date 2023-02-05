DROP DATABASE IF EXISTS JSP_Community;
CREATE DATABASE JSP_Community;
USE JSP_Community;

CREATE TABLE article(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(200) NOT NULL,
	`body` LONGTEXT NOT NULL
);

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목1",
`body`="내용1";

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목2",
`body`="내용2";

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목3",
`body`="내용3";

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title="제목4",
`body`="내용4";

SELECT COUNT(*) FROM article;

SELECT * FROM article
ORDER BY id DESC
LIMIT 0,30;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title=CONCAT("제목_",RAND()),
BODY = CONCAT("내용_",RAND());

INSERT INTO article(regDate, updateDate, title, BODY)
SELECT NOW(), NOW(), CONCAT('제목_',RAND()), CONCAT('내용_',RAND())
FROM article;

SELECT * FROM article;

CREATE TABLE `member`(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	user_id CHAR(100) NOT NULL,
	user_pw CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL,
	tel CHAR(100) NOT NULL
)

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
user_id = 'user1',
user_pw = 'user1',
`name` = '유저1',
tel = '010-1111-1111';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
user_id = 'user2',
user_pw = 'user2',
`name` = '유저2',
tel = '010-2222-2222';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
user_id = 'user3',
user_pw = 'user3',
`name` = '유저3',
tel = '010-3333-3333';

SELECT * FROM `member`;

ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER id;

SELECT * FROM article;

UPDATE article
SET memberId = 2;
WHERE memberId= 0;