


<div align="center">
  
# 반려 동물을 위한 쇼핑몰 - 포에버펫

## 배포 링크

[포에버펫](http://ec2-13-209-75-154.ap-northeast-2.compute.amazonaws.com:3000/) 에서 이용하실 수 있어요. <br>
[포에버펫API](http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com) 에서 이용하실 수 있어요.

</div>

### Back-End

<summary>✅ 역할</summary>

<div markdown="1"> 
   
- 김창모 : GitHub Actions 과 AWS 를 활용하여 프로젝트의 CICD 파이프라인구현과 AWS RDS 에 MariaDB 생성 ,초기 라이브러리 의존성 세팅 및 상품의 CRUD 를 포함하여 상품 이름검색 , 카테고리 검색 , 신상품 정렬 , 판매순 정렬 을 구현하였고 유저의 장바구니 , 찜목록 , 로그인한 유저의 상품정보 API 를 구현하였습니다.

- 김상복 : 소셜 로그인을 구현하기 위해 OAuth 및 Kakao API를 사용하였고
  Spring Security 와 JWT 토큰을 활용하여 로그인 과
  로그인 보안을 구현하였습니다.
  그 외 유저 정보 조회, 회원가입, 비밀번호 변경, 이메일 중복 확인 기능을 구현하였습니다

- 이준형 : 상품 주문 파트를 담당하여 구현하였고 상품주문 시 결제는 아임포트의 API 의 특성을 파악하여 프론트엔드 개발자님과의 의견공유를 통해 결제 로직을 구현하였습니다. 그외의 주문취소, 회원주문내역확인 API 를 구현하였습니다.

<div align="center">

## 🌈 Members

|                                             [창모](https://github.com/cmong0516)                                              |                                              [상복](https://github.com/AiRn33)                                              |                                               [준형](https://github.com/AlreadyBold)                                                |
| :-----------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------: |
| <img src="https://avatars.githubusercontent.com/u/74821906?v=4" width=200px alt="_"/> | <img src="https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/6087df6c-1820-411a-9e3f-2dabda35bd6f" width=200px alt="_"/> | <img src="https://avatars.githubusercontent.com/u/101548340?v=4" width=200px alt="_"/> |
|                                                           백엔드                                                            |                                                           백엔드                                                            |                                                           백엔드                                                            |

</div>


</div>


<div align="center">


## ✨ Intro

  <img width="300" alt="foreverpet_image" src="https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/f548fdcb-ee2b-426c-a4f0-a0e9f4ba633f"/>
</div>

<div align="center">

\- Back End

![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-square&logo=Spring%20Security&logoColor=white)
![Jpa](https://img.shields.io/badge/Jpa-007396?style=flat-square&logo=Java&logoColor=white)
![QueryDsl](https://img.shields.io/badge/QueryDsl-007396?style=flat-square&logo=Java&logoColor=white)
</p>

\- CI & CD

![AWS EC2](https://img.shields.io/badge/AWS-ec2-FF9900?style=flat-square&logo=amazonec2&logoColor=white)
![AWS S3](https://img.shields.io/badge/AWS-S3-569A31?style=flat-square&logo=amazons3&logoColor=white)
![AWS RDS](https://img.shields.io/badge/AWS-RDS-527FFF?style=flat-square&logo=amazonrds&logoColor=white)
![AWS CodeDeploy](https://img.shields.io/badge/AWS-CodeDeploy-527FFF?style=flat-square&logo=amazonaws&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub-Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white)


\- DataBase

![AWS RDS](https://img.shields.io/badge/AWS-RDS-527FFF?style=flat-square&logo=amazonrds&logoColor=white)
![Maria DB](https://img.shields.io/badge/MariaDB-1F305F?style=flat-square&logo=mariadbfoundation&logoColor=white)

</div>

**💡 반려 동물 친환경 & 비건 서비스 쇼핑몰**

반려 동물 산업이 국내외 소비시장에서 급격히 부상하고 있습니다. 1인가구의 증가, 고령화 등 세계 인구 구조의 변화로 반려동물 양육 인구가 증가하고, 반려동물을 가족처럼 생각하는 문화가 확산 되면서 일명 '펫케어' 산업의 양적 & 질적 성장은 더욱 가속화 될 전망이라고 합니다.

그래서 오랜 시간을 함께 할 반려 동물을 위한 친환경 , 비건 제품 등을 판매하여 소비자들 즉, 반려 동물에게 전해짐으로써 펫 케어를 위한 서비스를 제공 해 주는 웹 사이트를 제작하는 것 입니다.

## ⏩ 기능 시연

<details>
<summary><b>로그인(카카오 소셜 로그인)</b></summary>
<div markdown="1">
  
![포에버-로그인](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/c533a151-4d78-4997-8079-3f4f24ebd61f)

(소셜 로그인)

- 포에버펫 사이트에 접속하면 헤더 오른쪽 상단에 로그인 버튼을 눌러 소셜 로그인 , 일반 로그인을 할 수 있습니다.

- 인증(로그인)을 하면 헤더 상단의 화면이 분기 됩니다.
- http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Kakao%20Login%20API/loginKakao
</div>
</details>

<details>
<summary><b>회원가입</b></summary>
<div markdown="1">
  
![포에버-회원가입](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/5e1b6240-92ef-47a3-995f-9e9c0d1025e1)

- 포에벗 사이트에 접속 하여 회원가입을 클릭하면 사용자 정보와 아이디 중복 확인을 통해 회원가입을 할 수 있습니다.
- http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/User%20Login%20API/signup
</div>
</details>

<details>
<summary><b>메인(로그인,로그아웃)</b></summary>
<div markdown="1">
  
![포에버-인증상태 화면](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/8094d3f4-99c4-4b7e-9d1f-073e985669f7)

- 로그인 또는 회원가입을 하면 헤더 상단의 인증(로그인,로그아웃) 상태가 변동 됩니다.
- http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/User%20Login%20API/login

</div>
</details>

<details>
<summary><b>상품 추천</b></summary>
<div markdown="1">
  
![포에버-상품추천](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/fcf8def7-3fc4-41eb-8332-a2ccc339630b)

- 메인 홈 화면에서 베스트 , 신상품 , 간식 상품들의 API 를 랜덤 호출하여 확인 할 수 있습니다.
- 모든 상품 조회 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Product%20API/allProducts
- 상품 등록순 조회 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Product%20API/newProducts
- 상품 판매순 조회 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Product%20API/bestProducts
</div>
</details>

<details>
<summary><b>상품 상세 페이지</b></summary>
<div markdown="1">
  
![포에버- 상품상세](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/e9034c60-45bd-4300-b71e-5db03608940e)

- 해당 상품 카드를 클릭하면 상품 상세 페이지로 이동하여 상품 상세 정보를 확인 할 수 있고, 장바구니 & 구매를 할 수 있습니다.
- Id로 한가지 상품 조회 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Product%20API/findProductById
</div>
</details>

<details>
<summary><b>전체 상품 페이지</b></summary>
<div markdown="1">
  
![포에버-상품카테고리](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/c7e204b5-a432-4dde-9d9e-0056245c3f7d)

- 햄버거 버튼 또는 메인 홈 카테고리에서 사료 , 간식 , 건강식품을 클릭하면 해당 상품들이 있는 페이지로 이동합니다.
- 상품 카테고리별 조회 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Product%20API/searchProductByCategories
</div>
</details>

<details>
<summary><b>장바구니</b></summary>
<div markdown="1">
  
![포에버 - 장바구니](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/91958746-6022-47bd-b046-5f5b7a1e1bc9)

- 상품 카드에서 장바구니 아이콘을 클릭하면 장바구니에 담은 후 확인 할 수 있고, 헤더 부분에 장바구니 아이콘에 수량을 확인 할 수 있습니다.

- 장바구니에서 수량을 변동 할 수 있고 , 장바구니 상품을 삭제 할 수 있습니다.

- 장바구니 모달 창에서 결제하기 버튼을 클릭하면 결제 페이지로 이동 합니다.
- 장바구니 조회 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Cart%20%26%20Wish%20API/getLoginUserCart
- 장바구니 추가 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Cart%20%26%20Wish%20API/addProductInCart
- 장바구니 제거 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Cart%20%26%20Wish%20API/deleteProductInCart
- 장바구니 수량 + http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Cart%20%26%20Wish%20API/increaseQuantity
- 장바구니 수량 - http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Cart%20%26%20Wish%20API/decreaseQuantity
</div>
</details>

<details>
<summary><b>결제 페이지</b></summary>
<div markdown="1">
  
![포에버-결제_1](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/13e559c8-c09d-4951-85fb-24e7a7721903)

![포에버-결제_2](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/5a2c9de1-3ac5-47d6-a56e-b4f28df3cdb4)

- 상품의 상세 페이지에서 사용자의 정보를 입력 한 후 결제수단인 카카오페이로 주문이 가능합니다.

- 장바구니에서 결제하기를 클릭하면 결제 페이지로 이동하여 주문이 가능합니다.
  
- 주문및 결제 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Order%20API/createOrder
</div>
</details>

<details>
<summary><b>키워드 검색</b></summary>
<div markdown="1">

![포에버-검색](https://github.com/EUN-HA-CHOI/ForeverPet/assets/97012561/9678f4dd-23a9-4b91-b038-2dd274e170fe)

- 사료, 간식, 건강식품 상품들의 키워드로 검색이 가능합니다.

- debounce 를 사용하여 불필요한 함수 호출을 방지 하였습니다.

- 상품 검색 http://ec2-15-164-206-172.ap-northeast-2.compute.amazonaws.com/swagger-ui/index.html#/Product%20API/searchProduct
</div>
</details>

<details>
<summary><b>마이페이지</b></summary>
<div markdown="1">
  
![마이페이지](영상)

- 헤더 쪽 사람 아이콘을 클릭하면 마이페이지로 이동 합니다. 마이페이지에는 사용자의 프로필에서 등급,쿠폰을 확인 할 수 있습니다.

- 마이페이지는 주문내역과 장바구니 두 공간으로 분리 하였습니다.

- </div>
  </details>

<br/>

<br>
<br>


<summary>✅ 기술스택</summary>

<div align="center">
  <table>
    <tr>
      <td align="center" width="25%">
        <img src="https://miro.medium.com/v2/resize:fit:1400/1*ZdgKe8Bm_S0e614c9HiSZg.png" alt="Java" style="width:100px; height:100px;">
      </td>
      <td align="center" width="25%">
        <img src="https://miro.medium.com/v2/resize:fit:692/1*6jXr9K8Vm-5P5VFymsrGlQ.png" alt="SpringBoot3" style="width:100px; height:100px;">
      </td>
      <td align="center" width="25%">
        <img src="https://t1.daumcdn.net/cfile/tistory/99052E345D1DCA1512" alt="Spring Security" style="width:100px; height:100px;">
      </td>
      <td align="center" width="25%">
        <img src="https://img1.daumcdn.net/thumb/R1280x0.fpng/?fname=http://t1.daumcdn.net/brunch/service/user/dkta/image/mZyUgQW1H1vk_TFaK2FZbvZqyBM.png" alt="JWT" style="width:100px; height:100px;">
      </td>
    </tr>
    <tr>
      <td align="center" width="25%">
        <img src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FwQtDM%2Fbtq39llBYYc%2F7soEllYUXQmio3amNwakY0%2Fimg.png" alt="JPA" style="width:100px; height:100px;">
      </td>
      <td align="center" width="25%">
        <img src="https://blog.kakaocdn.net/dn/bA2sTQ/btrHNMfDKIg/1CeHvVFBbb20qqHIQ91CQ0/img.png" alt="QueryDsl" style="width:100px; height:100px;">
      </td>
      <td align="center" width="25%">
        <img src="https://images.velog.io/images/woo00oo/post/fe188ae4-0352-4a63-8e14-8a0b8e116e41/%E1%84%8C%E1%85%A6%E1%84%86%E1%85%A9%E1%86%A8%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%84%8C%E1%85%AE%E1%84%89%E1%85%A6%E1%84%8B%E1%85%AD._004%20(1).png" alt="OAuth" style="width:100px; height:100px;">
      </td>
      <td align="center" width="25%">
        <img src="https://d1.awsstatic.com/logos/partners/MariaDB_Logo.d8a208f0a889a8f0f0551b8391a065ea79c54f3a.png" alt="MariaDB" style="width:100px; height:100px;">
      </td>
    </tr>
  </table>
</div>





- **Java17** 를 선택한 이유는 SpringBoot3 를 사용하기 위한 최소 버전이 17버전 이였고 람다와 스트림을 활용할수 있는 모던자바 버전이기에 선택하였습니다.
- **SpringBoot3** 를 사용하여 초기 개발설정을 편리하게 할수 있었습니다.start.spring.io 에서 필요한 라이브러리 의존성을 추가하고 프로젝트를 시작하였습니다.
- **Spring Security** 를 사용하여 인증인가 구현을 하였고 OAuth 를 사용하여 소셜 로그인 구현을 하였습니다.
- **JPA** 를 통해 SQL 중심 개발이 아닌 객체중심 개발을 하였습니다.간단한 CRUD 는 Spring Data JPA를 사용하였고 동적쿼리 등은 QueryDsl 을 사용하였습니다.
- **JWT** 리소스 사용을 최소화 하기위해 토큰방식의 JWT를 사용하여 로그인을 구현하였습니다.
- **QueryDsl** 을 사용하여 JPA 로 처리하지 못하는 동적쿼리 나 DTO조회 등을 구현하였습니다.
- **OAuth** 을 사용하여 Kakao 로그인 API를 활용하여 kakao 계정을 사용하여 접근할수 있도록 구현하였습니다.
- **MariaDB** 를 사용하였습니다. RDBMS 중 하나를 선택하기로 의견이 모였고 AWS RDS 를 사용할 것이기에 동일 사양 대비 가격이 저렴하고 MySQL 과 문법이 같은 MariaDB 를 택하였습니다.

## CICD

  <img width="100%" alt="foreverpet_image" src="https://images.velog.io/images/rycando/post/96caa22e-d19a-466d-a0db-2a68be4608be/test.001.png"/>

- **Pull Request** : 3명의 백엔드 팀원은 작업할 브랜치를 만들고 기능구현을 하여 main 브랜치에 Pull Request 를 보냅니다.
- **Review** : Pull Request 를 본인 외 2명의 백엔드 팀원이 리뷰합니다.
- **Merge** : 본인 외 2명의 백엔드 팀원의 Approve 가 있어야만 Merge 가 활성화 됩니다.
- **GitHub Actions** : main.yml 파일에 등록된 설정에 따라 Merge 시 파일을 빌드하여 zip 파일을 생성하고 AWS S3 버킷으로 생성된 zip 파일을 업로드 합니다. (초기 push 시에도 배포 자동화 설정을 해놓았지만 리뷰되지 않은 코드들이 실행되어 merge 시에만 실행되게 main.yml 수정)
- **CodeDeploy** : main.yml 파일 설정에 따라 AWS CodeDeploy 를 사용하여 zip 파일 내의 appspec.yml 을 실행합니다.
- **deploy.sh** : appspec.yml 파일에 설정된 EC2 인스턴스 경로에 S3 에 업로드 되어있던 zip 파일을 업로드 하며 hooks 로 등록된 deploy.sh 를 실행시켜 프로젝트를 빌드한다.

