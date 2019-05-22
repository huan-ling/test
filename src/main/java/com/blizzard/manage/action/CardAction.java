package com.blizzard.manage.action;

import com.blizzard.common.entity.Card;
import com.blizzard.common.enums.CardRaceEnum;
import com.blizzard.common.enums.CardTypeEnum;
import com.blizzard.common.enums.CareerEnum;
import com.blizzard.common.util.HttpUtil;
import com.blizzard.common.util.IntegerUtil;
import com.blizzard.common.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-18 14:45
 */
@Component("cardAction")
public class CardAction {

    public List<Card> getCardByReptile(String url){
        //http://www.hstopdeck.com/cards/?view=table
        Map<String,String> map = new HashMap<>();
        // 伪装一下浏览器
        map.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        String html = HttpUtil.doGet(url, map);

        Document document = Jsoup.parse(html);
        Elements elements = document.select("tbody tr");
        List<Card> cardList = new ArrayList<>();
        for (Element elemenet : elements) {
            Card card = new Card();
            Element firstElement = elemenet.select("a").first();
            String img = firstElement.attributes().get("data-tooltip-img");
            String href = firstElement.attributes().get("href");
            String name = firstElement.children().first().html();
            String desc = elemenet.select("small").html();
            Elements elementTds = elemenet.select("td");
            Element careerElement = elementTds.get(1);
            card.setCareerEnum(getCareerEnum(careerElement.html()));
            Element cardTypeElement = elementTds.get(3);

            CardTypeEnum cardTypeEnum = getCardTypeEnum(cardTypeElement.html());
            switch (cardTypeEnum){
                case HERO :
                    card.setArmor(IntegerUtil.getInt(handleCardType(href,"护甲值")));
                    break;
                case MAGIC:
                    break;
                case RETINUE:
                    String cardRaceStr = handleCardType(href,"种族");
                    card.setCardRaceEnum(getCardRaceEnum(cardRaceStr));
                    break;
                case WEAPON:
                    card.setDurability(IntegerUtil.getInt(handleCardType(href,"护甲值")));
                    break;
                default:
                    break;
            }
            card.setCardTypeEnum(cardTypeEnum);

            card.setManaValue(IntegerUtil.getInt(elementTds.get(4).html()));
            card.setAttackForce(IntegerUtil.getInt(elementTds.get(5).html()));
            card.setBloodVolume(IntegerUtil.getInt(elementTds.get(6).html()));

            card.setImg(img);
            card.setName(name);
            card.setDesc(desc);

            cardList.add(card);
        }
        return cardList;
    }

    public String handleCardType(String href,String key){
        Map<String,String> map = new HashMap<>();
        // 伪装一下浏览器
        map.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        String html = HttpUtil.doGet("http://www.hstopdeck.com"+href, map);
        Document document = Jsoup.parse(html);
        Elements elements = document.select(".col-md-14 ul li");
        for(int i=0;i<elements.size();i++){
            Element element = elements.get(i);
            String str = element.select("strong").text();
            if(StringUtil.isNotNull(str) && str.contains(key)){
                if(str.contains("种族")){
                    return element.select("a").text();
                }
                return element.ownText();
            }
        }
        return "";
    }

    public static CareerEnum getCareerEnum(String name){
        switch (name){
            case "中立":
                return CareerEnum.NEUTRALITY;
            case "德鲁伊":
                return CareerEnum.DEROOY;
            case "法师":
                return CareerEnum.MAGE;
            case "猎人":
                return CareerEnum.HUNTSMAN;
            case "圣骑士":
                return CareerEnum.PALADIN;
            case "牧师":
                return CareerEnum.PASTOR;
            case "萨满":
                return CareerEnum.SHAMAN;
            case "潜行者":
                return CareerEnum.STALKER;
            case "术士":
                return CareerEnum.WAELOCK;
            case "战士":
                return CareerEnum.WARRIOR;

            default:
                return null;
        }
    }

    public static CardTypeEnum getCardTypeEnum(String name){
        switch (name){
            case "英雄":
                return CardTypeEnum.HERO;
            case "法术":
                return CardTypeEnum.MAGIC;
            case "随从":
                return CardTypeEnum.RETINUE;
            case "武器":
                return CardTypeEnum.WEAPON;

            default:
                return CardTypeEnum.OTHER;
        }
    }

    public static CardRaceEnum getCardRaceEnum(String name){
        if(StringUtil.isNull(name)){
            return CardRaceEnum.WITHOUT;
        }
        CardRaceEnum[] values = CardRaceEnum.values();
        for (CardRaceEnum cardRaceEnum : values){
            if(name.equals(cardRaceEnum.getDesc())){
                return cardRaceEnum;
            }
        }
        return CardRaceEnum.WITHOUT;
    }
}
