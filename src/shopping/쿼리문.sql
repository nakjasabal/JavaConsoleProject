-- 쇼핑몰 미니 프로젝트
/*****************************************************************
--심플 쇼핑몰 만들기
--education 계정에서 실습합니다. 
*****************************************************************/

/*
테이블 생성
상품코드관리 : sh_product_code
    ----------------------------
    상품코드(PK)	p_code
    카테고리명  	category_name
    ----------------------------   	 
상품관리 : sh_goods
    ----------------------------
    일련번호(PK)	g_idx
    상품명      	goods_name
    상품가격    	goods_price
    등록일      	regidate
    상품코드(FK)	p_code
    ----------------------------   	 
상품데이터 로그 : sh_goods_log
    ----------------------------
    일련번호(PK)	log_idx
    상품명      	goods_name
    상품일련번호	goods_idx
    로그액션    	p_action  
        -> 입력시 : ‘Insert’ , 삭제시 : ‘Delete’ 입력
    ----------------------------   
*/
--상품코드관리 : sh_product_code
create table sh_product_code
(
    p_code number primary key,
    category_name varchar2(50) not null
);
desc sh_product_code;

--상품관리 : sh_goods
create table sh_goods
(
    g_idx number primary key,
    goods_name varchar2(100) not null,
    goods_price number not null,
    regidate date default sysdate,
    p_code number not null, 
    
    foreign key (p_code)
        references sh_product_code(p_code)
);
desc sh_goods;

-- 상품데이터로그 : goods_log
create table sh_goods_log
(
    log_idx number primary key,
    goods_name varchar2(100) not null,
    goods_idx number not null,
    p_action char(6) not null
);
desc sh_goods_log;


/*
시퀀스 생성하기
    product_code_seq
    goods_seq
    goods_log_seq
*/
create sequence product_code_seq
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;
create sequence goods_seq
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;
create sequence goods_log_seq
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;

--더미데이터 입력
insert into sh_product_code values (1, '가전제품');
insert into sh_product_code values (product_code_seq.nextval, '도서');
insert into sh_product_code values (product_code_seq.nextval, '컴퓨터');
select * from sh_product_code;

insert into sh_goods 
    values (goods_seq.nextval, 'Sens노트북', 1200000, sysdate, 3);
insert into sh_goods 
    values (goods_seq.nextval, 'Gram노트북', 1300000, sysdate, 3);
insert into sh_goods 
    values (goods_seq.nextval, '냉장고', 1000000, sysdate, 1);
insert into sh_goods 
    values (goods_seq.nextval, '로봇청소기', 500000, sysdate, 1);
insert into sh_goods 
    values (goods_seq.nextval, '자바프로그래밍', 30000, sysdate, 2);    
select * from sh_goods;

insert into sh_goods 
    values (goods_seq.nextval, 'DSLR카메라', 900000, sysdate, 4); /*
        부모키가 없으므로 참조 제약조건 위배
    */

commit;

/*
JDBC프로그래밍

1.상품입력
    -PreparedStatement객체를 사용하여 작성한다.
    -클래스명 : shopping.InsertShop
    -상품명, 상품가격, 상품코드를 scanValue() 메소드로 입력받아 사용한다. 
    -입력이 완료되면 입력된 행의 갯수를 반환하여 출력한다. 
    
2.상품조회
    -Statement객체를 사용하여 작성한다.
    -클래스명 : shopping.SelectShop
    -검색할 상품명을 입력하여 해당조건에 맞는 레코드만 출력한다. 
    -like 를 사용해야 한다.
    -출력항목 : 일련번호, 상품명, 가격, 등록일, 제품코드
    -단, 등록일은 0000-00-00 00:00 형태로 출력해야 한다.
        상품가격은 세자리마다 컴마를 찍어준다.

3.상품수정,삭제
    -프로시저 작성후 CallableStatement객체를 사용하여 호출하도록 한다.
    
    -상품수정
        프로시저명 : ShopUpdateGoods
        클래스명 : shopping.UpdateShop 
        In파라미터 : 상품명, 가격, 제품코드, 수정할 상품의 일련번호
        Out파라미터 : 레코드 수정 결과(1 혹은 0)
    -상품삭제
        프로시저명 : ShopDeleteGoods
       	클래스명 : shopping.DeleteShop 
        In파라미터 : 삭제할 상품의 일련번호
        Out파라미터 : 레코드 삭제 결과(1 혹은 0)
*/


--2.상품조회시 쿼리문
SELECT * FROM sh_goods;

SELECT g_idx, goods_name, 
    to_char(goods_price,'9,999,000'), 
    to_char(regidate,'yyyy-mm-dd hh:mi'), p_code
FROM sh_goods WHERE 1=1 
and goods_name like '%노트북%';


--상품 수정 프로시져 생성
create or replace procedure ShopUpdateGoods (
        g_name in varchar2,     --상품명
        g_price in number,      --상품가격
        g_code in number,       --상품코드
        goods_idx in number,    --수정할 상품의 일련번호
        returnVal out number    --레코드 수정 결과
    )
is
begin
    update sh_goods
    set goods_name = g_name,
        goods_price = g_price,
        p_code = g_code
    where
        g_idx = goods_idx;
    
    if SQL%Found then
        --제대로 수정이 되었다면 수정된 행의 갯수 반환
        returnVal := SQL%RowCount;
        commit;
    else
        --수정에 실패했다면 0을 반환
        returnVal := 0;
    end if;
end;
/
--프로시저 검증
var retVal number;
execute ShopUpdateGoods('Memory짱',50000,1,8,:retVal);
print retVal;


--상품 삭제 프로시져 생성
create or replace procedure ShopDeleteGoods (
        goods_idx in number,    --수정할 상품의 일련번호
        returnVal out number    --레코드 수정 결과
    )
is
begin
    delete from sh_goods
    where
        g_idx = goods_idx;
    
    if SQL%Found then
        --제대로 삭제가 되었다면 삭제된 행의 갯수 반환
        returnVal := SQL%RowCount;
        commit;
    else
        --삭제에 실패했다면 0을 반환
        returnVal := 0;
    end if;
end;
/
--프로시저 삭제 검증
var retVal number;
execute ShopDeleteGoods(7,:retVal);
print retVal;



/*
연습문제] 트리거 생성
  : 상품관리 테이블에 데이터가 입력/삭제 되었을때 
  로그테이블[sh_goods_log]에 내역이 남도록 하는 트리거를 작성하시오. 
  업데이트는 제외한다.

트리거명 : shop_log_trigger

상품입력시 : Insert
상품삭제시 : Delete 문자열을 p_action에 입력한다.
*/
set serveroutput on;

create or replace trigger shop_log_trigger
    after insert or delete
    on sh_goods
    for each row
begin
    if Inserting then
        
        dbms_output.put_line('상품입력');
        insert into sh_goods_log
        values (
            goods_log_seq.nextval,
            :new.goods_name,
            :new.g_idx,
            'Insert'
        );
    
    elsif Deleting then
        
        dbms_output.put_line('상품삭제');
        insert into sh_goods_log
        values (
            goods_log_seq.nextval,
            :old.goods_name,
            :old.g_idx,
            'Delete'
        );
        
    end if;
end;
/
insert into sh_goods values 
    (goods_seq.nextval, '냉장고', 900000, sysdate, 1);
select * from sh_goods_log;
delete from sh_goods where g_idx=8;
select * from sh_goods_log;
