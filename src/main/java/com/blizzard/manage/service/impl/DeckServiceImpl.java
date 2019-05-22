package com.blizzard.manage.service.impl;

import com.blizzard.common.enums.CareerEnum;
import com.blizzard.common.exception.BaseException;
import com.blizzard.manage.mapper.CardMapper;
import com.blizzard.manage.mapper.DeckCardMapper;
import com.blizzard.manage.mapper.DeckMapper;
import com.blizzard.manage.pojo.Deck;
import com.blizzard.manage.pojo.DeckCard;
import com.blizzard.manage.service.DeckService;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-21 16:05
 */
@Service
public class DeckServiceImpl implements DeckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeckServiceImpl.class);

    @Autowired
    private DeckMapper deckMapper;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private DeckCardMapper deckCardMapper;

    @Override
    public List<Deck> getAllByUId(Integer uid) {
        return deckMapper.getAllByUId(uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(String name, CareerEnum careerEnum, List<DeckCard> deckCardList, Integer uid) {
        // 校验 这里只校验总数和卡牌最高数量是否正确 不校验卡牌所属职业等其他信息
        checkDeck(deckCardList);
        Deck deck = new Deck();
        deck.setName(name);
        deck.setCareerEnum(careerEnum);
        deck.setUid(uid);
        deckMapper.insert(deck);
        deckCardMapper.batchInsert(deck.getId(),deckCardList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer deckId, Integer uid) {
        int num = deckMapper.deleteByIdAndUId(deckId,uid);
        if(num != 1){
            throw new BaseException(106,"删除失败");
        }
        deckCardMapper.deleteByDId(deckId);
    }

    private void checkDeck(List<DeckCard> deckCardList){
        Map<Integer, DeckCard> map = deckCardList.stream().collect(Collectors.toMap(DeckCard::getCardId, self -> self));
        List<Integer> legendCardIdList = cardMapper.filterLegendCard(new ArrayList<>(map.keySet()));
        int totalNum = 0;
        for (DeckCard deckCard : deckCardList) {
            if(legendCardIdList.contains(deckCard.getCardId())){
                if(deckCard.getNum() != 1){
                    throw new BaseException(103,"传说卡牌在卡组中的上限不能超过1张");
                }
            }else if(deckCard.getNum() > 2){
                throw new BaseException(104,"非传说卡牌最多不能超过2张");
            }
            totalNum += deckCard.getNum();
        }
        if(totalNum != 30){
            throw new BaseException(105,"卡组的卡牌数量必须是30张");
        }
    }



}
