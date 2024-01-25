package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "orders")
public class Order extends SerializableObject{
    @ManyToOne
    @JsonIgnore
    private Client clientRefId;

    @JsonProperty("clientId")
    public String getClientId() {
        return (clientRefId != null) ? clientRefId.getRefId() : null;
    }

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "orders_items",
            joinColumns = @JoinColumn(name = "order_ref_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_ref_id")
    )
    private List<Inventory> items;

    @JsonProperty
    public List<String> getItems(){
        List<String> itemList = new ArrayList<>();
        if(items != null){
            for(Inventory i : items){
                itemList.add(i.getRefId());
            }
        }
        return itemList;
    }

    private float totalPrice;

    private Orderstatus orderStatus;

    private String notes;

}

