package com.bookstore.behaviour;

public interface PhysicalProduct {
  public Integer getStock();

  public void reduceStock(Integer quantity);
}
