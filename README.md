# JSP Community Project
* 본 프로젝트는 **CRUD**기능을 구현하기 위한 게시판 프로젝트입니다. 그리고 로그인을 통해서 게시글을 추가, 갱신, 삭제의 기능을 할 수 있습니다.

## 01. 개발기간 :calendar: 
* 2023.01 ~ 2019.02(~ing)
## 02. 사용기술 및 개발 🔥
* **OS** : Window 10
* **Tools** : IntelliJ, SQLyog, GitHub
* **Back-End** : Tomcat 10.0.27, Amazon Corretto 17.0.4
* **Front-End** : HTML, CSS
* **DB** : MySQL 8.0.22, SQLyog

## 03.프로젝트 소개 📝
 * 본 프로젝트는 JSP기술을 이용하였으며 MVC패턴에 대해서 이해할 수 있도록 **JSP에서 MVC를 모두 처리하는 MVC1 패턴에서 Dispatcher Servlet을 통해 
 Controller와 View를 분리시키는 과정을 포함하고 있으며, Front Controller에 대한 이해도를 학습할 수 있습니다.** JSP 기술을 이용하여 프로젝트를 구현한 이유는 Spring Framework에 대해 학습하기 위해서는
 **Servlet에 대한 개념에 대해 알고 있어야한다고 생각하기 때문**입니다. <br>
 * 그리고 해당 프로젝트를 진행하면서 Controller, Model, View의 역할들에 대해서 학습할 수 있었고 웹 브라우저의 요청으로
 부터 응답까지의 과정 및 흐름에 대해 학습할 수 있었습니다. 또한 모든 로직들을 한 곳에서 처리하는 것이 아니라 데이터베이스의 데이터에 접근하기
 위한 **DAO(Data Access Object)객체와 , 계층간 데이터 교환을 위한 DTO(Data Transfer Object)객체를 분리하여 개발하는 것이 유지보수 측면에서 매우 효율적이라는 점**도 알게되었다. 
 조금 더 쉽게 설명하자면, DAO는 DB를 사용하여 데이터의 조회 및 조작하는 기능을 담당하기 때문에 DB에 직접적인 코드를 포함하고, DTO는 DAO로부터 전달받은 순수한 데이터의 객체이며
 일반적으로 객체의 속성과 그 속성의 접근을 위한 메소드를 구현하고 있다. <br>
 * 추가적으로, DTO는 VO와 혼용하여 사용되는데, 차이점은 **VO는 읽기만 가능한 read only 속성을 가진다는 점**이다.
 
## 04. 프로젝트 기능 👏
* CRUD
* Login
* Logout
* 게시글 페이징
* Login Id 중복 확인
* 추가기능
  * 게시글 삭제 및 권한 부여 기능
  * 중복 로그인 및 회원가입 검사  기능

## 05. ERD
 <img src="https://user-images.githubusercontent.com/85385921/216825133-34fc3612-1795-4acb-aef3-6b76facb0dc4.PNG"/>
