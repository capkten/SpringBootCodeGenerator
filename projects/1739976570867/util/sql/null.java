
 #SQL横向select
    SELECT t.id
    FROM users t;

 #CSV横向字段名
    id

#LEFT JOIN
    SELECT
        *
    FROM
    users a
    LEFT JOIN users b
    ON a.users_id=b.users_id
    WHERE 1=1;

#INSERT INTO
    INSERT INTO users ( id )
    VALUES
    (
    ''
    );


#关联更新
    UPDATE users a
    JOIN users_join b ON a.users_id = b.users_id
    SET  a.id = b.id 
    WHERE
    b.users_id IS NOT NULL;

    UPDATE users a,users_join b
    SET  a.id = b.id 
    WHERE a.users_id = b.users_id;

#普通update
    UPDATE users
    SET
        id = ''
    WHERE
        id = ''
    ;



#关联删除
    delete a from users_del as a inner join users as b
    where a.users_id=b.users_id;

#普通删除
    DELETE
    FROM
    users
    WHERE
        id = ''
    ;



