-- 테이블 생성
create table banking_tb (
    idx number primary key, 
    accNumber varchar2(50) not null, 
    name varchar2(30) not null, 
    balance number default 0 not null
);

-- 시퀀스 생성
create sequence seq_banking
    increment by 1  
    start with 1 
    minvalue 1    
    nomaxvalue 
    nocycle        
    nocache; 