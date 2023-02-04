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


CREATE TABLE `member`(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	user_id CHAR(100) NOT NULL,
	user_pw CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL,
	tel CHAR(100) NOT NULL
)

SELECT * FROM `member`;