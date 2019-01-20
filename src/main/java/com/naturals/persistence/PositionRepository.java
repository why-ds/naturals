package com.naturals.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.Position;

public interface PositionRepository  extends CrudRepository<Position, Long>, QuerydslPredicateExecutor<Position>{

}
