package com.gameserver.gd.mapper;

import com.gameserver.gd.entity.Deck;
import com.gameserver.gd.entity.DeckExample;
import com.gameserver.gd.entity.DeckKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeckMapper {
    int countByExample(DeckExample example);

    int deleteByExample(DeckExample example);

    int deleteByPrimaryKey(DeckKey key);

    int insert(Deck record);

    int insertSelective(Deck record);

    List<Deck> selectByExample(DeckExample example);

    Deck selectByPrimaryKey(DeckKey key);

    int updateByExampleSelective(@Param("record") Deck record, @Param("example") DeckExample example);

    int updateByExample(@Param("record") Deck record, @Param("example") DeckExample example);

    int updateByPrimaryKeySelective(Deck record);

    int updateByPrimaryKey(Deck record);
}