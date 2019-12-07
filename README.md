# RecoFashion-server [![Build Status](https://travis-ci.org/BaekGeunYoung/recofashion-server.svg?branch=master)](https://travis-ci.org/BaekGeunYoung/recofashion-server)
# 목표 서비스
패션 코디 추천 서비스

### 기능
매일 데일리 코디를 추천해준다.

- 배색 조합 관련 이론을 참고해 사용자에게 상하의 배색 조합 추천
 + 추가로 피부톤, 개인 선호, 지난 날들의 데이터 등을 고려해 종합적인 recommendation 제공
- 그 날의 날씨를 고려해 입을만한 옷의 종류 추천
- 상하의 배색 조합을 추천해 주면서 참고할 만한 관련 패션 사진들 띄워주기

### project focus
- kotlin + spring-boot로 application 만들기
- 객체지향 원칙(SOLID) 지켜가며 만들기
- clean architecture를 똑같이 따라할 필요는 없지만, clean architecture에서 제시된 설계원칙 지키기
- 패션 사진 가져올 때 크롤러 사용
- 날씨 관련 open api 사용하기
- 매일 최적의 코디를 제시할 수 있는 적절한 알고리즘 구상하기

---
# Stack
## Frontend
- Javascript
- React
- React hooks
- React router
- Typescript
- Context API
- Axios
- TailwindCss
- React DnD
- React Color
- Reactstrap
- Cheerio
- GeoLocation API
- Open Weather API

## Backend
- Kotlin
- Spring-boot
- Security
- Mysql (Running on Docker)
- JPA
- Mockito
- JUnit

## CI/CD & External Infra
- AWS EC2
- AWS CodeDeploy
- AWS S3
- Travis CI
- Docker

---

# Simulation

## Main Page

