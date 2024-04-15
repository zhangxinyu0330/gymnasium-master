package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.notice;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface noticeMapper {
    int deleteByPrimaryKey(Integer noticeid);

    int insert(notice record);

    int insertSelective(notice record);

    notice selectByPrimaryKey(Integer noticeid);

    int updateByPrimaryKeySelective(notice record);

    int updateByPrimaryKey(notice record);

    @Select("select * from notice where noticeisdel=0")
    List<notice> findAll();

    @Insert("insert into notice values(#{noticeid},#{noticetime},#{content},#{title}," +
            "#{state},#{venueid},#{noticeisdel})")
    Integer addNotice(@Param("noticeid")Integer noticeid, @Param("noticetime")Date noticetime,
                      @Param("content")String content,@Param("title")String title,
                      @Param("state")Integer state,@Param("venueid")Integer venueid,
                      @Param("noticeisdel")Integer noticeisdel);

    @Update("update notice set noticeisdel=1 where noticeid=#{noticeid}")
    Integer delNotice(Integer noticeid);

    @Update("update notice set content=#{content} where noticeid=#{noticeid}")
    Integer updNoticeContent(@Param("noticeid")Integer noticeid, @Param("content")String content);

    @Update("update notice set title=#{title} where noticeid=#{noticeid}")
    Integer updNoticeTitle(@Param("noticeid")Integer noticeid, @Param("title")String title);

    @Select("select state from notice where noticeid=#{noticeid}")
    Integer findNoticeState(@Param("noticeid")Integer noticeid);

    @Update("update notice set state=#{state} where noticeid=#{noticeid}")
    Integer updNoticeState(@Param("noticeid")Integer noticeid,@Param("state")Integer state);

    @Update("update notice set venueid=#{venueid} where noticeid=#{noticeid}")
    Integer updNoticeVenueid(@Param("noticeid")Integer noticeid,@Param("venueid")Integer venueid);



}