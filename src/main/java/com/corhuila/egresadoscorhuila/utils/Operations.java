package com.corhuila.egresadoscorhuila.utils;

import com.corhuila.egresadoscorhuila.entity.EntityId;

import java.util.Comparator;
import java.util.List;

public class Operations {

    public static int autoIncrement(List<? extends EntityId> list) {
        if(list.isEmpty())
            return 1;
        return list.stream().max(Comparator.comparing(EntityId::getId)).get().getId() + 1;
    }
}
