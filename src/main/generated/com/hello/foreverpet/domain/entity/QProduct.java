package com.hello.foreverpet.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1017704511L;

    public static final QProduct product = new QProduct("product");

    public final com.hello.foreverpet.auditing.QBaseTimeEntity _super = new com.hello.foreverpet.auditing.QBaseTimeEntity(this);

    public final StringPath brandName = createString("brandName");

    public final EnumPath<com.hello.foreverpet.domain.dto.Categories> categories = createEnum("categories", com.hello.foreverpet.domain.dto.Categories.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> numberOfSold = createNumber("numberOfSold", Long.class);

    public final StringPath productDescription = createString("productDescription");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productImage = createString("productImage");

    public final StringPath productName = createString("productName");

    public final NumberPath<Long> productPrice = createNumber("productPrice", Long.class);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

