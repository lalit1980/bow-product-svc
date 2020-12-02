package com.boozeonwheel.product.domain.category;

public enum EntityType {
  Root, Dimension, Node, Include;

  public static EntityType get(final String type) {
    for (final EntityType e : EntityType.values()) {
      if (e.name().equalsIgnoreCase(type)) {
        return e;
      }
    }
    return null;
  }

}


