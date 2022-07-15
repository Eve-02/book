package com.xxx.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new LinkedHashMap<>();


    /**
     * 添加商品项
     * @param cartItem 商品
     */
    public void addItem(CartItem cartItem){
        // 先查看购物车中是否已经添加此商品，若有，则增加数量；否则添加到 集合 中
        CartItem item = items.get(cartItem.getId());
        if(item == null ){
            // 没有添加过此商品
            items.put(cartItem.getId(), cartItem);
        }else{
            // 添加过的情况
            item.setCount(item.getCount() + 1); // 数量加一
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); // 更新总金额
        }
    }

    /**
     * 删除商品项
     * @param id 商品
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id 商品
     * @param count 数量
     */
    public void updateCount(Integer id,Integer count){
        // 先查看购物车中是否有此商品
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            cartItem.setCount(count); // 修改商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount()))); // 更新总金额
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

}
