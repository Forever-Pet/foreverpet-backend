package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.repository.ProductJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitDataService {

    private final ProductService productService;
    private final ProductJpaRepository productJpaRepository;

    private final UserInfoJpaRepository userInfoJpaRepository;

//    @PostConstruct
    public void init() {

        String[] productNames = {
                // 은하님 스낵
                "고위드테일 소고기 크런칩 85g",
                "고위드테일x포에버펫 슈퍼바이츠 70g",
                "고위드테일 슈퍼바 25g",
                "아더마스 프로틴볼 베지미트볼",
                "스위피 오독오독 바삭",
                "코코테라피 블루베리 코블러",
                "빅스비 파크팝스 크런치",
                "포포네 단호박 코코넛 파운드",
                "포포네 가지가지치킨비스킷",
                "씽크라이크펫 비건 해피트릿",
                // 솜사탕님 비타
                "강아지 뉴트리션 트릿 관절케어 300g",
                "후디스펫 장케어 유산균 산양유 60g",
                "하트업 강아지 심장 면역 영양제",
                "강아지 기침 기관지 협착증 호흡기 영양제",
                "강아지 피부병 아토피 피부 장벽 강화 피부염 영양제",
                "뉴트리칼 독 비타민 미네랄 강아지 영양제 120.5g",
                "브레인펫트릿 160g 강아지 뇌 영양제",
                "강아지 뉴트리바운드 필수 영양소 공급 영양제",
                "강아지 하루 한포 종합 멀티 영양제 30p",
                "초유단백질 강아지 영양제",
                // 엘라님 간식
                "아침애 수제사료 오리&연어&감자 1.8kg",
                "네츄럴코어 에코 9a 시니어 오리&고구마 6kg",
                "로얄캐닌 푸들 어덜트 3kg 피모관리",
                "로얄캐닌 미니 라이트웨이트 케어 3kg 체중조절",
                "ANF 6Free 플러스 소고기&연어 5.6kg",
                "now 그레인프리 어덜트 스몰브리드 2.72kg",
                "더독 닥터소프트 치킨 시니어 3kg",
                "네츄럴코어 에코 6 센시티브 솔루션 연어 큰알갱이 10kg",
                "뉴트리나 건강백서 건강한 6세이상 10.2kg",
                "네츄럴코어 홀리스틱 베네 M32 멀티프로테인 6kg"
        };

        String[] productDescriptions = {
                //은
                "소고기 중 지방이 적은 홍두깨살 만을 이용하여 만들어진 바삭한 소고기 크런칩이랍니다. 호주산 신선한 소고기를 이용하여 기력회복에 좋아요. 고위드테일만의 저온건조로 더 바삭하고 맛있는 간식이 탄생한답니다.",
                "고위드테일의 슈퍼바가 더 작아졌어요. 국내산 무항생제 오리 안심을 베이스로 만든 고단백 간식, 슈퍼바이츠는 외출 시에 간편하게 휴대하여 든든한 식사 대용으로 급여할 수 있어요. 약 1cm의 트릿으로 아이들의 노즈워크에 활용하거나, 훈련용 보상 간식으로 주기 알맞은 간식입니다. ",
                "쫀쫀한 고위드테일만의 슈퍼바를 소개해요, 아이들이 좋아하는 오리고기를 베이스로 만들어낸 슈퍼바는 불포화지방산이 가득한 오리고기 뿐 만이 아니라 소화에 도움이 되는 단호박, 그리고 미네랄이 함유되어 있는 맥주효모와 올리브오일이 더해져 더 쫀쫀한 식감의 바 형태 간식이 만들어졌답니다. 작게 자르는 데에도 어려움이 없어 아이들의 노즈워크에 활용하거나, 스틱형태로 잘라 껌처럼 급여도 가능한 만능 간식이에요.",
                "야채와 고기를 믹스하여 밀도감있게 완성한 미트볼 간식입니다. 주식의 토퍼 또는 산책 시 가볍게 챙기기에 용이한 제품입니다.",
                "100% 자연 재료로 만든 건강한 과자같은 식감의 간식입니다, 치아가 약한 아이에게도 걱정없이 급여가 가능합니다 ,고양이도 먹을 수 있는 간식입니다.",
                "동물성 단백질 무함유로 단백질 제한식 중인 반려동물에게 좋습니다 ,그레인&글루텐 프리 제품입니다 , 인공향, 인공색소, 보존제가 포함되어 있지 않습니다 , 코코넛 오일의 지방사슬 지방산은 다이어트에 도움이 됩니다.",
                "팝콘처럼 바삭바삭한 식감의 저칼로리 간식을 만나보세요. 강아지가 정말 좋아하는 고구마와 사과로 만들어 냄새가 아주 좋답니다. 사탕수수로 단맛을 내어 맛도 좋지요. 이렇게 맛있는데 한 개에 4kcal 정도밖에 되지 않는 저칼로리 간식이니, 부담 없이 급여할 수 있겠죠?",
                "푹 찐 단호박에 락토프리 우유와 코코넛을 구워낸 파운드입니다. ,케롭&시금치 천연 재료로 건강하게 급여 가능합니다. ,부드러운 식감으로 전 연령 급여 가능합니다. ,무첨가 무방부제인 건강한 간식입니다. ,떡과 빵의 중간 식감으로 쫀득하고 부드러운 식감입니다.",
                "잘게 다진 국내산 닭가슴살에 스팀 한 가지를 골고루 섞은 바삭하게 만든 비스킷입니다. ,훈련 및 산책용 트릿으로 주기 좋습니다. ,가지 껍질에 함유된 나스닌이 색소 효과를 주었으며 콜레스테롤 수치 상승을 억제해 줍니다. ,체내 독소 배출을 촉진시켜 줍니다.",
                "말랑하고 부드러운 식감의 트릿입니다. ,휴먼 그레이드 원료로 제작된 저알러지, 저칼로리 간식입니다. ,뛰어난 기호성으로 아이들의 즐거움을 더해줍니다. ,고양이 친구들도 급여가 가능합니다.",

                //솜
                "연골을 구성하는 필수 성분인 글루코사민 첨가로 관절 건강에 도움을 줍니다.",
                "반려동물이 좋아하는 고소하고 상큼한 밀크 요거트 맛으로 소화력이 좋습니다.",
                "산소 이용률을 높여 심장 세포 에너지 생성에 도움을 주는 영양제 입니다.",
                "반려동물의 호흡기 및 기관지 건강에 도움을 주며 항산화, 향균작용에 도움을 줍니다.",
                "과민 피부 상태 개선에 도움, 피부 향산화에 도움, 보습 및 장벽 강화, 수분공급까지 한번에 !",
                "비타민 및 미네랄 보충을 위한 영양제",
                "메모페놀 성분으로 집주역 및 기억력 향상에 도움을 주며, 레스베라트롤 성분 사용 !",
                "수분공급, 회복에 필요한 필수 영양소 공급, 장의 운동을 도와주는 프리바이오틱 공급 !",
                "눈물 자국 피모 관리, 전체적인 관리, 종합 건강관리 포함하여 외적인 관리가 필요한 아이에겐 추천하는 제품 입니다 !",
                "나의 아이의 근력 감소로 인해 면역력이 취약하여 잘 걷지 못한다면 ? 이 제품으로 4배 이상의 단백질을 보충 해주세요 !",

                //엘
                "아침애 수제사료 오리&연어&감자는 천연 기능성 원료에 정성을 담아 국내 생산으로 만든 천연 수제 사료입니다. 신선한 생고기와 생선, 곡물로 비타민과 미네랄이 조화를 이뤄 기호성을 높였으며, 연어를 첨가해 건강한 피부&피모 개선에 도움을 주고, 블루베리를 함유하여 눈, 뇌세포의 노화 예방에 도움을 줍니다. 천연 항상화제를 넣어 면역 시스템 유지에도 좋습니다. 200g 9봉지로 개별 포장되어 있어 청결하고 안전하게 보관할 수 있습니다.",
                "네츄럴코어 에코 9a 시니어 오리&고구마 9세 이상은 치아와 잇몸이 약하고 소화능력이 떨어지며 관절이 약한 노령견용 사료로 미국 USDA와 유럽 ECOCERT로부터 유기농 인증을 획득한 믿을 수 있는 유기농 식품으로 농약, 화학비료, 인공색소, 방부제 및 GMO로부터 안전한 제품입니다.",
                "로얄캐닌 푸들 어덜트는 곱슬한 피모를 더욱 생기있게 가꿔주는데 도움을 주는 사료로, 생후 10개월 이상의 푸들을 위한 전용사료입니다. 풍부한 오메가3지방산(EPA & DHA) 보라지 오일 함유로 푸들의 풍성한 털 피모를 건강하게 유지할 수 있도록하며, 단백질 성분은 털 피모가 아름답게 자랄 수 있도록 하며, 적당한 근육량유지를 도와주고, 일반 단백질원에 특수발효처리를 하여 소화흡수도가 매우 높은 L.I.P.단백질을 사용하여 변 냄새를 줄여줍니다. ",
                "로얄 캐닌 미니 라이트 웨이트 케어는 로얄캐닌의 체중 조절 기능성 사료입니다. 고품질의 단백질로 인해 다이어트 중에도 근손실을 최소화할 수 있으며, 다량의 섬유질이 함유되어 적게 먹어도 포만감을 느낄 수 있도록 도움을 줍니다. 또한 오메가3 지방산을 첨가하여 체중 과다로 인한 관절 부담을 개선하는데 효과적이며, 낮은 지방 함량을 지녀 체중 조절에 적합합니다.",
                "ANF 6Free 플러스 소고기 연어는 천연 유기농 원료를 주원료로 사용하였습니다. 천연 항산화 물질이 일반 원료에 비해 40% 이상 함유되어 있어 면역력 강화와 알러지에 예민한 애견의 피부모질 개선에 도움이 됩니다. ANF 유기농 6FREE는 잔류 농약이나 화학첨가물과 같은 유해 물질이 포함되지 않은 안전한 원료로 만든 사료로, 건강을 해칠 수 있는 GMO(유전자 변형 물질), 항생제, 살충제, 호르몬제, 합성착색료, 화학보존료 총 6가지 유해물질을 사용하지 않았습니다.",
                "now 그레인프리 어덜트는 냉동하지 않은 사람이 섭취가능한 등급의 칠면조, 오리, 연어와 오일, 각종 야채와 과일을 사용하였습니다. now의 사료들은 지역농장에서 생산되는 달걀, 고기, 과일 및 야채 등을 지역 농민으로부터 공급받아 증기로 천천히 찌는 저온저속 기술로 가공처리하여 영양소 파괴를 막고 효육을 극대화 하였습니다. 칠면조와 닭고기는 단백질 공급해 주며, 연어는 필수 지방산이 함유되어 있습니다. ",
                "Dr.soft는 성장단계별 맞춤 사료로 건식사료와 동일한 제조공법이지만 열을 가하여 발포처리하는 방식으로 사료의 식감이 말랑말랑 부드러우며 체내 소화율이 높고, 반려견의 치아에 무리가 없다는 특징을 가지고 있습니다. Dr.soft 치킨 시니어는 지방 함유량이 적고 고단백 재료인 닭고기를 주원료로 하였으며, 닭고기에는 비타민 B3가 풍부해 반려견의 신진대사를 원활히 해 주며 영양공급에 도움이 됩니다. 탄수화물과 다량의 비타민, 미네랄이 함유된 곡물을 사용하여 아미노산을 제공해 주며, 게에서 추출한 글루코사민과 상어 연골에서 추출한 콘드로이친 성분으로 인하여 관절건강에 도움이 됩니다.",
                "네츄럴코어   에코 6 센시티브 솔루션은 비타민B1, B2 등이 풍부한 신선한 연어 순살코기 다량함유로 피부건강에 뛰어난 사료로 미국 USD와 유럽 ECOCERT로부터 유기농 인증을 획득한 믿을 수 있는 식품으로 농약, 화학비료, 인공색소, 방부제 및 GMO로부터 안전한 제품입니다. 또한, 유기농 원료에 밀, 옥수수, 콩 글루텐 등을 전혀 포함하지 않고 있어 민감성 피부, 아토피, 식이알러지가 있는 애견에게 적합하며 다량으로 함유되어 있는 천연 항상화 물질인 폴리페놀이 각종 질병과 노화의 원인이 되는 활성산소로부터 건강한 세포를 보호해 줍니다.",
                "뉴트리나 건강백서 건강한 6세이상은 6세 이상 반려견의 건강을 위한 토탈케어 사료입니다. 나이가 들면 사람과 마찬가지로 신지대사가 감소하고 각종 장기기능이 조금씩 떨어지며, 피부나 털 상태도 예전같지 않게 되기 때문에 6세이상 반려견의 영양은 달라야 합니다. 세심하게 설계 된 뉴트리나 건강백서 건강한 6세+로 활력 넘치는 건강한 삶을 준비해 주세요. 반려견의 나이 6세는 사람나이로 40~50세에 해당되며, 반려견 나이 16세는 80~120세에 해당 됩니다. 300g씩 낱개 개별포장 되어 있으니 구매시 참고하여 주시길 바랍니다.",
                "네츄럴코어 홀리스틱 베네 M32 멀티프로테인은 최적의 영양밸런스를 자랑하는 사료이며, 뼈를 발라낸 국내산 오리고기, 닭살코기 분말 등 양질의 단백질원을 함유하여 강아지에게 필요한 양질의 단백질을 풍부하게 제공해주며, 아스타잔틴이 함유된 사료로 키운 오리고기는 면역력과 항산화 기능을 향상시키고 스트레스 및 눈의 피로를 감소시켜 줍니다. 비타민나무로 불리는 씨벅턴은 사과의 200~800배의 비타민을 함유하고 있으며 비타민A, C가 동시에 포함된 유일한 과실로 강력한 항산화 효과뿐아니라 각종 영양소의 신진대사율을 높여 비만 예방과 심혈관 질환 예방에 도움을 줍니다. "

        };

        int[]productPrices = {
                //은
                15000,
                89000,
                28500,
                14000,
                15900,
                18000,
                16000,
                7200,
                5400,
                5300,
                //솜
                15000,
                42900,
                62700,
                79800,
                79000,
                12400,
                25000,
                29500,
                40000,
                39900,
                //엘
                27000,
                66000,
                54000,
                53900,
                63900,
                53000,
                24000,
                100000,
                77000,
                75000
        };

        String[] productImages = {
                //은
                "https://wooof.co.kr/web/product/medium/202206/6612fd4ff0065b5692e3ec2bfe6cd018.png",
                "https://wooof.co.kr/web/product/medium/202304/2993742da638d3724cf51eac79d59e71.png",
                "https://wooof.co.kr/web/product/medium/202107/f570b196f1f64dde5fdaa7c5a42768a4.jpg",
                "https://wooof.co.kr/web/product/medium/202207/eafcfbbb1c4cabda8e65a2824b2cec4b.jpg",
                "https://wooof.co.kr/web/product/medium/202210/dc482f0949700435f7184ed6444a4fcc.jpg",
                "https://wooof.co.kr/web/product/medium/202307/434d1ef3d21d9dff37e6ca382a8a5b7b.jpg",
                "https://wooof.co.kr/web/product/medium/202307/352093ee298cac1c9f1dba9e05aef18a.jpg",
                "https://wooof.co.kr/web/product/medium/202301/0366aa95e19704f6db900e561c07d9d0.jpg",
                "https://wooof.co.kr/web/product/medium/202301/cee33236b5acb28b785f3ed630f61f0f.jpg",
                "https://wooof.co.kr/web/product/medium/202304/3378d590e72ee236db9a5b365a7feb5b.png",
                //솜
                "https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/602398423909261-db455970-13a0-43c7-9c21-e332d13bd21d.jpg",
                "https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2517005899731778-8cd724b9-156c-4a03-a852-8777bc1e9556.jpg",
                "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/fe70/d2516fca4fc60f44bc401994f7cc8fc71e57483c07625801d881c787f3a2.png",
                "https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/4b68/5af542993d61a55889ccf753916fd69f81366f49a610ca0d685290dab267.png",
                "https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/872d/65ae87519664590530987571ea75dcfb892118751fb02039cc73d48e5a07.png",
                "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/03/13/14/2/b67dbeea-9f1c-4f43-8e87-b903e0a4c404.jpg",
                "https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/a75e/ded48628fec5709408151e9f5f5d6c6289dd91f3dbbe5f896dc0ab1a0e70.png",
                "https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/971940325732852-dd6521d5-923f-4833-99b9-ed8e61fd0b94.jpg",
                "https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2022/03/31/10/8/45d53c32-e1f8-472c-ba89-c5f1e9f65933.jpg",
                "https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/44a7/3c6387c41e5a0d3333feef993e4faff4b9bc0cc9815e31b48ad4c3cd15cf.png",
                //엘
                "https://img.dogpre.com/mobile/dogpre/product/41/40576_original_01892171.png",
                "https://img.dogpre.com/mobile/dogpre/product/18/17551_detail_01141911.png" ,
                "https://img.dogpre.com/mobile/dogpre/product/82/81863_detail_01549482.png",
                "https://img.dogpre.com/mobile/dogpre/product/79/78931_detail_01396836.png",
                "https://img.dogpre.com/mobile/dogpre/product/56/55596_original_01256815.png",
                "https://img.dogpre.com/mobile/dogpre/product/87/86168_detail_01619012.png",
                "https://img.dogpre.com/mobile/dogpre/product/40/39515_detail_01470976.png",
                "https://img.dogpre.com/mobile/dogpre/product/28/27255_original_01378031.png",
                "https://img.dogpre.com/mobile/dogpre/product/41/40873_detail_01007280.png",
                "https://img.dogpre.com/mobile/dogpre/product/85/84863_detail_01003993.png"

        };
        String[]brandName = {
                //은
                "고위드테일",
                "고위드테일",
                "고위드테일",
                "아더마스",
                "스위피",
                "코코테라피",
                "빅스비",
                "단호박",
                "포포네",
                "씽크라이크펫",
                //솜
                "Tamsaa",
                "foodis",
                "DOCTOR BY",
                "DOCTOR BY",
                "DOCTOR BY",
                "tomlyn",
                "PUPPYKITTYRANG",
                "Virbac",
                "Penovis",
                "DOCTOR BY",
                //엘
                "아침애",
                "네츄럴코어",
                "로얄캐닌",
                "로얄캐닌",
                "ANF",
                "NOW",
                "더독",
                "네츄럴코어",
                "뉴트리나",
                "네츄럴코어"
        };

        String[] categories = {"SNACK","SNACK","SNACK","SNACK","SNACK","SNACK","SNACK","SNACK","SNACK","SNACK",
                "BITA","BITA","BITA","BITA","BITA","BITA","BITA","BITA","BITA","BITA",
                "FOOD","FOOD","FOOD","FOOD","FOOD","FOOD","FOOD","FOOD","FOOD","FOOD"};



        if (productJpaRepository.findAll().size() == 0) {
            for (int i = 0; i < productNames.length; i++) {
                NewProductRequest newProductRequest = NewProductRequest.builder()
                        .productName(productNames[i])
                        .productDescription(productDescriptions[i])
                        .categories(categories[i])
                        .productPrice((long) productPrices[i])
                        .productImage(productImages[i])
                        .brandName(brandName[i])
                        .build();
                productService.createProduct(newProductRequest);
            }
        }
    }
}
