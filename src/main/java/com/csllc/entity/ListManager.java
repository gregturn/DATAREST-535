package com.csllc.entity;

import java.util.Set;

/**
 * This allows an existing persistent list managed by Hibernate to remain if it exists.
 * So Hibernate can better manage the addition and removal of entities
 */
public class ListManager {

    /**
     * Usage: this.persistentList = updateList(this.persistentList, incomingList);
     * @param target
     * @param source
     * @return
     */
    public Set updateList(Set source, Set target) {
        if (target == null) {
            return source;
        } else {
            target.clear();
            target.addAll(source);
            return target;
            // you might need to take care of bidirectional references here
        }
    }
    
}
