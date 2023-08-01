package com.hello.foreverpet.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderInfoEntity is a Querydsl query type for OrderInfoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderInfoEntity extends EntityPathBase<OrderInfoEntity> {

    private static final long serialVersionUID = 2088035569L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderInfoEntity orderInfoEntity = new QOrderInfoEntity("orderInfoEntity");

    public final com.hello.foreverpet.domain.dto.QAddress address;

    public final StringPath contents = createString("contents");

    public final StringPath memo = createString("memo");

    public final NumberPath<Long> order_no = createNumber("order_no", Long.class);

    public final NumberPath<Long> order_payment_no = createNumber("order_payment_no", Long.class);

    public final NumberPath<Long> order_process = createNumber("order_process", Long.class);

    public final StringPath payment_cd = createString("payment_cd");

    public final NumberPath<Long> purchase_no = createNumber("purchase_no", Long.class);

    public final StringPath user_no = createString("user_no");

    public QOrderInfoEntity(String variable) {
        this(OrderInfoEntity.class, forVariable(variable), INITS);
    }

    public QOrderInfoEntity(Path<? extends OrderInfoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderInfoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderInfoEntity(PathMetadata metadata, PathInits inits) {
        this(OrderInfoEntity.class, metadata, inits);
    }

    public QOrderInfoEntity(Class<? extends OrderInfoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.hello.foreverpet.domain.dto.QAddress(forProperty("address")) : null;
    }

}

