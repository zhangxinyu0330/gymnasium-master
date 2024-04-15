package com.example.gymnasium.mapper;

import com.example.gymnasium.entity.card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

@Mapper
public interface cardMapper {
    int deleteByPrimaryKey(Integer cardid);

    int insert(card record);

    int insertSelective(card record);

    card selectByPrimaryKey(Integer cardid);

    int updateByPrimaryKeySelective(card record);

    int updateByPrimaryKey(card record);

    @Select("select * from card where cardnumber=#{cardnumber}")
    card findByCardNum(Integer cardnumber);

    @Update("update card set userid=#{userid} where cardid=#{cardid}")
    Integer updCardUser(@Param("cardid")Integer cardid, @Param("userid")Integer userid);

    @Select("select * from card where cardid=#{cardid}")
    card findByCardId(Integer cardid);

    @Update("update card set account=#{account} where cardid=#{cardid}")
    Integer updCardAccount(@Param("cardid")Integer cardid, @Param("account")BigDecimal account);

    @Update("update card set account=#{account},point=#{point}, point=#{point},grade=#{grade} " +
            "where cardid=#{cardid}")
    Integer updCard(@Param("cardid")Integer cardid, @Param("account")BigDecimal account,
                    @Param("point")Integer point, @Param("grade")Integer grade);
}