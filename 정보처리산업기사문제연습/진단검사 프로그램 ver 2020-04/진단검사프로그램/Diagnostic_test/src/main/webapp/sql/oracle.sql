drop table TBL_PATIENT_202004;
drop table TBL_LAB_TEST_202004;
drop table TBL_RESULT_202004;

create table TBL_PATIENT_202004 (
p_no char(4) not null primary key,
p_name varchar2(20),
p_birth char(8),
p_gender char(1),
p_tel1 char(3),
p_tel2 char(4),
p_tel3 char(4),
p_city char(2)
);



insert into TBL_PATIENT_202004 values('1001','김환자','19850301','M','010','2222','0001','10');
insert into TBL_PATIENT_202004 values('1002','이환자','19900301','M','010','2222','0002','20');
insert into TBL_PATIENT_202004 values('1003','박환자','19770301','F','010','2222','0003','30');
insert into TBL_PATIENT_202004 values('1004','조환자','19650301','F','010','2222','0004','10');
insert into TBL_PATIENT_202004 values('1005','황환자','19400301','M','010','2222','0005','40');
insert into TBL_PATIENT_202004 values('1006','양환자','19440301','F','010','2222','0006','40');
insert into TBL_PATIENT_202004 values('1007','허환자','19760301','F','010','2222','0007','10');


select * from tbl_patient_202004;

create table tbl_lab_test_202004 (
t_code char(4) not null,
t_name varchar2(20),
primary key(t_code)
);



insert into tbl_lab_test_202004 values ('T001','결핵');
insert into tbl_lab_test_202004 values ('T002','장티푸스');
insert into tbl_lab_test_202004 values ('T003','수두');
insert into tbl_lab_test_202004 values ('T004','홍역');
insert into tbl_lab_test_202004 values ('T005','콜레라');


select  * from tbl_lab_test_202004;


create table tbl_result_202004 (
p_no char(4) not null,
t_code char(4),
t_sdate date,
t_status char(1),
t_ldate date,
t_result char(1),
primary key(p_no,t_code,t_sdate)
);


insert into tbl_result_202004 values('1001','T001','2020-01-01','1','2020-01-02','X');
insert into tbl_result_202004 values('1002','T002','2020-01-01','2','2020-01-02','P');
insert into tbl_result_202004 values('1003','T003','2020-01-01','2','2020-01-02','N');
insert into tbl_result_202004 values('1004','T004','2020-01-01','2','2020-01-02','P');
insert into tbl_result_202004 values('1005','T005','2020-01-01','2','2020-01-02','P');
insert into tbl_result_202004 values('1006','T001','2020-01-01','2','2020-01-02','N');
insert into tbl_result_202004 values('1007','T002','2020-01-01','2','2020-01-02','P');


select * from tbl_result_202004;


--환자조회 쿼리문
select p_no,p_name,substr(p_birth,1,4)||'년'||substr(p_birth,5,2)||'월'||substr(p_birth,7,2)||'일' as p_birth,
case p_gender when 'M' then '남' when 'F' then '여' end as p_gender,
p_tel1||'-'||p_tel2||'-'||p_tel3 as p_tel,
case p_city when '10' then '서울' when '20' then '경기' when '30' then '강원' when '40' then '대구' end as p_city
from TBL_PATIENT_202004;

--검사결과입력 쿼리문
select patient.p_no, patient.p_name,  
test.t_name, to_char(result.t_sdate, 'YYYY-MM-DD') as t_sdate,  
case result.t_status  when '1' then '검사중' when '2' then '검사완료' end as t_status, 
to_char(result.t_ldate, 'YYYY-MM-DD') as t_ldate,
case result.t_result when 'X' then '미입력' when 'P' then '양성'  when 'N' then '음성' end as t_result  
from tbl_patient_202004 patient,  tbl_lab_test_202004 test,  tbl_result_202004 result 
where patient.p_no = result.p_no  and result.t_code = test.t_code;

select tp.p_city,
case tp.p_city
	when '10' then '서울'
	when '20' then '경기'
	when '30' then '강원'
	when '40' then '대구'
end as p_city_name,
count(tp.p_city) as p_city_count
from TBL_PATIENT_202004 tp,tbl_result_202004 tr
where tp.p_no = tr.p_no group by p_city order by p_city asc;

