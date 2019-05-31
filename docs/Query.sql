-- 테이블 기본 조회
show tables;
desc users;
desc blog;
desc category;
desc post;
select * from users;
select * from blog;
select * from category;
select * from post;

/* 유저 */
-- 유저 추가
insert into users values('ydhwa', '양동화', '1234', now());

-- 유저 정보 가져오기
select id, name from users
where id = 'ydhwa' and password = '1234';

-- 로그인 한 유저 정보
select id, name from users
where id = 'ydhwa';



/* 블로그 */
-- 블로그 생성
insert into blog values('ydhwa', null, null);

-- 블로그 설정 가져오기
select title, logo from blog
where blog_id = 'ydhwa';

-- 블로그 설정 변경
update blog
set title = '동화의 블로그', logo = 'logo.jpg'
where blog_id like 'ydhwa';

alter table post change content contents longtext;


/* 카테고리 */
-- 카테고리 생성
insert into category values(null, '미분류', '분류 없음', now(), 'ydhwa');
insert into category values(null, 'TEST1', '테스트', now(), 'ydhwa');
insert into category(no, reg_date, blog_id) values(null, now(), 'ysjin');

-- 카테고리 삭제
delete from category where no = 8;

-- 모든 카테고리 보기(관리용 - 서브쿼리 사용함)
select no, name, ifnull((select count(*) from post p where p.category_no = c.no group by p.category_no), 0) as posts, description
from category c
where blog_id = 'ydhwa'
order by reg_date asc;

-- 모든 카테고리 보기(보여주기용 - 조금이라도 덜 불러오기 위함)
select no, name from category
where blog_id = 'ydhwa'
order by reg_date asc;

-- 카테고리 하나 보기(사이트 들어갔을 때, 가장 처음에 만들어진 것으로 보여줌)
select no, name from category
where blog_id = 'ydhwa'
order by reg_date asc
limit 0, 1;

select c.no 
from category c, users u 
where u.id = 'ysjin' 
	and c.blog_id = u.id 
order by c.reg_date asc limit 0, 1;

-- 카테고리 하나 보기(사용자가 선택한 카테고리로 이동)
select no, name from category
where no = 1;



/* 포스트 */
-- 포스트 생성
insert into post values(null, '미분류 제목입니다!', '미분류 내용입니다~', now(), 1);
insert into post values(null, 'Spring framework', '어렵지만 재밌네요!', now(), 7);
insert into post values(null, '미분류 제목입니다!22', '미분류 내용입니다~22', now(), 1);

-- 포스트 수정
update post
set title = '제목 수정했습니다!', content = '내용 수정하고 카테고리 cafe24로 옮깁니다~', category_no = 2
where no = 1;

-- 전체 포스트 불러오기 (특정 블로그/특정 카테고리 게시글 리스트의)
-- category는 어차피 유저들이 생성할 때 유니크하게 생성되므로 user 검사는 하지 않음
select p.title, date_format(p.reg_date, '%Y/%m/%d') as regDate
from post p, category c
where c.no = 1 and p.category_no = c.no
order by p.reg_date desc;

select p.title, date_format(p.reg_date, '%Y/%m/%d') as regDate
from post p, category c
where c.no = (select no from category c, users u where u.id = 'ydhwa' and c.blog_id = u.id order by c.reg_date asc limit 0, 1) 
	and p.category_no = c.no
order by p.reg_date desc;

-- 특정 게시글 불러오기(선택된 카테고리의 가장 최근에 생성된 게시글로 가져온다.)
select p.no, p.title, p.content, category_no, date_format(p.reg_date, '%Y/%m/%d %H:%i:%s') as regDate
from post p, category c
where c.no = 1 and p.category_no = c.no
order by p.reg_date desc
limit 0, 1;

-- 특정 게시글 불러오기(사용자가 선택한 게시글을 보여준다.)
select no, title, content, category_no, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as regDate
from post
where no = 3;
