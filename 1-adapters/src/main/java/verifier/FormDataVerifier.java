package verifier;

import entities.ShoppingBillEntity;
import value_objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormDataVerifier {
    public static ShoppingBillEntity verifyBillData(Map<String, List<String>> queryPairs, UsernameVO user) {
        String shopName = null;
        String date = null;

        List<ShoppingItemNameVO> itemNames = new ArrayList<>();
        List<Integer> prices = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : queryPairs.entrySet()) {
            switch (entry.getKey()) {
                case "shopName" -> shopName = entry.getValue().get(0);
                case "date" -> date = entry.getValue().get(0);
                default -> {
                    if (entry.getValue().get(0) == null) {
                        break;
                    }
                    // should only be item[i] and price[i] left
                    if (entry.getKey().startsWith("item")) {
                        itemNames.add(new ShoppingItemNameVO(entry.getValue().get(0)));
                        break;
                    }
                    if (entry.getKey().startsWith("price")) {
                        prices.add(Integer.valueOf(entry.getValue().get(0)));
                    }
                }
            }
        }

        if (shopName == null) {
            throw new IllegalArgumentException("shopName has to be provided");
        }
        if (date == null) {
            throw new IllegalArgumentException("date has to be provided");
        }
        if (itemNames.size() != prices.size()) {
            throw new IllegalArgumentException("all item names and prices need to be provided");
        }

        ShopNameVO shopNameVO = new ShopNameVO(shopName);
        DateVO dateVO = new DateVO(date);

        List<ShoppingItemVO> items = new ArrayList<>();
        for (int i = 0; i < itemNames.size(); i++) {
            items.add(new ShoppingItemVO(prices.get(i), itemNames.get(i)));
        }

        return new ShoppingBillEntity(0, shopNameVO, dateVO, user, items); // placeholder id temporary
    }

}
