<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.hzy.dao.UsersDao">

    <!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="com.hzy.entity.UsersEntity" id="UsersMap">
                <result property="id" column="id"/>
    </resultMap>

</mapper>