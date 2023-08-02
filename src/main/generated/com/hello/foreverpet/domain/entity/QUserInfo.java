package com.hello.foreverpet.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> {

    private static final long serialVersionUID = -747110777L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserInfo userInfo = new QUserInfo("userInfo");

    public final com.hello.foreverpet.auditing.QBaseTimeEntity _super = new com.hello.foreverpet.auditing.QBaseTimeEntity(this);

    public final ListPath<Product, QProduct> cart = this.<Product, QProduct>createList("cart", Product.class, QProduct.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final EnumPath<com.hello.foreverpet.domain.dto.OAuthProvider> oAuthProvider = createEnum("oAuthProvider", com.hello.foreverpet.domain.dto.OAuthProvider.class);

    public final StringPath userAccessToken = createString("userAccessToken");

    public final com.hello.foreverpet.domain.dto.QAddress userAddress;

    public final BooleanPath userDeleteYn = createBoolean("userDeleteYn");

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userNickname = createString("userNickname");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath userPhone = createString("userPhone");

    public final NumberPath<Integer> userPoint = createNumber("userPoint", Integer.class);

    public final StringPath userProfileImage = createString("userProfileImage");

    public final StringPath userPw = createString("userPw");

    public final StringPath userRefreshToken = createString("userRefreshToken");

    public QUserInfo(String variable) {
        this(UserInfo.class, forVariable(variable), INITS);
    }

    public QUserInfo(Path<? extends UserInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserInfo(PathMetadata metadata, PathInits inits) {
        this(UserInfo.class, metadata, inits);
    }

    public QUserInfo(Class<? extends UserInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userAddress = inits.isInitialized("userAddress") ? new com.hello.foreverpet.domain.dto.QAddress(forProperty("userAddress")) : null;
    }

}