![image.png](https://images.velog.io/post-images/dvmflstm/f8a0e360-14ae-11ea-90e2-9568aa518b84/image.png)

왼쪽 부분은 회원가입 및 로그인, 패션 추천을 받을 수 있는 SearchForm을 띄워준다.

![image.png](https://images.velog.io/post-images/dvmflstm/7e772030-14af-11ea-8f12-8b681d7ea05a/image.png)

정상적으로 로그인 한 후에는 오른쪽 사진처럼 그 날 입고 싶은 옷의 테마를 골라 코디 추천을 받을 수 있고, 날씨 관련 api 및 geolocation api를 통해 현재 위치의 현재 온도를 띄워주고 있다. 테마로는 모노톤, 파스텔톤, 디프톤, 비비드톤을 고를 수 있다.

![image.png](https://images.velog.io/post-images/dvmflstm/d798a940-14af-11ea-90e2-9568aa518b84/image.png)

로그인한 경우에는 좌측 상단에 user pofile을 볼 수 있는 버튼이 나타난다.

![image.png](https://images.velog.io/post-images/dvmflstm/ea9902b0-14af-11ea-8f12-8b681d7ea05a/image.png)

여기서 간단한 사용자 정보를 조회할 수 있고, 개인의 선호 색상을 선택할 수 있다.

Main page 우측에 보이는 color picker는 어플리케이션으로부터 추천을 받는 것 뿐 아니라 임의의 색상에 대해 적절한 색상 조합을 추천받고 싶을 때 사용하는 기능이다.

![image.png](https://images.velog.io/post-images/dvmflstm/05d7cd90-14b0-11ea-8f12-8b681d7ea05a/image.png)


## Recommend Page

![image.png](https://images.velog.io/post-images/dvmflstm/5d4edaf0-14b0-11ea-9f39-516187c18223/image.png)

파스텔톤으로 테마를 설정하고 추천을 받았을 때 나오는 화면이다. 해당 컬러에 대한 톤인톤 방식의 조합과 톤온톤 방식의 조합을 제시해주고, 그날 날씨에 맞는 옷의 종류를 추천해준다.

![image.png](https://images.velog.io/post-images/dvmflstm/a148bdc0-14b0-11ea-9f39-516187c18223/image.png)

이 컬러박스들은 drag & drop을 통해 우측에 보이는 Today's Top Color와 Today's Pants Color 부분에 자유롭게 색상을 입힐 수 있다.

![image.png](https://images.velog.io/post-images/dvmflstm/0eb8cb20-14b1-11ea-90e2-9568aa518b84/image.png)

우측 하단에 보이는 Example Fashions 는 해당 색상에 대해 추천할 만한 패션들을 구글 이미지에서 크롤링해와서 띄워준다.

![image.png](https://images.velog.io/post-images/dvmflstm/309305d0-14b1-11ea-9f39-516187c18223/image.png)

![image.png](https://images.velog.io/post-images/dvmflstm/4d652570-14b2-11ea-8f12-8b681d7ea05a/image.png)


## Search Page
Recommend page와 전체적으로 유사하지만, 어플리케이션이 추천해주는 것이 아닌 사용자가 선택한 임의의 색상에 대해서 tone in tone 조합과 tone on tone 조합을 제시해준다.

![image.png](https://images.velog.io/post-images/dvmflstm/9c8c9fd0-14b1-11ea-90e2-9568aa518b84/image.png)

이처럼 마음에 드는 색상을 골라 search 버튼을 누르면?

![image.png](https://images.velog.io/post-images/dvmflstm/0541bd30-14b2-11ea-90e2-9568aa518b84/image.png)

해당 색상에 대한 tone in tone, tone on tone 조합 조회가 가능하다.

## 메인 컬러는 어떻게 결정하나?
tone in tone / tone on tone 색상 조합을 제시하는 알고리즘을 구상할 때도 많은 노력을 기울였지만, 사실 그 날의 메인 컬러를 결정하는 부분이 가장 고민이었다. 이는 정해진 답이 있는 것이 아닌 주관적인 부분이기 때문이었다. 현재로서는 간단하게 아래와 같은 과정을 거친다.

1. 모든 메인 컬러 후보에 100점의 default score를 부여한다.
2. 사용자가 선택한 테마가 모노톤이라면 무채색 중에서, 모노톤이 아니라면 유채색 중에서 메인컬러를 선택한다.
3. 사용자의 최근 코디 기록을 보고, 최근에 입은 색상에 대한 점수를 감산한다.
	3-1. 더 최근 기록일 수록 더 많은 점수를 감산한다.
4. 사용자의 선호 색상을 보고 이 색상에 대한 점수를 가산한다.
5. 약간의 variation을 위해 랜덤 확률로 약간의 점수에 변동을 주어 최종 점수를 결정한다.
6. 결정된 최종 점수가 가장 높은 색상을 메인 컬러로 선택한다.

알고리즘을 보완하기 위해 그날의 날씨(흐림, 맑음, 계절 등) 혹은 사람의 체형 등을 추가적으로 고려할 수 있을 것 같다. 또한 사람이 하의 혹은 상의의 색상을 무조건 단색으로만 입는다는 보장도 없기 때문에 이 부분에 대한 추가적인 고민도 필요할 것 같다.

## 프로젝트를 통해 배운 점
### Frontend
- tailwindcss를 이용한 기민한 스타일 적용. css 파일은 일체 사용하지 않고 전체적으로 가볍게 스타일링할 수 있었음
- React DnD를 이용한 drag & drop 구현
- Cheerio를 이용한 google image 크롤링
- 조금더 깔끔한 UX를 제공하기 위한 async job 관리 및 loading state 관리

### Backend
- Kotlin + Spring-boot로 완결성 있는 프로젝트 완성
- SOLID 및 clean architecture 원칙 지켜가며 코드 작성하기
- table 간의 복잡한 관계를 가지는 DB구조에서 JPA entity 정의하기
- Spring boot에서 cors 관련 정책 설정하기
- JUnit을 통한 유닛 테스트
- 보편적인 CRUD만 작성하는 것이 아닌, 특수한 상황에서 적용할 수 있는 rule-based algorithm 고안
- RGB 패턴을 3차원 좌표평면에 옮겨, 기하학적인 개념을 코드로 구현

### CI & CD
- AWS EC2 + S3 + CodeDeploy + github Travis CI 를 이용한 빌드 및 배포 자동화 구현
